package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.repository.CadeiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadeiraService {
    private final CadeiraRepository repository;

    public List<Cadeira> buscarTodasCadeiras() {
        return this.repository.findAll();
    }

}
