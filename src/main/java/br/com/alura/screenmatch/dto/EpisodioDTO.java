package br.com.alura.screenmatch.dto;

public record EpisodioDTO(Integer temporada, Integer numeroEpisodio, String titulo, Double avaliacao) {
    // Construtor "antigo" para não quebrar o resto do código
    public EpisodioDTO(Integer temporada, Integer numeroEpisodio, String titulo) {
        this(temporada, numeroEpisodio, titulo, 0.0);
    }
}
