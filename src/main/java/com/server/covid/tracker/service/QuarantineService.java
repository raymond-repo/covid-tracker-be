package com.server.covid.tracker.service;

import java.util.List;

import com.server.covid.tracker.dto.request.QuarantineRequest;
import com.server.covid.tracker.dto.response.QuarantineResponse;

public interface QuarantineService {
	
	QuarantineResponse getLatestQuarantineAreas();
	
	void save(List<QuarantineRequest> quarantineRequest);

}
