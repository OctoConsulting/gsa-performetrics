package performetrics.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "simulation")
public class Simulation {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="simultation_id")
	private Long id;
	
	@Column(name = "simulation_name")
	private String simulationName;

	@Column(name = "base_url")
	private String baseUrl;

	@Column(name = "scenario_name")
	private String scenarioName;

	@OneToMany(mappedBy = "simulation",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ExecutionSteps> steps = new ArrayList<ExecutionSteps>();

	@Column(name = "concurrent_users")
	private String constantConncurrentUsers;

	@Column(name = "conncurrent_user_duration")
	private String constantConncurrentUserDuration;

	@Column(name = "ramp_concurrent_users_start")
	private String rampConcurrentUsersStart;

	@Column(name = "ramp_concurrent_users_end")
	private String rampConcurrentUsersEnd;

	@Column(name = "ramp_up_duration")
	private String rampUpDuration;

	@Column(name = "status")
	private String processingStatus;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}

}
