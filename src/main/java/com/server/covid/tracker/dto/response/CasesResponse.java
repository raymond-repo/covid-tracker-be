package com.server.covid.tracker.dto.response;

import java.util.List;

public class CasesResponse {

	private String name;

	private Integer totalCase;

	private Integer newCase;

	private Integer activeCase;

	private Integer recovered;

	private Integer died;

	private String datePublished;

	private List<Integer> totalCasesTimeline;

	private List<Integer> recoveredTimeline;

	private List<Integer> diedTimeline;

	public CasesResponse() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalCase() {
		return totalCase;
	}

	public void setTotalCase(Integer totalCase) {
		this.totalCase = totalCase;
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
