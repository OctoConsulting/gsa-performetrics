package performetrics.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.maven.cli.MavenCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import performetrics.domain.Simulation;
import performetrics.repository.SimulationRepository;
import performetrics.utility.ScalaSimulatorGenerator;

@Service
public class PerformetricService {

	private static final String CURR_PATH = System.getProperty("user.dir");

	private static final Logger log = LoggerFactory.getLogger(PerformetricService.class);

	@Autowired
	private ScalaSimulatorGenerator scalaSimulatorGenerator;

	@Autowired
	private SimulationRepository simulationRepository;

	@Value("${scala.results.path}")
	private String destinationResultsPath;
	
	public ResponseEntity<Simulation> generateSimulator(Simulation simulation) {

		Random rand = new Random();
		int randVal = Math.abs(rand.nextInt(Integer.MAX_VALUE));

		String simulationFileName = simulation.getSimulationName() + "_" + randVal;

		String FileName = CURR_PATH + "/src/test/scala/performetrics/" + simulationFileName + ".scala";
		log.info("fileName " + FileName);

		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(FileName);
			// String scalaData = "package performetrics\n" + "\n" + "import
			// io.gatling.core.Predef._\n"
			// + "import io.gatling.http.Predef._\n" + "import
			// scala.concurrent.duration._\n" + "\n"
			// + "class AlertsSimulator extends Simulation {\n" + "\n" + " val httpProtocol
			// = http\n"
			// + " .baseURL(\"http://localhost:8080\")\n" + "\n" + " val scn =
			// scenario(\"AlertsSimulator\")\n"
			// + " .repeat(10) {\n" + " exec(http(\"GET /io\").get(\"/alert/v2/alerts\"))\n"
			// + " }\n"
			// + "\n" + " setUp(\n" + " scn.inject(atOnceUsers(10))\n" + "
			// ).protocols(httpProtocol)\n" + "}";

			String scalaData = scalaSimulatorGenerator.scalaGeneratorData(randVal, simulation);

			byte[] bytesArray = scalaData.getBytes();
			outputStream.write(bytesArray);
			outputStream.flush();
		} catch (IOException e) {
			log.error("Error ", e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException ioe) {
				log.error("Error ", ioe);
			}
		}
		simulation.setProcessingStatus("PROCESSING");
		simulation.setSimulationFileName(simulationFileName);
		
		Simulation savedSimulation = simulationRepository.save(simulation);

		
		return new ResponseEntity<Simulation>(savedSimulation, HttpStatus.OK);
	}

	/**
	 * 
	 */
	public void invokeScalaCommand(long simulationId, String scalaFileName) {

		log.info("Start Invoking the gatling command ::");

		CompletableFuture.runAsync(() -> {
			final String fileName = "performetrics.".concat(scalaFileName);
			MavenCli cli = new MavenCli();
			cli.doMain(new String[] { "gatling:test", "-Dgatling.simulationClass=" + fileName }, ".", System.out,
					System.out);

			Simulation simulation = simulationRepository.findById(simulationId);
			simulation.setProcessingStatus("COMPLETED");
			
			String resultsFolderName = "";
			try {
				resultsFolderName =moveFiles(scalaFileName);
			} catch (IOException e) {
				log.error("Error in moving the files ::",e);
			}
			simulation.setSimulationResultsFolderName(resultsFolderName);
			log.info("saving the info in repository ::");
			simulationRepository.save(simulation);
			log.info("end of the saving the info in repository ::");
		});

		
		log.info("End of the Invoking the gatling command ::");
	}

	/**
	 * 
	 * @return
	 */
	public List<Simulation> getAllSimulations() {
		return simulationRepository.findAll();
	}

	public Simulation getSimulationById(Long id) {
		return simulationRepository.findById(id);
	}
	
	public String moveFiles(String relativeFileName) throws IOException {
		
		//Pattern pattern = Pattern.compile("(_)*[0-9]");
	    //Matcher matcher = pattern.matcher(relativeFileName);
	    //matcher.replaceFirst("-");
		String[] fileNames = relativeFileName.split("(_)",2);
		final String matchingFiles  = fileNames[0].concat("-") .concat(fileNames[1]).toLowerCase();
		log.info("Start of the move files ::");
		File fromFile = new File("./target/gatling/"); 
		
		File destFile = new File(destinationResultsPath+matchingFiles.toLowerCase());
		
        File[] files = fromFile.listFiles(new FilenameFilter() {
            
           @Override
           public boolean accept(File dir, String name) {
               if(name.toLowerCase().startsWith(matchingFiles.toLowerCase())){
                   return true;
               } else {
                   return false;
               }
           }
       });
        
        for (File file : files) {
        	FileUtils.copyDirectory(file, destFile);
		}
        log.info("End of the move files, files coped to directory::{}", destFile.getAbsolutePath()); 
        
        return matchingFiles;
        
        
        
	}

}
