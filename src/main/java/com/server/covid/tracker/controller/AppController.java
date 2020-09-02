package com.server.covid.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.covid.tracker.dto.request.RegionRequest;
import com.server.covid.tracker.dto.request.VaccineSummaryRequest;
import com.server.covid.tracker.dto.response.CasesResponse;
import com.server.covid.tracker.dto.response.VaccineSummaryResponse;
import com.server.covid.tracker.service.RegionService;
import com.server.covid.tracker.service.VaccineSummaryService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private VaccineSummaryService vaccineSummaryService;
	
	@GetMapping("/getAllRegion")
	public List<CasesResponse> getAllRegion(){
		return regionService.getAllRegion();
	}
	
	@GetMapping("/getSummary")
	public CasesResponse getSummary(){
		return regionService.getSummary();
	}
	
	@GetMapping("/getRegionByName")
	public List<CasesResponse> getRegionByName(@RequestParam String name){
		return regionService.getRegionByName(name);
	}
	
	@GetMapping("/getRegionByNameAndLatestPublishedDate")
	public CasesResponse getRegionByNameAndLatestPublishedDate(@RequestParam String name){
		return regionService.getRegionByNameAndLatestPublishedDate(name);
	}
	
	@PostMapping("/saveRegion")
	public void saveRegion(@RequestBody RegionRequest regionRequest) {
		regionService.saveRegion(regionRequest);
	}
	
	@PostMapping("/saveBulkRegion")
	public void saveBulkRegion(@RequestBody List<RegionRequest> regionRequestList) {
		regionService.saveBulkRegion(regionRequestList);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam String name) {
		regionService.deleteByName(name);
	}
	
	@GetMapping("/getAllVaccineSummary")
	public List<VaccineSummaryResponse> getAllVaccineSummary() {
		return vaccineSummaryService.getAllVaccineSummary();
	}
	
	@GetMapping("/getLatestVaccineSummary")
	public VaccineSummaryResponse getLatestVaccineSummary() {
		return vaccineSummaryService.getLatestVaccineSummary();
	}
	
	@PostMapping("/saveVaccineSummary")
	public void saveVaccineSummary(@RequestBody VaccineSummaryRequest vaccineSummaryRequest) {
		vaccineSummaryService.save(vaccineSummaryRequest);
	}
	
	@PostMapping("/bulkSaveVaccineSummary")
	public void bulkSaveVaccineSummary(@RequestBody List<VaccineSummaryRequest> listOfVaccineSummaryRequest) {
		vaccineSummaryService.bulkSave(listOfVaccineSummaryRequest);
	}
	
	@DeleteMapping("/deleteById")
	public void deleteById(@RequestParam Integer id) {
		vaccineSummaryService.deleteById(id);
	}
	
	@DeleteMapping("/deleteByDeveloperManufacturer")
	public void deleteByDeveloperManufacturer(@RequestParam String developerMenufacturer) {
		vaccineSummaryService.deleteByDeveloperManufacturer(developerMenufacturer);
	}
}
