package com.ferraz.api.client.poc;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Hello {
    public static void main(String[] args) {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
        System.out.println("response = " + response.getBody().asString());
        ValidatableResponse validate = response.then().statusCode(201);
    }
}
