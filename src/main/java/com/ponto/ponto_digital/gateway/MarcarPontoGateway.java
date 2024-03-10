package com.ponto.ponto_digital.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ponto.ponto_digital.model.Schemas.MarcarPontoSchema;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.model.repository.MarcarPontoRepository;
import com.ponto.ponto_digital.utils.Mapper;

@Component
public class MarcarPontoGateway {
    private final Mapper mapper = new Mapper();

    private final MarcarPontoRepository marcarPontoRepository;

    @Autowired
    public MarcarPontoGateway(MarcarPontoRepository marcarPontoRepository) {
        this.marcarPontoRepository = marcarPontoRepository;
    }

    public MarcarPonto baterPonto(MarcarPonto marcarPonto){
        return mapper.converter(marcarPontoRepository
        .save(mapper.converter(marcarPonto, MarcarPontoSchema.class)), MarcarPonto.class);
    }

    public Page<MarcarPonto> findAll(Pageable pageable){
        return marcarPontoRepository.findAll(pageable)
        .map(m -> mapper.converter(m, MarcarPonto.class));
    }
    
}
