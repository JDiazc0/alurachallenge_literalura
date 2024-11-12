package com.alurachallenge.alurachallenge_literalura.Service;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
