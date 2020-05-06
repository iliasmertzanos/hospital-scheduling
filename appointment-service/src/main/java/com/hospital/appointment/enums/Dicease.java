package com.hospital.appointment.enums;

public enum Dicease {
	DIAB_TYP_1("Type 1 Diabetes") 
	,DIAB_TYP_2("Type 2 Diabetes")
	,PNEU("Pneumonia")
	,COVID_19("SARS-COVID19")
	,HEART_ATT("Heart attack")
	,HEART_VALV("Heart valve disease")
	,ASTHMA("Asthma")
	;

	String description;

	Dicease(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}	
	
}
