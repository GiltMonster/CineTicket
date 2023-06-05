package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    private final ClienteService service;

    @GetMapping("/test")
    public CadastroDTO testCliente() {
        CadastroDTO cadastroDTO = new CadastroDTO();
        cadastroDTO.setEmail("yurimcf@gmail.com");
        cadastroDTO.setNome("Yuri Mathaus");
        cadastroDTO.setSobrenome("Cavalcante");
        cadastroDTO.setDataNascimento(LocalDate.of(1998, 10, 31));
        cadastroDTO.setTelefone("11995098635");
        cadastroDTO.setEndereco("Rua Nova Brasília");
        cadastroDTO.setSenha("1234");
        return cadastroDTO;
    }

    //ok
    @PostMapping
    public Cliente adicionarCliente(@RequestBody CadastroDTO cadastroDTO) {
        return this.service.inserirCliente(cadastroDTO);
    }

    @PostMapping("/atualizar")
    public Cliente atualizarCliente(@RequestBody Cliente cliente) {
        return this.service.atualizarCliente(cliente);
    }

    //ok
    @GetMapping
    public List<Cliente> buscarTodosClientes() {
        return this.service.buscarTodosClientes();
    }

    //ok
    @DeleteMapping(value = "/{email}")
    public Cliente excluirCliente(@PathVariable("email") String email) {
        return this.service.deletarCliente(email);
    }

    @PutMapping(value = "/{email}")
    public Cliente buscarClientePorEmail(@PathVariable("email") String email) {
        return this.service.buscarClientePorEmail(email);
    }
}
