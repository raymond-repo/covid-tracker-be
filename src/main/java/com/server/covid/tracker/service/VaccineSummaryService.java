package com.server.covid.tracker.service;

import java.util.List;

import com.server.covid.tracker.dto.request.VaccineSummaryRequest;
import com.server.covid.tracker.dto.response.VaccineSummaryResponse;

public interface VaccineSummaryService {
	
	void save(VaccineSummaryRequest vaccineSummaryRequest);
	
	void bulkSave(List<VaccineSummaryRequest> listOfVaccineSummaryRequest);
	
	void deleteById(Integer id);
	
	void deleteByDeveloperManufacturer(String developerMenufacturer);
	
	VaccineSummaryResponse getLatestVaccineSummary();
	
	List<VaccineSummaryResponse> getAllVaccineSummary();

}
