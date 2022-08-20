package com.ferraz.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // entidade
@Document(collation ="comprovante_api_teste") //Nome da tabela
public class Comprovante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JsonProperty("Registro")
    private String registro;
    @JsonProperty("Data")
    private String data;
    public Comprovante(String registro, String data) {
        this.registro = registro;
        this.data = data;
    }
}
