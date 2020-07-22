package performetrics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "query_param")
public class QueryParam {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "query_param_id")
	private Long id;

	@Column(name = "param_key")
	private String paramKey;

	@Column(name = "param_value")
	private String paramValue;

	// @ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JoinColumn(name = "step_id", nullable = false)
	// private ExecutionSteps executionSteps;

	public String getParamName() {
		return paramKey;
	}

	public void setParamName(String paramName) {
		this.paramKey = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
