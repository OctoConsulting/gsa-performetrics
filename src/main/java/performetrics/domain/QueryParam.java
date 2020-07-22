package performetrics.domain;

public class QueryParam {

	private String paramKey;

	private String paramValue;

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
