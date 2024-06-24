package com.tulius.literAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T convertData(String json, Class<T> className){
        try {
            return objectMapper.readValue(json,className);
        }catch (JsonProcessingException e){
            throw new RuntimeException();
        }
    }
}
