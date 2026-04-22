package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {
    // Configurar os Protocolos e métodos HTTP (Get, Post, Put, Delete)
    // Injeção de dependêncis -> Baixo acoplamento de código
    @Autowired
    private SerieService servico;

    // Cria um caminho para usar o DTO para enviar uma resposta
    @GetMapping
    public List<SerieDTO> obterSeries() {
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return  servico.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamento();
    }

    // PathVariable indica que a variável servirá como caminho da url
    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id) {
        return servico.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero) {
        return servico.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeCategoria) {
        return servico.obterSeriesPorCategoria(nomeCategoria);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTopEpisodios(@PathVariable Long id) {
        return servico.obterTopEpisodios(id);
    }
}
