package br.com.alura.screenmatch.service;


import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String Json, Class<T> classe);
    <T> List<T> obterDadosLista(String json, Class<T> classe);
}
