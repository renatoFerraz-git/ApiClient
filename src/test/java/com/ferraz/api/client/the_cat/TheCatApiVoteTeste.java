package com.ferraz.api.client.the_cat;

import com.ferraz.api.client.model.Comprovante;
import com.ferraz.api.client.request.CatVoteRequest;
import com.ferraz.api.client.response.CatVoteResponse;
import com.ferraz.api.client.service.ComprovanteService;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static com.ferraz.api.client.MessageUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TheCatApiVoteTeste {

    @Autowired
    ComprovanteService comprovante;

    Gson gson = new Gson();
    @Test
    public void naoDeveVotarSemPassarUmToken() {

        CatVoteRequest catVoteRequest = new CatVoteRequest("8krfAgKYD", true, "demo-ab4d47");

        Response response = given().contentType("application/json").body(catVoteRequest)
                .when().post(urlCatApiVote);
        response.then()
                .body("message", containsString("AUTHENTICATION_ERROR"))
                .statusCode(401);

        int status = response.getStatusCode();
        String message = JsonPath.from(response.asString()).getString("message");
        String level = JsonPath.from(response.asString()).getString("level");
        CatVoteResponse catVoteResponse = new CatVoteResponse(message, status, level);

        response.then()
                .body("message", containsString(message))
                .statusCode(status);

        System.out.println("Response: " + response.body().asString());
        System.out.println("Response: " + gson.toJson(catVoteResponse));
        //comprovante.gerarComprovante(ResponseEntity.ok(catVoteResponse));
    }


    @Test
    public void naoDeveVotarComUmTokenInvalido() {
        Response response = given().contentType("application/json").body(bodyCatApiVote)
                .when().post(urlCatApiVote + "?api_key=" + tokenCatApiInvalid);
        response.then().body("message", containsString("AUTHENTICATION_ERROR")).statusCode(401);
        System.out.println("Response: " + response.body().asString());
    }

    @Test
    public void deveVotarSemErrosComUmTokenValido() {
        Response response = given().contentType("application/json").body(bodyCatApiVote)
                .when().post(urlCatApiVote + "?api_key=" + tokenCatApiValid);
        response.then().body("message", containsString("SUCCESS")).statusCode(201);
        System.out.println("response.asString() = " + response.asString());
    }

    @Test
    public void deveDeletarUmaVotacaoPorId() {
        String id = voteReturnId();
        Response response = given().contentType("application/json")
                .when().delete(urlCatApiVote + id + "?api_key=" + tokenCatApiValid);
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("response.asString() = " + response.asString());
    }

    @Test
    public void deveDeletarUmaVotacaoPorIdComHeader() {
        String id = voteReturnId();
        Response response = given().contentType("application/json").header("x-api-key", tokenCatApiValid).pathParam("vote_id", id)
                .when()/*.auth().basic(username, password); TODO Autenticação!*/.delete(urlCatApiVote + "{vote_id}");
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("response.asString() = " + response.asString());
    }

    @Test
    public void naoDeveDeletarUmaVotacaoPorFaltaDoParametroId() {
        Response response = given().contentType("application/json")
                .when().delete(urlCatApiVote + "?api_key=" + tokenCatApiValid);
        response.then().body("message", containsString(httpErrorMethodNotAllowed)).statusCode(405);
        System.out.println("response.asString() = " + response.asString());

    }

    @Test
    public void naoDeveDeletarUmaVotacaoPorParametroIdInvalido() {
        Response response = given().contentType("application/json")
                .when().delete(urlCatApiVote + "123456" + "?api_key=" + tokenCatApiValid);
        response.then().body("message", containsString("INVALID_ACCOUNT")).statusCode(400);
        System.out.println("response.asString() = " + response.asString());
    }

    //-----------------------------------------------------------------------------------------
    public String voteReturnId() {
        Response response = given().contentType("application/json").body(bodyCatApiVote)
                .when().post(urlCatApiVote + "?api_key=" + tokenCatApiValid);
        return response.jsonPath().getString("id");
    }
}
