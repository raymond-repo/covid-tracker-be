package com.server.covid.tracker.dto.request;

import java.util.List;

public class SummaryRequest {

	private String date;

	private Integer newCase;

	private Integer totalCases;

	private Integer newDeaths;

	private Integer totalDeaths;

	private Integer active;

	private Integer recoveries;

	private List<RegionRequest> region;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getNewCase() {
		return newCase;
	}

	public void setNewCase(Integer newCase) {
		this.newCase = newCase;
	}

	public Integer getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(Integer totalCases) {
		this.totalCases = totalCases;
	}

	public Integer getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(Integer newDeaths) {
		this.newDeaths = newDeaths;
	}

	public Integer getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(Integer totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(Integer recoveries) {
		this.recoveries = recoveries;
	}

	public List<RegionRequest> getRegion() {
		return region;
	}

	public void setRegion(List<RegionRequest> region) {
		this.region = region;
	}

}
