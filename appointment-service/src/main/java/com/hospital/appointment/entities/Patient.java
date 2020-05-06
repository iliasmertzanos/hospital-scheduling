package com.hospital.appointment.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.hospital.appointment.enums.Dicease;

import lombok.Data;

@Entity
@Data
public class Patient {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	@NotNull
	private String name;
	
	@Column
	@NotNull
	private Integer age;
	
	@Column
	private Double weight;
	
	@Column
	private Double height;
	
	@Column
	@NotNull
	private Long insuranceId;
	
	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private Dicease dicease;
	
	@OneToMany(
			fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "patient"
    )
    private List<Appointment> appointment;
	
}
