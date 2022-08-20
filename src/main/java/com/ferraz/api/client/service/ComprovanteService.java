package com.ferraz.api.client.service;

import org.springframework.http.ResponseEntity;

public interface ComprovanteService {
    void gerarComprovante(ResponseEntity<?> response) ;
    void deletarComprovantes();
}
