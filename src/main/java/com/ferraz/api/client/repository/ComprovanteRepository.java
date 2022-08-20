package com.ferraz.api.client.repository;

import com.ferraz.api.client.model.Comprovante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComprovanteRepository extends MongoRepository<Comprovante, Long> {
}
