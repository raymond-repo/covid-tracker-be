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

import com.server.covid.tracker.dao.QuarantineDAO;
import com.server.covid.tracker.dto.request.QuarantineRequest;
import com.server.covid.tracker.dto.response.QuarantineListResponse;
import com.server.covid.tracker.dto.response.QuarantineResponse;
import com.server.covid.tracker.entity.QuarantineEntity;
import com.server.covid.tracker.service.QuarantineService;

@Service
public class QuarantineServiceImpl implements QuarantineService {

	private static final Logger lOGGER = LoggerFactory.getLogger(SummaryServiceImpl.class);

	@Autowired
	private QuarantineDAO quarantineDAO;

	@Override
	public QuarantineResponse getLatestQuarantineAreas() {

		QuarantineResponse quarantineResponse = new QuarantineResponse();
		
		String latestDate = quarantineDAO.getLatestDate();
		quarantineResponse.setDate(transformDate(String.valueOf(latestDate), "yyyyMMdd", "MMMM dd, yyyy"));

		List<QuarantineEntity> listOfQuarantineEntity = quarantineDAO.findAllByDate(latestDate);

		quarantineResponse.setQuarantineListResponse(listOfQuarantineEntity.stream().map(res -> {
			QuarantineListResponse quarantineListResponse = new QuarantineListResponse();

			BeanUtils.copyProperties(res, quarantineListResponse);
			
			quarantineListResponse.setStartDate(transformDate(res.getStartDate(), "yyyyMMdd", "dd-MMM-yy"));
			quarantineListResponse.setDueDate(transformDate(res.getDueDate(), "yyyyMMdd", "dd-MMM-yy"));

			return quarantineListResponse;
		}).collect(Collectors.toList()));

		return quarantineResponse;
	}

	@Override
	public void save(List<QuarantineRequest> quarantineRequest) {

		quarantineDAO.saveAll(quarantineRequest.stream().map(res -> {
			QuarantineEntity quarantineEntity = new QuarantineEntity();

			BeanUtils.copyProperties(res, quarantineEntity);
			quarantineEntity.setStartDate(transformDate(res.getStartDate(), "MMMM dd, yyyy", "yyyyMMdd"));
			quarantineEntity.setDueDate(transformDate(res.getDueDate(), "MMMM dd, yyyy", "yyyyMMdd"));
			
			return quarantineEntity;
		}).collect(Collectors.toList()));

	}

	private String transformDate(String date, String dateFormatFrom, String dateFormatTo) {

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
