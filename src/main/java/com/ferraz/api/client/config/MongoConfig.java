package com.ferraz.api.client.config;

import com.ferraz.api.client.model.Comprovante;
import com.ferraz.api.client.repository.ComprovanteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = ComprovanteRepository.class)
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunner(ComprovanteRepository comprovanteRepository) {
        return strings -> {
            comprovanteRepository.save(new Comprovante( "TesteComprovante: ", "00/00/0000"));
        };
    }
}