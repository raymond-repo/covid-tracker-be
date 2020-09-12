package com.server.covid.tracker.dto.response;

public class RegionResponse {

	private String name;

	private Integer cases;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCases() {
		return cases;
	}

	public void setCases(Integer cases) {
		this.cases = cases;
	}
}
