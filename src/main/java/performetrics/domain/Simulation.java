package performetrics.domain;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

	private String simulationName;

	private String baseUrl;

	private String scenarioName;

	private List<ExecutionSteps> steps = new ArrayList<ExecutionSteps>();

	private String constantConncurrentUsers;

	private String constantConncurrentUserDuration;

	private String rampConcurrentUsersStart;

	private String rampConcurrentUsersEnd;

	private String rampUpDuration;

	public String getSimulationName() {
		return simulationName;
	}

	public void setSimulationName(String simulationName) {
		this.simulationName = simulationName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public List<ExecutionSteps> getSteps() {
		return steps;
	}

	public void setSteps(List<ExecutionSteps> steps) {
		this.steps = steps;
	}

	public String getConstantConncurrentUsers() {
		return constantConncurrentUsers;
	}

	public void setConstantConncurrentUsers(String constantConncurrentUsers) {
		this.constantConncurrentUsers = constantConncurrentUsers;
	}

	public String getConstantConncurrentUserDuration() {
		return constantConncurrentUserDuration;
	}

	public void setConstantConncurrentUserDuration(String constantConncurrentUserDuration) {
		this.constantConncurrentUserDuration = constantConncurrentUserDuration;
	}

	public String getRampConcurrentUsersStart() {
		return rampConcurrentUsersStart;
	}

	public void setRampConcurrentUsersStart(String rampConcurrentUsersStart) {
		this.rampConcurrentUsersStart = rampConcurrentUsersStart;
	}

	public String getRampConcurrentUsersEnd() {
		return rampConcurrentUsersEnd;
	}

	public void setRampConcurrentUsersEnd(String rampConcurrentUsersEnd) {
		this.rampConcurrentUsersEnd = rampConcurrentUsersEnd;
	}

	public String getRampUpDuration() {
		return rampUpDuration;
	}

	public void setRampUpDuration(String rampUpDuration) {
		this.rampUpDuration = rampUpDuration;
	}

}
