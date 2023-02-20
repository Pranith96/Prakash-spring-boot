package com.user.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private Integer plotNo;
	private String streetNo;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String pincode;

	
	public Address(Integer addressId, Integer plotNo, String streetNo, String locality, String city, String state,
			String country, String pincode) {
		this.addressId = addressId;
		this.plotNo = plotNo;
		this.streetNo = streetNo;
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public Address() {
	}


	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(Integer plotNo) {
		this.plotNo = plotNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
