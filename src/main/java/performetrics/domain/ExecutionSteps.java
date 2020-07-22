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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "execution_steps")
public class ExecutionSteps {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="step_id")
	private Long id;
	
	@Column(name="execution_name")
	private String executionName;

	@Column(name="request_type")
	private String requestType;

	@Column(name="route")
	private String route;

	@OneToMany(mappedBy = "executionSteps",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<QueryParam> queryParams = new ArrayList<QueryParam>();

	@OneToMany(mappedBy = "executionSteps",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HeaderParam> authHeader = new ArrayList<>();

	@Column(name="pause")
	private int pause;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "simultation_id", nullable = false)
	private Simulation simulation;
	
	public String getExecutionName() {
		return executionName;
	}

	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public List<QueryParam> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(List<QueryParam> queryParams) {
		this.queryParams = queryParams;
	}

	public List<HeaderParam> getAuthHeader() {
		return authHeader;
	}

	public void setAuthHeader(List<HeaderParam> authHeader) {
		this.authHeader = authHeader;
	}

	public int getPause() {
		return pause;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
