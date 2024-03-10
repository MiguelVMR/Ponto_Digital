package com.ponto.ponto_digital.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ponto.ponto_digital.model.Schemas.MarcarPontoSchema;

@Repository
public interface MarcarPontoRepository extends JpaRepository<MarcarPontoSchema,UUID>{
    
}
