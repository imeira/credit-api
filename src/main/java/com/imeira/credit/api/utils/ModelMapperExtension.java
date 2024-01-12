package com.imeira.credit.api.utils;

import org.modelmapper.ModelMapper;

public class ModelMapperExtension {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
