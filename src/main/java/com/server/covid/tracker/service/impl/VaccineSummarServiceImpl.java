package com.server.covid.tracker.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.server.covid.tracker.dao.VaccineSummaryDAO;
import com.server.covid.tracker.dto.request.VaccineSummaryRequest;
import com.server.covid.tracker.dto.response.VaccineSummaryListResponse;
import com.server.covid.tracker.dto.response.VaccineSummaryResponse;
import com.server.covid.tracker.entity.VaccineSummaryEntity;
import com.server.covid.tracker.service.VaccineSummaryService;

@Service
public class VaccineSummarServiceImpl implements VaccineSummaryService {

	private static final Logger lOGGER = LoggerFactory.getLogger(VaccineSummarServiceImpl.class);

	@Autowired
	private VaccineSummaryDAO vaccineSummaryDAO;

	@Override
	public void save(VaccineSummaryRequest vaccineSummaryRequest) {

		VaccineSummaryEntity vaccineSummaryEntity = new VaccineSummaryEntity();
		BeanUtils.copyProperties(vaccineSummaryRequest, vaccineSummaryEntity);
		vaccineSummaryEntity.setDatePublished(transformDate(vaccineSummaryRequest.getDatePublished(), "MMMM dd, yyyy", "yyyyMMdd"));
		
		vaccineSummaryDAO.save(vaccineSummaryEntity);
	}

	@Override
	public void bulkSave(List<VaccineSummaryRequest> listOfVaccineSummaryRequest) {
		vaccineSummaryDAO.saveAll(listOfVaccineSummaryRequest.stream().map(vaccineSummaryRequest -> {
			VaccineSummaryEntity vaccineSummaryEntity = new VaccineSummaryEntity();
			BeanUtils.copyProperties(vaccineSummaryRequest, vaccineSummaryEntity);
			vaccineSummaryEntity.setDatePublished(transformDate(vaccineSummaryRequest.getDatePublished(), "MMMM dd, yyyy", "yyyyMMdd"));
			return vaccineSummaryEntity;
		}).collect(Collectors.toList()));

	}

	@Override
	public void deleteById(Integer id) {
		vaccineSummaryDAO.deleteById(id);
	}

	@Override
	public void deleteByDeveloperManufacturer(String developerMenufacturer) {
		vaccineSummaryDAO.deleteByDeveloperManufacturer(developerMenufacturer);

	}

	@Override
	public VaccineSummaryResponse getLatestVaccineSummary() {

		VaccineSummaryResponse vaccineSummaryResponse = new VaccineSummaryResponse();

		String latestDate = vaccineSummaryDAO.getLatestPublishedDate();

		List<VaccineSummaryEntity> vaccineSummaryEntity = vaccineSummaryDAO
				.findByDatePublishedOrderByTrialPhaseDesc(latestDate);

		vaccineSummaryResponse.setDatePublished(transformDate(latestDate, "yyyyMMdd", "MMMM dd, yyyy"));
		vaccineSummaryResponse.setVaccineSummaryListResponse(vaccineSummaryEntity.stream().map(res -> {
			VaccineSummaryListResponse vaccineSummaryListResponse = new VaccineSummaryListResponse();

			vaccineSummaryListResponse.setTrialPhase(res.getTrialPhase());
			vaccineSummaryListResponse.setDeveloperManufacturer(res.getDeveloperManufacturer());

			return vaccineSummaryListResponse;
		}).collect(Collectors.toList()));

		return vaccineSummaryResponse;
	}

	@Override
	public List<VaccineSummaryResponse> getAllVaccineSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	private String transformDate(String date, String dateFormatFrom, String dateFormatTo) {

		if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(dateFormatFrom) && !StringUtils.isEmpty(dateFormatTo)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatFrom);
			try {
				java.util.Date dateValue = simpleDateFormat.parse(date);
				simpleDateFormat = new SimpleDateFormat(dateFormatTo);
				return simpleDateFormat.format(dateValue);
			} catch (ParseException e) {
				lOGGER.error(e.getMessage());
			}
		}
		return null;
	}

}
