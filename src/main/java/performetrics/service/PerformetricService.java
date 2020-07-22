package performetrics.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.apache.maven.cli.MavenCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import performetrics.domain.ExecutionSteps;
import performetrics.domain.Simulation;
import performetrics.repository.GatlingRepository;
import performetrics.utility.ScalaSimulatorGenerator;

@Service
public class PerformetricService {

	private static final String CURR_PATH = System.getProperty("user.dir");

	private static final Logger log = LoggerFactory.getLogger(PerformetricService.class);

	@Autowired
	private ScalaSimulatorGenerator scalaSimulatorGenerator;

	public ResponseEntity<String> generateSimulator(Simulation simulation) {
		UUID uuid = UUID.randomUUID();
		String FileName = CURR_PATH + "/src/test/scala/performetrics/" + simulation.getSimulationName() + "_"
				+ uuid.toString() + ".scala";
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

			String scalaData = scalaSimulatorGenerator.scalaGeneratorData(uuid.toString(), simulation);

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

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@Autowired
	private GatlingRepository gatlingRepo;
	
	
	/**
	 * 
	 */
	public void  invokeScalaCommand() {
    	
		log.info("Start Invoking the gatling command ::");
		CompletableFuture.runAsync(() -> {
    	    MavenCli cli = new MavenCli();
			cli.doMain(new String[]{"gatling:test","-Dgatling.simulationClass=performetrics.AlertsSimulator"}, ".", System.out, System.out);
			
			ExecutionSteps executionSteps = new ExecutionSteps();//new ExecutionSteps("io","/gatling/v1/performance/io",true);
			log.info("saving the info in repository ::");
			gatlingRepo.save(executionSteps);
			log.info("end of the saving the info in repository ::");
    	});
		
		log.info("End of the Invoking the gatling command ::");
    }

}
