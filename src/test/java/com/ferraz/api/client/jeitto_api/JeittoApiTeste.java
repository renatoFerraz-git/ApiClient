package com.ferraz.api.client.jeitto_api;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class JeittoApiTeste {

    @Test
    public void Api_Jeitto_400(){
        given()
                .when()
                .post("https://us-central1-jeitto-backend.cloudfunctions.net/preapproved_insert")
                .then().body("message", contains("could not process request."))
                .statusCode(400);
    }

    @Test
    public void Api_Jeitto_200(){
        given()
                .contentType("application/json")
                .body("{\"token\": \"84da8s4d8asd8a4sd48asd8\",\"cpf\": \"00681443189\"}")
                .when()
                .post("https://us-central1-jeitto-backend.cloudfunctions.net/preapproved_insert")
                .then().body("message", contains("inserted preapproved lead."))
                .statusCode(200);
    }
}
