package com.ferraz.api.client;

import com.ferraz.api.client.model.Comprovante;
import com.ferraz.api.client.repository.ComprovanteRepository;
import com.ferraz.api.client.service.ComprovanteService;
import com.ferraz.api.client.service.impl.ComprovanteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {

    public static ComprovanteServiceImpl comprovante = new ComprovanteServiceImpl();
    @Autowired
    private static ComprovanteService comp;

    public static void main(String[] args) {

//        try {
//            comp.gerarComprovante("teste");
//        }catch (Exception e){
//            comprovante.gerarComprovante("teste");
//            e.printStackTrace();
//        }

    }
}
