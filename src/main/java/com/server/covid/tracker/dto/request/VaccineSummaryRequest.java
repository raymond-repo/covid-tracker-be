package com.server.covid.tracker.dto.request;

public class VaccineSummaryRequest {

	private String developerManufacturer;

	private String trialPhase;

	private String datePublished;

	public VaccineSummaryRequest() {
		super();
	}

	public String getDeveloperManufacturer() {
		return developerManufacturer;
	}

	public void setDeveloperManufacturer(String developerManufacturer) {
		this.developerManufacturer = developerManufacturer;
	}

	public String getTrialPhase() {
		return trialPhase;
	}

	public void setTrialPhase(String trialPhase) {
		this.trialPhase = trialPhase;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

}
