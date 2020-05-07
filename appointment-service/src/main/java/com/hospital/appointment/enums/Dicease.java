package com.hospital.appointment.enums;

public enum Dicease {
	//ENDOCRINOLOGY
	DIAB_TYP_1("Type 1 Diabetes") 
	,DIAB_TYP_2("Type 2 Diabetes")
	,Osteoporosis("Osteoporosis")
	,Addison("Addison\'s Disease")
	//RESPIROLOGY
	,PNEU("Pneumonia")
	,ASTHMA("Asthma")
	,bronchitis("bronchitis")
	,chronic_bronchitis("chronic bronchitis")
	//PATHOLOGY
	,COVID_19("SARS-COVID19")
	,Influ_A("Influenzavirus_A")
	,Influ_B("Influenzavirus_B")
	,Influ_C("Influenzavirus_C")
	,Influ_D("Influenzavirus_D")
	//CARDIOLOGY
	,Arrhythmia("Arrhythmia")
	,HEART_ATT("Heart attack")
	,HEART_VALV("Heart valve disease")
	,Coronary_artery("Coronary artery disease")
	,Myocardial_infarction("Myocardial infarction")
	
	;

	String description;

	Dicease(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}	
	
}
