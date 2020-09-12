package com.server.covid.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.covid.tracker.entity.RegionEntity;

@Repository
public interface RegionDAO extends JpaRepository<RegionEntity, Integer>{

}
