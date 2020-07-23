package performetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @RestController
public class PerftestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerftestApplication.class, args);
	}

	// @GetMapping("/io")
	// public String io() throws InterruptedException {
	// long sleepDuration = 200L + (long) (500L * Math.random());
	// Thread.sleep(sleepDuration);
	// return "done after " + sleepDuration + "ms";
	// }

}
