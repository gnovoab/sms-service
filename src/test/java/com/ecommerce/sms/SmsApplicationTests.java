package com.ecommerce.sms;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmsApplicationTests {

	@Autowired
	private transient TestRestTemplate restTemplate;

	@Test
	void contextLoads() { }

	@Test
	public void healthOk() {
		ResponseEntity<JsonNode> entity = restTemplate.getForEntity("/actuator/health", JsonNode.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

}
