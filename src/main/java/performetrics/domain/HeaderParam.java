package performetrics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "header_param")
public class HeaderParam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "header_param_id")
	private Long id;

	@Column(name = "header_key")
	private String headerKey;

	@Column(name = "header_value")
	private String headerValue;

	// @ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JoinColumn(name = "step_id", nullable = false)
	// private ExecutionSteps executionSteps;

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	// public ExecutionSteps getExecutionSteps() {
	// return executionSteps;
	// }
	//
	// public void setExecutionSteps(ExecutionSteps executionSteps) {
	// this.executionSteps = executionSteps;
	// }

}
