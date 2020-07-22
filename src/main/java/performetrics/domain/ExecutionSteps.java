package performetrics.domain;

import java.util.ArrayList;
import java.util.List;

public class ExecutionSteps {

	private String executionName;

	private String RequestType;

	private String route;

	private List<QueryParam> queryParams = new ArrayList<QueryParam>();

	private List<HeaderParam> authHeader = new ArrayList<>();

	private int pause;

	public String getExecutionName() {
		return executionName;
	}

	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}

	public String getRequestType() {
		return RequestType;
	}

	public void setRequestType(String requestType) {
		RequestType = requestType;
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

}
