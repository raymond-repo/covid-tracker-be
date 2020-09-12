package com.server.covid.tracker.service;

import java.util.List;

import com.server.covid.tracker.dto.request.SummaryRequest;
import com.server.covid.tracker.dto.response.SummaryResponse;

public interface SummaryService {
	
	SummaryResponse getLatestSummary();
	
	void save(List<SummaryRequest> summaryRequest);

}
