package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SessaoDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Sessao;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
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
        sessao.setIdSessao(1); // para o test
        sessao.setDataSessao(sessaoDTO.getDataSessao());
        sessao.setSala(salaService.buscarSalaPorId(sessaoDTO.getIdSala()));
        sessao.setFilme(filmeService.buscarFilmePorId(sessaoDTO.getIdFilme()));
        return this.repository.save(sessao);
    }

    public List<Sessao> buscarTodasSessoes() {
        List<Sessao> sessaos = this.repository.findAll();
        if (sessaos.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return sessaos;
    }

    public Sessao buscarSessaoPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo e aceita valores numéricos somente");
        }
        return this.repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Sessao deleteSessao(Integer id) {
        Sessao deleted = buscarSessaoPorId(id);
        repository.deleteById(id);
        return deleted;
    }
}