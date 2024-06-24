package com.tulius.literAlura.service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classe);
}
