package com.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "addressid_generator")
	@SequenceGenerator(name="addressid_generator",initialValue=101,allocationSize=1)
	private int addressId;
	private int houseNumber;
	private String area;
	private String city;
	private String state;
	private String country;
	private long pincode;
}
