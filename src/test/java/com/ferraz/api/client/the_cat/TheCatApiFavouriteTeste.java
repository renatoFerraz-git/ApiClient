package com.ferraz.api.client.the_cat;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static com.ferraz.api.client.MessageUtil.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TheCatApiFavouriteTeste {

    @Test
    public void deveDeletarFavouritPorId() {
        String id = favouritReturnId();
        Response response = given().contentType("application/json")
                .when().delete(urlCatApiFavourit + id + "?api_key=" + tokenCatApiValid);
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("response.asString() = " + response.asString());
    }

    @Test
    public void deveDeletarUmFavouritePorIdComHeader() {
        String id = favouritReturnId();
        Response response = given().contentType("application/json").header("x-api-key", tokenCatApiValid).pathParam("favourite_id", id)
                .when().delete(urlCatApiFavourit + "{favourite_id}");
        response.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("response.asString() = " + response.asString());
    }

    @Test
    public void deveFavoritarImagemCorretamente_depoisNaoDeveFavoritarDuplicada_depoisDeveDeletarOFavourities() {
        Response favourite = given().contentType("application/json").body(bodyCatApiFavourite)
                .when().post(urlCatApiFavourit + "?api_key=" + tokenCatApiValid);
        favourite.then().body("message", containsString("SUCCESS")).statusCode(200);
        String id = favourite.jsonPath().getString("id");

        Response duplicate = given().contentType("application/json").body(bodyCatApiFavourite)
                .when().post(urlCatApiFavourit + "?api_key=" + tokenCatApiValid);
        duplicate.then().body("message", containsString(dubpicateFavourite)).statusCode(400);

        Response delete = given().contentType("application/json")
                .when().delete(urlCatApiFavourit + id + "?api_key=" + tokenCatApiValid);
        delete.then().body("message", containsString("SUCCESS")).statusCode(200);
        System.out.println("favourite = " + favourite.asString());
    }

    //-----------------------------------------------------------------------------------------
    public String favouritReturnId() {
        Response response = given().contentType("application/json").body(bodyCatApiFavourite)
                .when().post(urlCatApiFavourit + "?api_key=" + tokenCatApiValid);
        return response.jsonPath().getString("id");
    }
}
