package com.ponto.ponto_digital.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class Mapper {

  private final ModelMapper modelMapper = new ModelMapper();

  public <T> T converter(Object from, Class<T> to) {
    if (Objects.isNull(from)) {
      return null;
    }

    return modelMapper.map(from, to);
  }

   public <S, T> List<T> converter(List<S> from, Class<T> to) {
        if (Objects.isNull(from) || from.isEmpty()) {
            return null;
        }

        return from.stream().map(model -> modelMapper.map(model, to)).collect(Collectors.toList());
    }

}
