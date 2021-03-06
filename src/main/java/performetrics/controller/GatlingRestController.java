package performetrics.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import performetrics.domain.Simulation;
import performetrics.service.PerformetricService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = GatlingBaseController.BasePath + "/v1/performance", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Performance", description = "Performance Operations")
public class GatlingRestController {

	private Logger log = LoggerFactory.getLogger(GatlingRestController.class);

	@Autowired
	private PerformetricService performanceService;

	/**
	 * 
	 * @param simulation
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Simulation> generateScalaPerformanceFile(@RequestBody Simulation simulation) {

		log.info("Call generateScalaPerformanceFile ");
		return performanceService.generateSimulator(simulation);
	}

	/**
	 * 
	 * @param simulationId
	 * @param scalaFileName
	 * @return
	 * @throws InterruptedException
	 */
	@PostMapping("/invokeGatling")
	@ApiResponse(description = "to execute the maven command for gatling")
	@Operation(description = "to execute the maven command for gatling")
	public ResponseEntity<Object> invokeGatlingCommand(@RequestParam Long simulationId,
			@RequestParam String scalaFileName) throws InterruptedException {

		performanceService.invokeScalaCommand(simulationId, scalaFileName);

		return new ResponseEntity<>("Successfully Triggered Gatling Execution", HttpStatus.OK);
	}

	@GetMapping("/io")
	public String io() throws InterruptedException {
		long sleepDuration = 200L + (long) (500L * Math.random());
		Thread.sleep(sleepDuration);
		return "done after " + sleepDuration + "ms";
	}

	/***
	 * Get All Status
	 * 
	 * @return
	 */
	@GetMapping("/getAllSimulations")
	public ResponseEntity<List<Simulation>> getAllSimulations() {
		return new ResponseEntity<List<Simulation>>(performanceService.getAllSimulations(), HttpStatus.OK);

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/simulation")
	public ResponseEntity<Simulation> getSimulationById(@RequestParam(value = "id", required = true) Long id) {
		log.info("Call getSimulationById " + id);
		Simulation simulation = performanceService.getSimulationById(id);
		if (simulation != null) {
			return new ResponseEntity<Simulation>(simulation, HttpStatus.OK);
		} else {
			return new ResponseEntity<Simulation>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/moveFiles")
	@ApiResponse(description = "to move files to another directory ")
	@Operation(description = "to move files to another directory")
	public ResponseEntity<Object> moveFiles(@RequestParam String scalaFileName) throws IOException {

		performanceService.moveFiles(scalaFileName);

		return new ResponseEntity<>("Successfully Moved the files", HttpStatus.OK);
	}

}
