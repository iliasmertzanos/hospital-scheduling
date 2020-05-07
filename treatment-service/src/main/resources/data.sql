INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (1, 'PATHOLOGY', 'Hamud', 'Abibi', 'Pathologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (2, 'PATHOLOGY', 'Abdala', 'Abibi', 'Pathologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (3, 'PATHOLOGY', 'Ilias', 'Mertzanidis', 'Pathologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (4, 'CARDIOLOGY', 'John', 'Noman', 'Cargiologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (5, 'CARDIOLOGY', 'Jan', 'Müller', 'Cargiologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (6, 'CARDIOLOGY', 'Hamud', 'Abibi', 'Cargiologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (7, 'ENDOCRINOLOGY', 'Katty', 'Wincelt', 'Endocrinologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (8, 'ENDOCRINOLOGY', 'Judith', 'Müller', 'Endocrinologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (9, 'ENDOCRINOLOGY', 'Ben', 'Völker', 'Endocrinologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (10, 'RESPIROLOGY', 'Katerina', 'Neumann', 'Respirologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (11, 'RESPIROLOGY', 'Vent', 'Junker', 'Respirologist', 8.0);
INSERT INTO doctor (id, clinical_area, first_name, last_name, spezialisation, working_hours) VALUES (12, 'RESPIROLOGY', 'Ben', 'Jaegermann', 'Respirologist', 8.0);


INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (1, 'CARDIOLOGY', 'examination', 'Arrhythmia', 2.0, true, 400.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (2, 'CARDIOLOGY', 'examination', 'HEART_ATT', 1.0, false, 600.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (3, 'CARDIOLOGY', 'examination', 'HEART_VALV', 3.0, true, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (4, 'CARDIOLOGY', 'examination', 'Coronary_artery', 2.0, false, 600.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (5, 'PATHOLOGY', 'examination', 'COVID_19', 1.0, false, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (6, 'PATHOLOGY', 'examination', 'Influ_A', 2.0, true, 20.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (7, 'PATHOLOGY', 'examination', 'Influ_B', 3.0, true, 30.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (8, 'PATHOLOGY', 'examination', 'Influ_C', 2.5, false, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (9, 'ENDOCRINOLOGY', 'examination', 'DIAB_TYP_1', 2.1, false, 20.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (10, 'ENDOCRINOLOGY', 'examination', 'DIAB_TYP_2', 1.5, false, 10.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (11, 'ENDOCRINOLOGY', 'examination', 'Osteoporosis', 3.0, true, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (12, 'ENDOCRINOLOGY', 'examination', 'Addison', 4.5, true, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (13, 'RESPIROLOGY', 'examination', 'PNEU', 1.2, true, 500.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (14, 'RESPIROLOGY', 'examination', 'ASTHMA', 2.3, false, 563.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (15, 'RESPIROLOGY', 'examination', 'bronchitis', 0.5, false, 874.0);
INSERT INTO treatment_catalog (tc_id, clinical_area, description, dicease_code, duration, on_private_expences, treatment_cost) VALUES (16, 'RESPIROLOGY', 'examination', 'chronic_bronchitis', 1.9, true, 20.0);





