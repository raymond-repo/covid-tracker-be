package com.server.covid.tracker.dto.response;

import java.util.List;

public class VaccineSummaryResponse {

	private String datePublished;

	private List<VaccineSummaryListResponse> vaccineSummaryListResponse;

	public VaccineSummaryResponse() {
		super();
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

	public List<VaccineSummaryListResponse> getVaccineSummaryListResponse() {
		return vaccineSummaryListResponse;
	}

	public void setVaccineSummaryListResponse(List<VaccineSummaryListResponse> vaccineSummaryListResponse) {
		this.vaccineSummaryListResponse = vaccineSummaryListResponse;
	}

}
