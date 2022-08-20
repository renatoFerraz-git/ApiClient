package com.ferraz.api.client.poc;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HelloTeste {

    @Test
    public void deveReceberStatusCode200_GivenWhenThen() {
        given()        //Condicionais   ( Dado que exista...            )
                .when()//Ações          ( Quando eu fizer algo...       )
                .get("http://restapi.wcaquino.me/ola")
                .then()//Assertivas     ( Então a resposta deverá ser...)
                .statusCode(200);
    }

    @Test
    public void deveReceberOlaMundoEStatusCode200() {
        Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
        Assert.assertEquals("Ola Mundo!", response.getBody().asString());
        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void deveReceberStatusCode200() {
        get("http://restapi.wcaquino.me/ola").then().statusCode(200);
    }

    @Test
    public void deveValidarBody() {
        given()
        .when()
            .get("http://restapi.wcaquino.me/ola")
        .then()
            .statusCode(200)
            .body(Matchers.is(not(nullValue())))
            .body(Matchers.is("Ola Mundo!"))
            .body(Matchers.containsString("Ola"));
    }

    @Test
    public void devoConhecerOsMatchersHamcrast() {

        //TODO Consulte a documentação https://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
        //Os imports de Assert e dos Matchers podem ser staticos
        //Assert são passados, Matchers são esperados
        String nome = "Renato Ferraz";
        Assert.assertThat(nome, Matchers.is("Renato Ferraz"));
        assertThat(nome, is("Renato Ferraz"));
        assertThat(nome, is(not("Renato Pereira")));
        assertThat(nome, not("Renato Pereira"));
        assertThat(nome, isA(String.class));
        assertThat(nome, anyOf(is("Joana Santos"), is("Renato Ferraz"), is("Paula Santos")));
        assertThat(nome, containsString("Ferraz"));

        assertThat(nome, allOf(
                containsString("Ferraz"),
                is("Renato Ferraz"),
                isA(String.class), not("Renato Pereira"),
                anyOf(is("Joana Santos"), is("Renato Ferraz"))
        ));

        Assert.assertThat(70, Matchers.is(70));
        assertThat(70, isA(Integer.class));
        assertThat(70.0, isA(Double.class));
        assertThat(70.0, greaterThan(60.0));
        assertThat(70.0, lessThan(80.0));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        Assert.assertThat(impares, Matchers.contains(1, 3, 5, 7, 9));  // A ordem importe
        assertThat(impares, containsInAnyOrder(3, 1, 7, 5, 9)); // A ordem não importa
        assertThat(impares, hasItem(7));                               // Procura por um item na lista
        assertThat(impares, hasItems(7, 5));                           // Procura por alguns itens na lista
        assertThat(impares, hasSize(5));

    }
}
