package com.roche.main.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roche.main.controller.RestController;
import com.roche.main.exception.ExceptionHandling;
import com.roche.main.response.Response;
import com.roche.main.validateIP.ValidateIP;

@Service
public class CountryNameServiceImpl implements CountryNameService {

	private static final Logger log = LoggerFactory.getLogger(CountryNameServiceImpl.class);
	private static final String url = "https://ipvigilante.com/json/";
	private static final String data = "data";
	
	//list of unique names (no repetitions of names) sorted alphabetically
	private static final Set<String> setOfCountires = new TreeSet<String>();

	@Override
	public Response  northernCountries (List<String> ipAddress) {

		if (ipAddress.isEmpty() == true || ipAddress.equals(null) || ipAddress.size() < 1)
			throw new ExceptionHandling("ip is empty or null" + ipAddress);
		if (ipAddress.size() > 5)
			throw new ExceptionHandling("ip is greater than 5 ip addresses" + " " + ipAddress);
		for (String ip : ipAddress) {
			log.info(ip);
			if (ValidateIP.isIp(ip.toString()) == false)
				throw new ExceptionHandling("ip is NOT correct" + "  " + ip.toString());
			if (calllLatitudeService(ip) == false)
				continue;
			// throw new ExceptionHandling("IPAddress is NOT country from the Northern
			// Hemisphere " + ip);

			callCountryName(ip);
		}

		Response results = new Response();
		results.setCountries(setOfCountires);
		return results;

	}

	/**
	 * check if latitude request is positive then country from the northern
	 * hemisphere.
	 * 
	 * @param IPAddress of the country
	 **/

	private boolean calllLatitudeService(String ipAddres) {

		final String latitude = "latitude";

		RestTemplate restTemplate = new RestTemplate();
		String urlLatitude = url + ipAddres + "/latitude";
		log.info("urlLatitude : " + urlLatitude);

		ResponseEntity<String> response = restTemplate.getForEntity(urlLatitude, String.class);
		assert (response.getStatusCode() == HttpStatus.OK);
		log.info(" calllLatitudeService Response Body {} +" + response.getBody());
		JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
		double result = jsonObject.getAsJsonObject(data).get(latitude).getAsDouble();

		if (result < 0) {
			return false;
		}
		return true;

	}

	private void callCountryName(String ipAddres) {

		final String countryName = "country_name";

		RestTemplate restTemplate = new RestTemplate();
		String urlLCountryName = url + ipAddres + "/country_name";
		log.info("urlLCountryName : " + urlLCountryName);

		ResponseEntity<String> response = restTemplate.getForEntity(urlLCountryName, String.class);
		assert (response.getStatusCode() == HttpStatus.OK);
		log.info(" calllLatitudeService Response Body {} +" + response.getBody());
		JsonObject jsonObject = new JsonParser().parse(response.getBody()).getAsJsonObject();
		String result = jsonObject.getAsJsonObject(data).get(countryName).getAsString();
		setOfCountires.add(result);
	}

}
