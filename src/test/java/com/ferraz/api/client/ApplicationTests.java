package com.ferraz.api.client;

import com.ferraz.api.client.service.ComprovanteService;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


class ApplicationTests {

		@Autowired
		ComprovanteService comprovante;


	@Test
	void apiJeitto() {

		Response response = given()
				.contentType("application/json").body("{\"example\": \"0000\", \"example\": \"true\"}")
				.when()
				.post("https://us-central1-jeitto-backend.cloudfunctions.net/preapproved_insert/example/example/?api_key=example");
		response.then().body("message", containsString("SUCCESS")).statusCode(200);





	}

}
