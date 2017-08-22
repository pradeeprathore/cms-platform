package com.ascent.cms.core.vo;

public class StatisticsVO {

	
	private Long totalNewPatient;
	
	private Long totalNewMedicalCase;
	
	private Long totalPatient;
	
	private Long totalNewVisit;
	
	private Double totalFee;

	public Long getTotalNewPatient() {
		return totalNewPatient;
	}

	public void setTotalNewPatient(Long totalNewPatient) {
		this.totalNewPatient = totalNewPatient;
	}

	public Long getTotalPatient() {
		return totalPatient;
	}

	public void setTotalPatient(Long totalPatient) {
		this.totalPatient = totalPatient;
	}

	public Long getTotalNewMedicalCase() {
		return totalNewMedicalCase;
	}

	public void setTotalNewMedicalCase(Long totalNewMedicalCase) {
		this.totalNewMedicalCase = totalNewMedicalCase;
	}

	public Long getTotalNewVisit() {
		return totalNewVisit;
	}

	public void setTotalNewVisit(Long totalNewVisit) {
		this.totalNewVisit = totalNewVisit;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	
	
}
