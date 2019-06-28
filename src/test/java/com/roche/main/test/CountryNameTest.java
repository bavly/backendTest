package com.roche.main.test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.roche.main.MainClass;
import com.roche.main.controller.RestController;
import com.roche.main.service.CountryNameService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainClass.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CountryNameTest {

	private final static String url = "http://localhost:8888/";

	@Autowired
	private CountryNameService countryNameService;

	private MockMvc countryNameMockMvc;

	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);

		final RestController northCountriesResource = new RestController (countryNameService);
		this.countryNameMockMvc = MockMvcBuilders.standaloneSetup(northCountriesResource).build();
	}

	@Test
	public void testGetCountriesByIPAddress() throws Exception {
		countryNameMockMvc.perform(get(url + "northcountries?ip=1.1.1.1&ip=2.2.2.2")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.northcountries").value(hasItem("France")));
	}
	
	@Test
	public void testGetCountriesWrongIpAddress() throws Exception {
		countryNameMockMvc.perform(get(url + "/northcountries?ip=6522.33.3"))
		.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetCountriesByIPAddressMoreThanFiveIPAdress() throws Exception {
		countryNameMockMvc.perform(get(url + "northcountries?ip=1.1.1.1&ip=2.2.2.2&ip=3.3.3.3&ip=4.4.4.4&ip=5.5.5.5&ip=3.3.3.3&ip=4.4.4.4&ip=5.5.5.5"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testGetCountriesNoIPAddress() throws Exception {
		countryNameMockMvc.perform(get(url + "/northcountries"))
		.andExpect(status().isBadRequest());
	}



}
