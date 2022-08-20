package com.ferraz.api.client.poc;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseUriTeste {

    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;

    @BeforeClass
    public static void setup(){
        //RestAssured.port = 8081;
        //RestAssured.basePath = "/v2";
        RestAssured.baseURI = "https://restapi.wcaquino.me";

        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.log(LogDetail.ALL);
        reqSpec = requestBuilder.build();

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        resSpec = responseBuilder.build();

        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = resSpec;
    }

    @Test
    public void conhecendoXml() {

        given()
        .when()
            .get("/usersXML/3")
        .then()
        .rootPath("user")
            .body("name", is("Ana Julia"))
            .body("@id", is("3"))

        .rootPath("user.filhos")
            .body("name.size()", is(2))
            .body("name[0]", is("Zezinho"))

        .detachRootPath("filhos")
            .body("filhos.name", hasItem("Luizinho"))
            .body("filhos.name", hasItems("Luizinho", "Zezinho"))

        .appendRootPath("filhos")
            .body("name", hasItem("Luizinho"))
            .body("name", hasItems("Luizinho", "Zezinho"))
        ;
    }

    @Test
    public void requestSpecBuilder() {

        given()
        .when()
            .get("/usersXML/3")
        .then()
        ;
    }

}
