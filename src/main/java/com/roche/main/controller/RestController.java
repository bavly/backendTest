package com.roche.main.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roche.main.exception.ExceptionHandling;
import com.roche.main.response.Response;
import com.roche.main.service.CountryNameService;
import com.roche.main.validateIP.ValidateIP;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	 private final CountryNameService countryNameService;;

	    public RestController(CountryNameService countryNameService) {
	        this.countryNameService = countryNameService;
	}
	

	private static final Logger log = LoggerFactory.getLogger(RestController.class);

	@ApiResponses(value = 
		{ @ApiResponse(code = 200, message = "Request processed successfully"),
		  @ApiResponse(code = 400, message = "Identifier to resource does not exist.") 
		})
	@GetMapping(value = "/northcountries", headers = "Accept=application/json")
	public ResponseEntity<Response>  processData(@RequestParam("ip") final List<String> ipAddress) {
		try {
		
		 return new ResponseEntity<Response>(countryNameService.checkIPAddress(ipAddress), HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("CountryName: error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
