package com.server.covid.tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.covid.tracker.entity.VaccineSummaryEntity;

@Repository
public interface VaccineSummaryDAO extends JpaRepository<VaccineSummaryEntity, Integer> {
	
	void deleteByDeveloperManufacturer(String developerManufacturer);
	
	@Query(value = "SELECT date_published FROM T_VACCINE ORDER BY date_published DESC LIMIT 1", nativeQuery = true)
	String getLatestPublishedDate();
	
	List<VaccineSummaryEntity> findByDatePublishedOrderByTrialPhaseDesc(String datePublished);

}
