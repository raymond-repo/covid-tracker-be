package com.server.covid.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.covid.tracker.dto.request.QuarantineRequest;
import com.server.covid.tracker.dto.request.SummaryRequest;
import com.server.covid.tracker.dto.request.VaccineSummaryRequest;
import com.server.covid.tracker.dto.response.QuarantineResponse;
import com.server.covid.tracker.dto.response.SummaryResponse;
import com.server.covid.tracker.dto.response.VaccineSummaryResponse;
import com.server.covid.tracker.service.QuarantineService;
import com.server.covid.tracker.service.SummaryService;
import com.server.covid.tracker.service.VaccineSummaryService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private SummaryService summaryService;
	
	@Autowired
	private QuarantineService quarantineService;
	
	@Autowired
	private VaccineSummaryService vaccineSummaryService;
	
	@GetMapping("/getLatestSummary")
	public SummaryResponse getLatestSummary() {
		return summaryService.getLatestSummary();
	}
	
	@PostMapping("/saveSummary")
	public void saveSummary(@RequestBody List<SummaryRequest> summaryRequest) {
		summaryService.save(summaryRequest);
	}
	
	@GetMapping("/getLatestQuarantine")
	public QuarantineResponse getLatestQuarantine() {
		return quarantineService.getLatestQuarantineAreas();
	}
	
	@PostMapping("/saveQuarantine")
	public void saveQuarantine(@RequestBody List<QuarantineRequest> quarantineRequest) {
		quarantineService.save(quarantineRequest);
	}
	
	@GetMapping("/getLatestVaccineSummary")
	public VaccineSummaryResponse getLatestVaccineSummary() {
		return vaccineSummaryService.getLatestVaccineSummary();
	}
	
	@PostMapping("/saveVaccineSummary")
	public void saveVaccineSummary(@RequestBody VaccineSummaryRequest vaccineSummaryRequest) {
		vaccineSummaryService.save(vaccineSummaryRequest);
	}
}
