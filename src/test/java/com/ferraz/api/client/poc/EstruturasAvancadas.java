package com.ferraz.api.client.poc;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EstruturasAvancadas {

    @Test
    public void estruturasAvancadas(){
        given()
        .when()
            .get("http://restapi.wcaquino.me/users")
        .then()
            .statusCode(200)
            .body("age.findAll{it <= 25}.size()", is(2))
            .body("age.findAll{it > 10 && it < 50}.size()", is(3))
            .body("findAll{it.age > 10 && it.age < 50}.name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
            .body("findAll{it.name.contains('na')}.name", hasItems("Maria Joaquina", "Ana Júlia"))
            .body("findAll{it.name.length() < 7}.name", hasItems())
            .body("findAll{it.name.length() < 10}.name", hasItems("Ana Júlia"))
            .body("findAll{it.name.length() > 10}.name", hasItems("João da Silva", "Maria Joaquina"))
            .body("findAll{it.age < 25}[0].name", is("Ana Júlia"))
            .body("name.collect{it.toUpperCase()}", hasItems("JOÃO DA SILVA","MARIA JOAQUINA","ANA JÚLIA"))
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItems("MARIA JOAQUINA"))
            .body("age.collect{it * 2}", hasItems(60, 50, 40))
            .body("id.max()", is(3))
            .body("salary.min()", is(1234.5678f))
            .body("salary.findAll{it != null}.sum()", allOf(greaterThan(3000d), lessThan(5000d)))
            .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001)))
            .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))
            //TODO Doc: https://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
        ;
    }
    @Test
    public void simplificandoJsonPathComJava(){
        //.body("name.findAll{it.contains('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))
        ArrayList<String> nomesContMaria =
        given()
        .when()
            .get("http://restapi.wcaquino.me/users")
        .then()
            .statusCode(200)
            .extract().path("name.findAll{it.contains('Maria')}")
        ;
        Assert.assertEquals(1, nomesContMaria.size());
        for(String nome : nomesContMaria){
            Assert.assertTrue(nome.equalsIgnoreCase("maria joaquina"));
        }
    }
}
