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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.server.covid.tracker.dao.CasesDAO;
import com.server.covid.tracker.dto.request.RegionRequest;
import com.server.covid.tracker.dto.response.CasesResponse;
import com.server.covid.tracker.entity.CasesEntity;
import com.server.covid.tracker.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

	private static final Logger lOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

	@Autowired
	private CasesDAO regionDAO;

	@Override
	public List<CasesResponse> getAllRegion() {
		return regionDAO.findAll().stream().map(res -> {
			CasesResponse regionResponse = new CasesResponse();
			regionResponse.setName(res.getName());
			regionResponse.setTotalCase(res.getTotalCase());
			regionResponse.setNewCase(res.getNewCase());
			regionResponse.setActiveCase(res.getActiveCase());
			regionResponse.setRecovered(res.getRecovered());
			regionResponse.setDied(res.getDied());
			regionResponse.setDatePublished(transformDate(res.getDatePublished(), "MMMM dd, yyyy", "yyyyMMdd"));

			return regionResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public void saveRegion(RegionRequest regionRequest) {

		CasesEntity regionEntity = new CasesEntity();
		regionEntity.setName(regionRequest.getName());
		regionEntity.setNewCase(regionRequest.getNewCase());
		regionEntity.setActiveCase(regionRequest.getActiveCase());
		regionEntity.setRecovered(regionRequest.getRecovered());
		regionEntity.setDied(regionRequest.getDied());

		regionEntity.setTotalCase(isNotNullElseZero(regionDAO.getTotalCasesByRegion(regionRequest.getName()))
				+ regionRequest.getNewCase());

		regionEntity.setDatePublished(transformDate(regionRequest.getDatePublished(), "MMMM dd, yyyy", "yyyyMMdd"));

		regionDAO.save(regionEntity);
	}

	@Override
	public void saveBulkRegion(List<RegionRequest> regionRequestList) {
		regionDAO.saveAll(regionRequestList.stream().map(regionRequest -> {
			CasesEntity regionEntity = new CasesEntity();
			regionEntity.setName(regionRequest.getName());
			regionEntity.setNewCase(regionRequest.getNewCase());
			regionEntity.setActiveCase(regionRequest.getActiveCase());
			regionEntity.setRecovered(regionRequest.getRecovered());
			regionEntity.setDied(regionRequest.getDied());

			regionEntity.setTotalCase(isNotNullElseZero(regionDAO.getTotalCasesByRegion(regionRequest.getName()))
					+ regionRequest.getNewCase());

			regionEntity.setDatePublished(transformDate(regionRequest.getDatePublished(), "MMMM dd, yyyy", "yyyyMMdd"));

			return regionEntity;
		}).collect(Collectors.toList()));

	}

	@Override
	public List<CasesResponse> getRegionByName(String name) {
		return regionDAO.findByName(name).stream().map(regionEntity -> {
			CasesResponse regionResponse = new CasesResponse();
			regionResponse.setName(regionEntity.getName());
			regionResponse.setTotalCase(regionEntity.getTotalCase());
			regionResponse.setNewCase(regionEntity.getNewCase());
			regionResponse.setActiveCase(regionEntity.getActiveCase());
			regionResponse.setRecovered(regionEntity.getRecovered());
			regionResponse.setDied(regionEntity.getDied());
			regionResponse
					.setDatePublished(transformDate(regionEntity.getDatePublished(), "yyyyMMdd", "MMMM dd, yyyy"));

			return regionResponse;
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteByName(String name) {
		regionDAO.deleteByName(name);
	}

	@Override
	public CasesResponse getRegionByNameAndLatestPublishedDate(String name) {

		CasesResponse regionResponse = new CasesResponse();

		CasesEntity regionEntity = regionDAO.findByNameOrderByDatePublishedDesc(name) != null
				? regionDAO.findByNameOrderByDatePublishedDesc(name).get(0)
				: null;

		if (regionEntity != null) {
			regionResponse.setName(regionEntity.getName());
			regionResponse.setTotalCase(regionEntity.getTotalCase());
			regionResponse.setNewCase(regionEntity.getNewCase());
			regionResponse.setActiveCase(regionEntity.getActiveCase());
			regionResponse.setRecovered(regionEntity.getRecovered());
			regionResponse.setDied(regionEntity.getDied());
			regionResponse
					.setDatePublished(transformDate(regionEntity.getDatePublished(), "yyyyMMdd", "MMMM dd, yyyy"));

			List<Integer> totalCasesTimeline = new ArrayList<>();
			List<Integer> recoveredTimeline = new ArrayList<>();
			List<Integer> diedTimeline = new ArrayList<>();

			List<CasesEntity> casesEntityList = regionDAO.findByName(name);
			for (int i = 1; i <= 12; i++) {

				String date = getCurrentYear() + String.format("%02d", i);

				totalCasesTimeline.add(casesEntityList.stream()
						.filter(casesEntity -> date.substring(0, 6)
								.equals(casesEntity.getDatePublished().substring(0, 6)))
						.map(CasesEntity::getTotalCase).mapToInt(Integer::intValue).sum());

				recoveredTimeline.add(casesEntityList.stream()
						.filter(casesEntity -> date.substring(0, 6)
								.equals(casesEntity.getDatePublished().substring(0, 6)))
						.map(CasesEntity::getRecovered).mapToInt(Integer::intValue).sum());

				diedTimeline.add(casesEntityList.stream()
						.filter(casesEntity -> date.substring(0, 6)
								.equals(casesEntity.getDatePublished().substring(0, 6)))
						.map(CasesEntity::getDied).mapToInt(Integer::intValue).sum());
			}

			regionResponse.setTotalCasesTimeline(totalCasesTimeline);
			regionResponse.setRecoveredTimeline(recoveredTimeline);
			regionResponse.setDiedTimeline(diedTimeline);
		}

		return regionResponse;
	}

	@Override
	public CasesResponse getSummary() {

		CasesResponse casesResponse = new CasesResponse();

		String latestPublishedDate = regionDAO.getLatestPublishedDate();

		List<CasesEntity> listOfRegionEntity = regionDAO.findAll();

		Integer newCases = listOfRegionEntity.stream().filter(res -> latestPublishedDate.equals(res.getDatePublished()))
				.map(CasesEntity::getNewCase).mapToInt(Integer::intValue).sum();
		Integer totalCases = listOfRegionEntity.stream().map(CasesEntity::getTotalCase).mapToInt(Integer::intValue)
				.sum();
		Integer activeCase = listOfRegionEntity.stream().map(CasesEntity::getActiveCase).mapToInt(Integer::intValue)
				.sum();
		Integer recovered = listOfRegionEntity.stream().map(CasesEntity::getRecovered).mapToInt(Integer::intValue)
				.sum();
		Integer died = listOfRegionEntity.stream().map(CasesEntity::getDied).mapToInt(Integer::intValue).sum();

		casesResponse.setDatePublished(transformDate(latestPublishedDate, "yyyyMMdd", "MMMM dd, yyyy"));
		casesResponse.setNewCase(newCases);
		casesResponse.setTotalCase(totalCases);
		casesResponse.setActiveCase(activeCase);
		casesResponse.setRecovered(recovered);
		casesResponse.setDied(died);

		List<Integer> totalCasesTimeline = new ArrayList<>();
		List<Integer> recoveredTimeline = new ArrayList<>();
		List<Integer> diedTimeline = new ArrayList<>();

		List<CasesEntity> casesEntityList = regionDAO.findAll();
		for (int i = 1; i <= 12; i++) {

			String date = getCurrentYear() + String.format("%02d", i);

			totalCasesTimeline.add(casesEntityList.stream()
					.filter(casesEntity -> date.substring(0, 6).equals(casesEntity.getDatePublished().substring(0, 6)))
					.map(CasesEntity::getTotalCase).mapToInt(Integer::intValue).sum());

			recoveredTimeline.add(casesEntityList.stream()
					.filter(casesEntity -> date.substring(0, 6).equals(casesEntity.getDatePublished().substring(0, 6)))
					.map(CasesEntity::getRecovered).mapToInt(Integer::intValue).sum());

			diedTimeline.add(casesEntityList.stream()
					.filter(casesEntity -> date.substring(0, 6).equals(casesEntity.getDatePublished().substring(0, 6)))
					.map(CasesEntity::getDied).mapToInt(Integer::intValue).sum());
		}

		casesResponse.setTotalCasesTimeline(totalCasesTimeline);
		casesResponse.setRecoveredTimeline(recoveredTimeline);
		casesResponse.setDiedTimeline(diedTimeline);

		return casesResponse;
	}

	private String getCurrentYear() {
		return DateTimeFormatter.ofPattern("yyyy").format(LocalDateTime.now());
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

	private Integer isNotNullElseZero(Integer value) {
		return value != null ? value : 0;
	}

}
