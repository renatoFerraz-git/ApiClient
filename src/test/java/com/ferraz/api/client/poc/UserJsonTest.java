package com.ferraz.api.client.poc;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserJsonTest {

    @Test
    public void deveVerificarPrimeiroNivel() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users/1")
                .then()
                .statusCode(200)
                .body(is(not(nullValue())))
                .body("id", Matchers.is(1))
                .body("name", Matchers.containsString("Silva"))
                .body("age", Matchers.greaterThan(18))
        ;
    }

    @Test
    public void deveVerificarSegundoNivel() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users/2")
                .then()
                .statusCode(200)
                .body(is(not(nullValue())))
                .body("endereco.numero", is(0))
                .body("endereco.numero", greaterThan(-1))
                .body("endereco.numero", lessThan(1))
                .body("endereco.rua", containsString("bobos"))
        ;
    }

    @Test
    public void deveVerificarlLista() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                .body(is(not(nullValue())))
                .body("filhos", hasSize(2))
                .body("filhos[0].name", is("Zezinho"))
                .body("filhos[0].name", containsString("zinho"))
                .body("filhos.name", hasSize(2))
                .body("filhos.name", hasItem("Zezinho"))
                .body("filhos.name", hasItems("Zezinho", "Luizinho"))
        ;
    }

    @Test
    public void deveRetornarErroUsuarioInexistente() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users/4")
                .then()
                .statusCode(404)
                .body(is(not(nullValue())))
                .body("error", is("Usuário inexistente"))
        ;
    }

    @Test
    public void deveVerificarListaNaRaiz() {
        given()
                .when()
                .get("http://restapi.wcaquino.me/users")
                .then()
                .statusCode(200)
                .body(is(not(nullValue())))
                .body("$", hasSize(3))
                .body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
                .body("age[0]", is(30))
                .body("filhos.name", hasItem(Arrays.asList("Zezinho", "Luizinho")))
                .body("salary", contains(1234.5678f, 2500, null))

        ;

    }

    //TODO Response.............
    @Test
    public void deveVerificarPrimeiroNivelOutrasFormas() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");
        Assert.assertEquals(new Integer(1), response.path("id"));
        Assert.assertEquals(new Integer(30), response.path("age"));
        Assert.assertEquals("João da Silva", response.path("name"));

        int id = response.path("id");
    }


    //TODO JsonPath.............
    @Test
    public void deveVerificarPrimeiroNivel_JsonPath() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");

        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));
        Assert.assertEquals(30, jpath.getInt("age"));
        Assert.assertEquals("João da Silva", jpath.getString("name"));

        String name = JsonPath.from(response.asString()).getString("name");
        Assert.assertEquals("João da Silva", name);
        Assert.assertTrue(name.contains("Silva"));
    }

    @Test
    public void deveVerificarSegundoNivel_JsonPath() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/2");
        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals("Rua dos bobos", jpath.getString("endereco.rua"));
        Assert.assertEquals(0, jpath.getInt("endereco.numero"));
    }

    @Test
    public void deveVerificarLista_JsonPath() {
        Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/3");
        JsonPath jpath = new JsonPath(response.asString());
        List<String> filhos = response.path("filhos");
        Assert.assertEquals(2, filhos.size());
        Assert.assertEquals("Zezinho", jpath.getString("filhos[0].name"));
        Assert.assertEquals("Luizinho", jpath.getString("filhos[1].name"));
        String comp = response.getBody().asString();
        System.out.println(comp);
        //comprovante.gerarComprovante(response.getBody().asString());
    }
}
