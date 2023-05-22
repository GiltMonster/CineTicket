package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.exceptions.IllegalArgumentException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SalaService {
    private final SalaRepository repository;

    public Sala inserirSala(Sala sala) {
        if (sala.getIdSala() == null) {
            throw  new IllegalArgumentException("Campo 'id' não pode ser nulo");
        }
        return this.repository.save(sala);
    }

    public List<Sala> buscarTodasSalas() {
        List<Sala> salas = this.repository.findAll();
        if (salas.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return salas;
    }

    public Sala buscarSalaPorId(String idSala) {
        if(idSala == null ) {
            throw new IllegalArgumentException("Campo 'id' não pode ser nulo");
        }

        return this.repository.findById(idSala).orElseThrow(ResourceNotFoundException::new);
    }

}
