package com.server.covid.tracker.service;

import java.util.List;

import com.server.covid.tracker.dto.request.RegionRequest;
import com.server.covid.tracker.dto.response.CasesResponse;

public interface RegionService {
	
	List<CasesResponse> getAllRegion();
	
	List<CasesResponse> getRegionByName(String name);
	
	CasesResponse getSummary();
	
	CasesResponse getRegionByNameAndLatestPublishedDate(String name);
	
	void saveRegion(RegionRequest regionRequest);
	
	void saveBulkRegion(List<RegionRequest> regionRequestList);
	
	void deleteByName(String name);

}
