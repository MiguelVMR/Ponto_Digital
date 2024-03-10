package com.ponto.ponto_digital.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ponto.ponto_digital.model.Schemas.MarcarPontoSchema;
import com.ponto.ponto_digital.model.Schemas.UsuarioSchema;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioSchema,UUID>{

    @Query("SELECT u.marcarPontoSchema FROM UsuarioSchema u WHERE u.id = :id")
    Page<MarcarPontoSchema> findPontosByUsuarioId(@Param("id") UUID id, @Param("pageable") Pageable pageable);

    
}
