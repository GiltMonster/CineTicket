package br.senac.sp.projeto.cineticketoficial.services;

import br.senac.sp.projeto.cineticketoficial.DTO.SalaCadeiraDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cadeira;
import br.senac.sp.projeto.cineticketoficial.entity.Sala;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeira;
import br.senac.sp.projeto.cineticketoficial.entity.SalaCadeiraPK;
import br.senac.sp.projeto.cineticketoficial.repository.SalaCadeiraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaCadeiraService {
    private final SalaCadeiraRepository repository;


    public SalaCadeira inserirEAtualizarSalaCadeira(SalaCadeiraDTO salaCadeiraDTO) {
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
        return this.repository.findAll();
    }

    public void atualizarStatusCadeiras(List<SalaCadeiraDTO> cadeirasOcupadas) {
        List<SalaCadeira> cadeirasUsadas = new ArrayList<>();
        for (SalaCadeiraDTO dto : cadeirasOcupadas) {
            SalaCadeira cadeiraUsada = inserirEAtualizarSalaCadeira(dto);
            cadeiraUsada.setOcupado(true);
            cadeirasUsadas.add(cadeiraUsada);
        }
        this.repository.saveAll(cadeirasUsadas);
    }

}
