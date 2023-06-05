package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.CadeiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadeiraService {
    private final CadeiraRepository repository;

    public List<Cadeira> buscarTodasCadeiras() {
        List<Cadeira> cadeiras = this.repository.findAll();
        if (cadeiras.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return cadeiras;
    }

}
