package com.ferraz.api.client.the_cat;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.ferraz.api.client.MessageUtil.bodyCatApi;
import static com.ferraz.api.client.MessageUtil.urlCatApi;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TheCatApiSignUpTeste {

    @Test
    public void deveConseguirReceberUmTokenNoEmailInformado() {
        //TODO https://www.4devs.com.br/gerador_de_empresas ( gerar novo email )
        Response response = given()
                .contentType("application/json").body(bodyCatApi)
                .when().post(urlCatApi);
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("Response: " + response.body().asString());
    }

}
