package com.roche.main.service;

import java.util.List;
import java.util.Set;

import com.roche.main.response.Response;

public interface CountryNameService {

	/**
	 * This method check IP address is correct & not more than 5 IP address 
	 * & IPaddress format is correct , latitude value is positive.
	 * return the names of northernCountries from the IP address of the countries
	 * 
	 * @param IP address of the countries
	 * @return Name of countries from List of IP address releated to
	 *         northernCountries
	 */

	Response northernCountries(List<String> ipAddress);

}
