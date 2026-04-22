package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// É uma classe que serve para guardar as lógicas, acessos a bancos (transações)
@Service
public class SerieService {
    // Injeção de dependências
    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obterTodasAsSeries() {
        //Usar o DTO (Data Object Transfer)
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());

    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterLancamento() {
        return converteDados(repositorio.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repositorio.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeCategoria) {
        Categoria categoria = Categoria.fromPortugues(nomeCategoria);
        return converteDados(repositorio.findByGenero(categoria));
    }

    public List<EpisodioDTO> obterTopEpisodios(Long id) {
        return repositorio.obterTopEpisodios(id)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumero(), e.getTitulo(), e.getAvaliacao()))
                .collect(Collectors.toList());
    }
}
