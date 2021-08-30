package org.tlabs.pma.controller;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HomeControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void versionNumberMatchForHomePage_test() {
		System.out.println("http://localhost:" + port + "/home");
		String responseHTML = restTemplate.getForObject
				("http://localhost:" + port + "/home", String.class);
		
		assertEquals(responseHTML.contains("2.2.2"),true);
	}

}
