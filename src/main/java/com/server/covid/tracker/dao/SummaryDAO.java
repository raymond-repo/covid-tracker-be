package com.server.covid.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.covid.tracker.entity.SummaryEntity;

@Repository
public interface SummaryDAO extends JpaRepository<SummaryEntity, Integer>{
	
	@Query(value = "SELECT date FROM t_summary ORDER BY date DESC LIMIT 1", nativeQuery = true)
	Integer getLatestDate();
	
	@Query(value = "SELECT date FROM t_summary WHERE SUBSTRING(date, 1, 6) = :date ORDER BY date DESC LIMIT 1", nativeQuery = true)
	Integer getAvailableLatestDate(String date);
	
	@Query("SELECT totalCases FROM t_summary WHERE date=:date")
	Integer getTotalCases(Integer date);
	
	@Query("SELECT recoveries FROM t_summary WHERE date=:date")
	Integer getTotalRecovered(Integer date);
	
	@Query("SELECT totalDeaths FROM t_summary WHERE date=:date")
	Integer getTotalDeaths(Integer date);

}
