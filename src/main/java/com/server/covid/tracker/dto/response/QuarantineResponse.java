package com.server.covid.tracker.dto.response;

import java.util.List;

public class QuarantineResponse {

	private String date;

	private List<QuarantineListResponse> quarantineListResponse;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<QuarantineListResponse> getQuarantineListResponse() {
		return quarantineListResponse;
	}

	public void setQuarantineListResponse(List<QuarantineListResponse> quarantineListResponse) {
		this.quarantineListResponse = quarantineListResponse;
	}

}
