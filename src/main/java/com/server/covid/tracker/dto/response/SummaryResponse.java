package com.server.covid.tracker.dto.response;

import java.util.List;

public class SummaryResponse {

	private String date;

	private Integer newCase;

	private Integer totalCases;

	private Integer newDeaths;

	private Integer totalDeaths;

	private Integer newActive;

	private Integer active;

	private Integer newRecoveries;

	private Integer recoveries;

	private List<RegionResponse> region;

	private List<Integer> totalCasesTimeline;

	private List<Integer> recoveredTimeline;

	private List<Integer> diedTimeline;

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

	public Integer getNewActive() {
		return newActive;
	}

	public void setNewActive(Integer newActive) {
		this.newActive = newActive;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getNewRecoveries() {
		return newRecoveries;
	}

	public void setNewRecoveries(Integer newRecoveries) {
		this.newRecoveries = newRecoveries;
	}

	public Integer getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(Integer recoveries) {
		this.recoveries = recoveries;
	}

	public List<RegionResponse> getRegion() {
		return region;
	}

	public void setRegion(List<RegionResponse> region) {
		this.region = region;
	}

	public List<Integer> getTotalCasesTimeline() {
		return totalCasesTimeline;
	}

	public void setTotalCasesTimeline(List<Integer> totalCasesTimeline) {
		this.totalCasesTimeline = totalCasesTimeline;
	}

	public List<Integer> getRecoveredTimeline() {
		return recoveredTimeline;
	}

	public void setRecoveredTimeline(List<Integer> recoveredTimeline) {
		this.recoveredTimeline = recoveredTimeline;
	}

	public List<Integer> getDiedTimeline() {
		return diedTimeline;
	}

	public void setDiedTimeline(List<Integer> diedTimeline) {
		this.diedTimeline = diedTimeline;
	}

}
