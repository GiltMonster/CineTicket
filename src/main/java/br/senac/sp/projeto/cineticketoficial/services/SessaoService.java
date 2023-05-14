package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SessaoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Sessao;
import br.senac.sp.projeto.cineticketoficial.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoService {
    private final SessaoRepository repository;
    private final FilmeService filmeService;
    private final SalaService salaService;

    public Sessao inserirSessao(SessaoDTO sessaoDTO) {
        Sessao sessao = new Sessao();
        sessao.setDataSessao(sessaoDTO.getDataSessao());
        sessao.setSala(salaService.buscarSalaPorId(sessaoDTO.getIdSala()));
        sessao.setFilme(filmeService.buscarFilmePorId(sessaoDTO.getIdFilme()));
        return this.repository.save(sessao);
    }

    public List<Sessao> buscarTodasSessoes() {
        return this.repository.findAll();
    }

    public Sessao buscarSessaoPorId(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Sessao excluirSessao(Integer id) {
        Sessao deleted = buscarSessaoPorId(id);
        repository.deleteById(id);
        return deleted;
    }
}