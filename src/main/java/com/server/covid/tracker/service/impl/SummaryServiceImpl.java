package com.server.covid.tracker.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.server.covid.tracker.dao.SummaryDAO;
import com.server.covid.tracker.dto.request.SummaryRequest;
import com.server.covid.tracker.dto.response.RegionResponse;
import com.server.covid.tracker.dto.response.SummaryResponse;
import com.server.covid.tracker.entity.RegionEntity;
import com.server.covid.tracker.entity.SummaryEntity;
import com.server.covid.tracker.service.SummaryService;

@Service
public class SummaryServiceImpl implements SummaryService {

	private static final Logger lOGGER = LoggerFactory.getLogger(SummaryServiceImpl.class);

	@Autowired
	private SummaryDAO summaryDAO;

	@Override
	public SummaryResponse getLatestSummary() {

		Integer latestDate = summaryDAO.getLatestDate();

		SummaryResponse summaryResponse = new SummaryResponse();
		SummaryEntity summaryEntity = summaryDAO.findById(latestDate).get();
		SummaryEntity beforeLatestDate = summaryDAO.findById(latestDate - 1).get();

		BeanUtils.copyProperties(summaryEntity, summaryResponse);

		summaryResponse.setNewActive(substructInt(summaryEntity.getActive(), beforeLatestDate.getActive()));
		summaryResponse.setNewRecoveries(substructInt(summaryEntity.getRecoveries(), beforeLatestDate.getRecoveries()));

		summaryResponse.setDate(transformDate(summaryEntity.getDate(), "yyyyMMdd", "MMMM dd, yyyy"));

		summaryResponse.setRegion(summaryEntity.getRegionEntity().stream().map(res -> {
			RegionResponse region = new RegionResponse();
			region.setName(res.getName());
			region.setCases(res.getCases());
			return region;
		}).collect(Collectors.toList()));

		List<Integer> totalCasesTimeline = new ArrayList<>();
		List<Integer> recoveredTimeline = new ArrayList<>();
		List<Integer> diedTimeline = new ArrayList<>();

		for (int i = 1; i <= 12; i++) {
			String dateYYMM = getCurrentYear() + String.format("%02d", i);

			Integer date = summaryDAO.getAvailableLatestDate(dateYYMM);

			if (null != date) {
				totalCasesTimeline.add(summaryDAO.getTotalCases(date));
				recoveredTimeline.add(summaryDAO.getTotalRecovered(date));
				diedTimeline.add(summaryDAO.getTotalDeaths(date));
			}

		}
		summaryResponse.setTotalCasesTimeline(totalCasesTimeline);
		summaryResponse.setRecoveredTimeline(recoveredTimeline);
		summaryResponse.setDiedTimeline(diedTimeline);

		return summaryResponse;
	}

	@Override
	public void save(List<SummaryRequest> summaryRequest) {

		summaryDAO.saveAll(summaryRequest.stream().map(summary -> {
			
			SummaryEntity summaryEntity = new SummaryEntity();
			BeanUtils.copyProperties(summary, summaryEntity);

			summaryEntity.setDate(Integer.valueOf(transformDate(summaryEntity.getDate(), "MMMM dd, yyyy", "yyyyMMdd")));

			summary.getRegion().forEach(res -> {
				RegionEntity regionEntity = new RegionEntity();
				regionEntity.setName(res.getName());
				regionEntity.setCases(res.getCases());
				summaryEntity.addRegionEntity(regionEntity);
			});

			return summaryEntity;
		}).collect(Collectors.toList()));

	}

	private Integer substructInt(Integer int1, Integer int2) {
		return int1 != null && int2 != null ? int1 - int2 : null;
	}

	private String getCurrentYear() {
		return DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now());
	}

	private String transformDate(Integer date, String dateFormatFrom, String dateFormatTo) {

		if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(dateFormatFrom) && !StringUtils.isEmpty(dateFormatTo)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatFrom);
			try {
				java.util.Date dateValue = simpleDateFormat.parse(String.valueOf(date));
				simpleDateFormat = new SimpleDateFormat(dateFormatTo);
				return simpleDateFormat.format(dateValue);
			} catch (ParseException e) {
				lOGGER.error(e.getMessage());
			}
		}
		return null;
	}

}
