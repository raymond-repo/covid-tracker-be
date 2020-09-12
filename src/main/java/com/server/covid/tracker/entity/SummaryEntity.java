package com.server.covid.tracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "t_summary")
public class SummaryEntity {

	@Id
	private Integer date;

	private Integer newCase;

	private Integer totalCases;

	private Integer newDeaths;

	private Integer totalDeaths;

	private Integer active;

	private Integer recoveries;

	@OneToMany(mappedBy = "summaryEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RegionEntity> regionEntity;

	public SummaryEntity() {
		this.regionEntity = new ArrayList<>();
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getNewCase() {
		return newCase;
	}

	public void setNewCase(Integer newCase) {
		this.newCase = newCase;
	}

	public Integer getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(Integer totalCases) {
		this.totalCases = totalCases;
	}

	public Integer getNewDeaths() {
		return newDeaths;
	}

	public void setNewDeaths(Integer newDeaths) {
		this.newDeaths = newDeaths;
	}

	public Integer getTotalDeaths() {
		return totalDeaths;
	}

	public void setTotalDeaths(Integer totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getRecoveries() {
		return recoveries;
	}

	public void setRecoveries(Integer recoveries) {
		this.recoveries = recoveries;
	}

	public List<RegionEntity> getRegionEntity() {
		return regionEntity;
	}

	public void setRegionEntity(List<RegionEntity> regionEntity) {
		this.regionEntity = regionEntity;
	}

	public void addRegionEntity(RegionEntity regionEntity) {
		this.regionEntity.add(regionEntity);
	}

}
