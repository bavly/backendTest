package com.roche.main.response;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class Response {
	
	@ApiModelProperty
	@JsonProperty(value = "northcountries")
	Set<String> countries = new TreeSet<>();

	/**
	 * @return the countries
	 */
	public Set<String> getCountries() {
		return countries;
	}

	/**
	 * @param countries the countries to set
	 */
	public void setCountries(Set<String> countries) {
		this.countries = countries;
	}

	public Response() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Response [countries=" + countries + "]";
	}

	

}
