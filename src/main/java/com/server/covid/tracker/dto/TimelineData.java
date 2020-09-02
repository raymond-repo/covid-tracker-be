package com.server.covid.tracker.dto;

public class TimelineData {

	private Integer totalCasesTimeline;

	private Integer recoveredTimeline;

	private Integer diedTimeline;

	public Integer getTotalCasesTimeline() {
		return totalCasesTimeline;
	}

	public TimelineData(Integer totalCasesTimeline, Integer recoveredTimeline, Integer diedTimeline) {
		this.totalCasesTimeline = totalCasesTimeline;
		this.recoveredTimeline = recoveredTimeline;
		this.diedTimeline = diedTimeline;
	}

	public void setTotalCasesTimeline(Integer totalCasesTimeline) {
		this.totalCasesTimeline = totalCasesTimeline;
	}

	public Integer getRecoveredTimeline() {
		return recoveredTimeline;
	}

	public void setRecoveredTimeline(Integer recoveredTimeline) {
		this.recoveredTimeline = recoveredTimeline;
	}

	public Integer getDiedTimeline() {
		return diedTimeline;
	}

	public void setDiedTimeline(Integer diedTimeline) {
		this.diedTimeline = diedTimeline;
	}

}
