package performetrics.utility;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import performetrics.domain.ExecutionSteps;
import performetrics.domain.QueryParam;
import performetrics.domain.Simulation;

@Component
public class ScalaSimulatorGenerator {

	private static final Logger log = LoggerFactory.getLogger(ScalaSimulatorGenerator.class);

	public String scalaGeneratorData(String uuid, Simulation simulation) {
		String simualtionName = simulation.getSimulationName() + "_" + uuid;

		StringBuilder builder = new StringBuilder();
		builder.append("package performetrics \n \n");

		builder.append("import scala.concurrent.duration._ \n \n");

		builder.append("import io.gatling.core.Predef._ \n");
		builder.append("import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder} \n");
		builder.append("import io.gatling.http.Predef._ \n");
		builder.append("import io.gatling.http.protocol.HttpProtocolBuilder \n\n");

		builder.append("class " + simualtionName + "extends Simulation { \n\n");

		builder.append("val httpProtocol = http \n");
		builder.append(".baseURL(\"" + simulation.getBaseUrl() + "\") \n\n");

		builder.append("}");

		return builder.toString();
	}

	public void exececutionStepBuilder(StringBuilder builder, Simulation simulation) {

		builder.append("val scn = scenario(\" " + simulation.getScenarioName() + "\") \n");
		for (ExecutionSteps exec : simulation.getSteps()) {
			builder.append(".exec( \n");
			builder.append("http( \" " + exec.getExecutionName() + " \" ) \n");

			this.buildRequest(builder, exec);

			if (CollectionUtils.isNotEmpty(exec.getQueryParams())) {
				this.buildQueryParam(builder, exec.getQueryParams());
			}
			builder.append(" )");
		}
	}

	public void buildRequest(StringBuilder builder, ExecutionSteps step) {
		switch (step.getRequestType()) {
		case "get":
			builder.append(".get(\"" + step.getRoute() + "\" ) \n");
			break;
		case "post":
			builder.append(".post(\"" + step.getRoute() + "\" ) \n");
			break;
		case "put":
			builder.append(".put(\"" + step.getRoute() + "\" ) \n");
			break;
		case "delete":
			builder.append(".delete(\"" + step.getRoute() + "\" ) \n");
			break;
		case "head":
			builder.append(".head(\"" + step.getRoute() + "\" ) \n");
			break;
		case "patch":
			builder.append(".patch(\"" + step.getRoute() + "\" ) \n");
			break;
		case "options":
			builder.append(".options(\"" + step.getRoute() + "\" ) \n");
			break;
		default:
			break;
		}

	}

	public void buildQueryParam(StringBuilder builder, List<QueryParam> params) {
		for (QueryParam param : params) {
			builder.append(".queryParam(\"" + param.getParamName() + "\", \" " + param.getParamValue() + "\") \n");
		}
	}

}
