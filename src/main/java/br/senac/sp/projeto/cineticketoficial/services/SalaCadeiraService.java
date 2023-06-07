package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeira;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeiraPK;
import br.senac.sp.projeto.cineticketoficial.exceptions.NullAttributesException;
import br.senac.sp.projeto.cineticketoficial.exceptions.ResourceNotFoundException;
import br.senac.sp.projeto.cineticketoficial.repository.SalaCadeiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaCadeiraService {
    private final SalaCadeiraRepository repository;
    private final CadeiraService cadeiraService;
    private final SalaService salaService;


    public SalaCadeira inserirEAtualizarSalaCadeira(SalaCadeiraDTO salaCadeiraDTO) {
        if (salaCadeiraDTO.possuiAtributosNulos()) {
            throw new NullAttributesException();
        }
        SalaCadeira salaCadeira = criarSalaCadeiraAPartirDTO(salaCadeiraDTO);
        return this.repository.save(salaCadeira);
    }

    private SalaCadeira criarSalaCadeiraAPartirDTO(SalaCadeiraDTO salaCadeiraDTO) {
        SalaCadeiraPK pk = new SalaCadeiraPK();
        pk.setIdSala(salaCadeiraDTO.getIdSala());
        pk.setIdCadeira(salaCadeiraDTO.getIdCadeira());

        SalaCadeira salaCadeira = new SalaCadeira();
        salaCadeira.setSalacadeiraPK(pk);
        salaCadeira.setOcupado(salaCadeiraDTO.isOcupado());

        Cadeira cadeira = new Cadeira();
        cadeira.setIdCadeira(salaCadeiraDTO.getIdCadeira());
        salaCadeira.setCadeira(cadeira);

        Sala sala = new Sala();
        sala.setIdSala(salaCadeiraDTO.getIdSala());
        salaCadeira.setSala(sala);
        return salaCadeira;
    }

    public List<SalaCadeira> listarTodasSalaCadeiras() {
        List<SalaCadeira> cadeiras = this.repository.findAll();
        if (cadeiras.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return cadeiras;
    }

    public void atualizarStatusCadeiras(List<SalaCadeiraDTO> cadeirasOcupadas) {
        List<SalaCadeira> cadeirasEmUso = new ArrayList<>();
        for (SalaCadeiraDTO dto : cadeirasOcupadas) {
            SalaCadeira cadeiraUsada = inserirEAtualizarSalaCadeira(dto);
            cadeiraUsada.setOcupado(true);
            cadeirasEmUso.add(cadeiraUsada);
        }
        this.repository.saveAll(cadeirasEmUso);
    }

    public boolean criarSalaCadeiras() {
        List<Cadeira> cadeiras = cadeiraService.buscarTodasCadeiras();
        List<Sala> salas = salaService.buscarTodasSalas();
        List<SalaCadeiraDTO> dtos = new ArrayList<>();
        for (Sala sala : salas) {
            for (Cadeira cadeira : cadeiras) {
                SalaCadeiraDTO dto = new SalaCadeiraDTO();
                dto.setIdSala(sala.getIdSala());
                dto.setIdCadeira(cadeira.getIdCadeira());
                dto.setOcupado(false);
                dtos.add(dto);
            }
        }
        for (SalaCadeiraDTO dto : dtos) {
            inserirEAtualizarSalaCadeira(dto);
        }


        return true;
    }

}
