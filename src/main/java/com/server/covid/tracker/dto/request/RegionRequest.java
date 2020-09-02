package com.server.covid.tracker.dto.request;

public class RegionRequest {

	private String name;

	private Integer newCase;

	private Integer activeCase;

	private Integer recovered;

	private Integer died;

	private String datePublished;

	public RegionRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNewCase() {
		return newCase;
	}

	public void setNewCase(Integer newCase) {
		this.newCase = newCase;
	}

	public Integer getActiveCase() {
		return activeCase;
	}

	public void setActiveCase(Integer activeCase) {
		this.activeCase = activeCase;
	}

	public Integer getRecovered() {
		return recovered;
	}

	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}

	public Integer getDied() {
		return died;
	}

	public void setDied(Integer died) {
		this.died = died;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

}
