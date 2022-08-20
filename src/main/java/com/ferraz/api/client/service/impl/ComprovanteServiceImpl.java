package com.ferraz.api.client.service.impl;

import com.ferraz.api.client.model.Comprovante;
import com.ferraz.api.client.repository.ComprovanteRepository;
import com.ferraz.api.client.service.ComprovanteService;
import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ComprovanteServiceImpl implements ComprovanteService {
    @Autowired
    ComprovanteRepository comprovanteRepository;
    Gson gson = new Gson();

    public void gerarComprovante(ResponseEntity<?> response) {

        try {
            String log = gson.toJson(response);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = formatter.format(new Date());
            Comprovante comprovanteJeitto = new Comprovante(log, data);
            comprovanteRepository.save(comprovanteJeitto);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletarComprovantes() {
        comprovanteRepository.deleteAll();
    }
}