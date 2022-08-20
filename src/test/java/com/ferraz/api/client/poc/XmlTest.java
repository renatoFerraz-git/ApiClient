package com.ferraz.api.client.poc;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class XmlTest {

    @Test
    public void conhecendoXml(){
        given()
        .when()
            .get("https://restapi.wcaquino.me/usersXML/3")
        .then()
            .statusCode(200)
            .body("user.name", is("Ana Julia"))
            .body("user.@id", is("3"))
            .body("user.filhos.name.size()", is(2))
            .body("user.filhos.name[0]", is("Zezinho"))
            .body("user.filhos.name", hasItem("Luizinho"))
            .body("user.filhos.name", hasItems("Luizinho", "Zezinho"))
        ;
    }
    @Test
    public void conhecendoXmlRootPath(){
        given()
                .when()
                .get("https://restapi.wcaquino.me/usersXML/3")
                .then()
                .statusCode(200)
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
}
