package com.server.covid.tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.server.covid.tracker.entity.QuarantineEntity;

@Repository
public interface QuarantineDAO extends JpaRepository<QuarantineEntity, Integer>{
	
	@Query(value = "SELECT date FROM t_quarantine ORDER BY date DESC LIMIT 1", nativeQuery = true)
	String getLatestDate();
	
	List<QuarantineEntity> findAllByDate(String date);
}
