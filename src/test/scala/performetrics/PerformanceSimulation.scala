package performetrics

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PerformanceSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8080")

  val scn = scenario("PerformanceSimulation")
    .repeat(10) {
      exec(http("GET /io").get("/alert/v2/alerts"))
    }

  setUp(
    scn.inject(atOnceUsers(10))
  ).protocols(httpProtocol)
}