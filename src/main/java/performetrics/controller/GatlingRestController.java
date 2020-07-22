package performetrics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import performetrics.domain.Simulation;
import performetrics.service.PerformetricService;

@RestController
@RequestMapping(value = GatlingBaseController.BasePath + "/v1/performance", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Performance", description = "Performance Operations")
public class GatlingRestController {

	private Logger log = LoggerFactory.getLogger(GatlingRestController.class);

	@Autowired
	private PerformetricService performanceService;

	@PostMapping
	public ResponseEntity<String> generateScalaPerformanceFile(@RequestBody Simulation simulation) {

		log.info("Call generateScalaPerformanceFile ");
		return performanceService.generateSimulator(simulation);
	}

}
