package com.server.covid.tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.covid.tracker.dto.TimelineData;
import com.server.covid.tracker.entity.CasesEntity;

@Repository
public interface CasesDAO extends JpaRepository<CasesEntity, Integer>{
	
	List<CasesEntity> findByName(String name);
	
	void deleteByName(String name);
	
	@Query(value = "SELECT SUM(total_case) as totalCasesTimeline, "
			+ "SUM(recovered) as recoveredTimeline, "
			+ "SUM(died) as diedTimeline"
			+ "from T_CASES WHERE SUBSTRING(date_published, 1, 6)=:yearMonth", nativeQuery = true)
	TimelineData getTotalCasesByMonth(String yearMonth);
	
//	@Query("SELECT new com.server.covid.tracker.dto.TimelineData(SUM(total_case), SUM(recovered), SUM(died)) "
//			+ "from T_CASES WHERE SUBSTRING(date_published, 1, 6)=:yearMonth AND name=:regionName")
//	TimelineData getTotalCasesByMonthAndRegionName(String yearMonth, String regionName);
	
	@Query("SELECT SUM(totalCase) AS totalCase FROM T_CASES WHERE name = :region")
	Integer getTotalCasesByRegion(String region);
	
	@Query(value = "SELECT date_published FROM T_CASES ORDER BY date_published DESC LIMIT 1", nativeQuery = true)
	String getLatestPublishedDate();
	
	List<CasesEntity> findByNameOrderByDatePublishedDesc(String name);

}
