package com.server.covid.tracker.dto.response;

public class VaccineSummaryListResponse {

	private String trialPhase;

	private String developerManufacturer;

	public VaccineSummaryListResponse() {
		super();
	}

	public String getTrialPhase() {
		return trialPhase;
	}

	public void setTrialPhase(String trialPhase) {
		this.trialPhase = trialPhase;
	}

	public String getDeveloperManufacturer() {
		return developerManufacturer;
	}

	public void setDeveloperManufacturer(String developerManufacturer) {
		this.developerManufacturer = developerManufacturer;
	}

}
