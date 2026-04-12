package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSeriesEncontradas(@JsonAlias("Title") String tituloEncontrado,
                                     @JsonAlias("Year") String ano) {
}
