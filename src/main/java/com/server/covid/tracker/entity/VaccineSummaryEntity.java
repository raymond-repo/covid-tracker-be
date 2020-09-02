package com.server.covid.tracker.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "T_VACCINE")
public class VaccineSummaryEntity implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String developerManufacturer;

	private String trialPhase;

	private String datePublished;

	public VaccineSummaryEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeveloperManufacturer() {
		return developerManufacturer;
	}

	public void setDeveloperManufacturer(String developerManufacturer) {
		this.developerManufacturer = developerManufacturer;
	}

	public String getTrialPhase() {
		return trialPhase;
	}

	public void setTrialPhase(String trialPhase) {
		this.trialPhase = trialPhase;
	}

	public String getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}

}
