package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.DTO.CadastroDTO;
import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.entity.Cliente;
import br.senac.sp.projeto.cineticketoficial.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClienteRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ClienteService clienteService;
    private final String baseURI = "/api/clientes";

    @Test
    void testAdicionarCliente_Sucesso() throws Exception {
        CadastroDTO dto = criarCadastroDTO();
        Cliente cliente = criarCliente();

        when(clienteService.inserirCliente(eq(dto))).thenReturn(cliente);
        when(clienteService.inserirCliente(any(CadastroDTO.class))).thenReturn(cliente);

        mvc.perform(post(baseURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"exemplo@email.com\", " +
                                "\"nome\": \"Exemplo\", " +
                                "\"sobrenome\": \"Teste\", " +
                                "\"dataNascimento\": \"1998-10-31\", " +
                                "\"telefone\": \"123456789\", " +
                                "\"endereco\": \"Rua Nova Brasilia\", " +
                                "\"senha\": \"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(cliente.getEmail()))
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.sobrenome").value(cliente.getSobrenome()))
                .andExpect(jsonPath("$.dataNascimento").value("1998-10-31"))
                .andExpect(jsonPath("$.telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$.endereco").value(cliente.getEndereco()));
    }

    @Test
    void testBuscarClientePorEmail_Sucesso() throws Exception{
        String email = "exemplo@email.com";
        Cliente cliente = criarCliente();

        when(clienteService.buscarClientePorEmail(email)).thenReturn(cliente);

        mvc.perform(get(baseURI + "/" + cliente.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(cliente.getEmail()))
                .andExpect(jsonPath("$.nome").value(cliente.getNome()))
                .andExpect(jsonPath("$.sobrenome").value(cliente.getSobrenome()))
                .andExpect(jsonPath("$.dataNascimento").value(cliente.getDataNascimento().toString()))
                .andExpect(jsonPath("$.telefone").value(cliente.getTelefone()))
                .andExpect(jsonPath("$.endereco").value(cliente.getEndereco()));

    }

    private CadastroDTO criarCadastroDTO() {
        CadastroDTO dto = new CadastroDTO();
        dto.setEmail("exemplo@email.com");
        dto.setNome("Exemplo");
        dto.setSobrenome("Teste");
        dto.setDataNascimento(LocalDate.of(1998, 10, 31));
        dto.setTelefone("123456789");
        dto.setEndereco("Rua Nova Brasilia");
        dto.setSenha("123456");
        return dto;
    }

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setEmail("exemplo@email.com");
        cliente.setNome("Exemplo");
        cliente.setSobrenome("Teste");
        cliente.setDataNascimento(LocalDate.of(1998, 10, 31));
        cliente.setTelefone("123456789");
        cliente.setEndereco("Rua Nova Brasilia");
        Acesso acesso = new Acesso();
        acesso.setEmail("exemplo@email.com");
        acesso.setSenha("123456");
        cliente.setAcesso(acesso);
        return cliente;
    }
}