package br.senac.sp.projeto.cineticketoficial.restAPIs;

import br.senac.sp.projeto.cineticketoficial.entity.Acesso;
import br.senac.sp.projeto.cineticketoficial.exceptions.LoginInvalidException;
import br.senac.sp.projeto.cineticketoficial.services.AcessoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AcessoRestControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AcessoService acessoService;
    private final String baseURI = "/api/acessos";

    @Test
    void testLogin_Sucesso() throws Exception {
        // Dados do acesso
        Acesso acesso = criarAcesso();

        // Configurando o mock
        when(acessoService.validarLogin(eq(acesso))).thenReturn(acesso);
        when(acessoService.validarLogin(any(Acesso.class))).thenReturn(acesso);

        // Realizando a requisição e verificando o resultado
        mvc.perform(post(baseURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"exemplo@gmail.com\", \"senha\": \"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(acesso.getEmail()))
                .andExpect(jsonPath("$.senha").value(acesso.getSenha()));
    }

    @Test
    void testLogin_SenhaErrada() throws Exception {
        // Dados do acesso
        Acesso acesso = criarAcesso();

        // Configurando o mock
        when(acessoService.validarLogin(eq(acesso))).thenReturn(acesso);
        when(acessoService.validarLogin(any(Acesso.class))).thenThrow(new LoginInvalidException());

        // Realizando a requisição e verificando o resultado
        mvc.perform(post(baseURI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"exemplo@gmail.com\", \"senha\": \"12345\"}"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

    }

    @Test
    void atualizarAcesso_Sucesso() throws Exception {
        // Dados do acesso
        Acesso acesso = criarAcesso();
        Acesso acessoAtualizado = new Acesso();
        acessoAtualizado.setEmail(acesso.getEmail());
        acessoAtualizado.setSenha("12345");

        // Configurando o mock
        when(acessoService.atualizarSenha(eq(acesso))).thenReturn(acessoAtualizado);
        when(acessoService.atualizarSenha(any(Acesso.class))).thenReturn(acessoAtualizado);

        // Realizando a requisição e verificando o resultado
        mvc.perform(put(baseURI + "/" + "atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"exemplo@gmail.com\", \"senha\": \"12345\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(acessoAtualizado.getEmail()))
                .andExpect(jsonPath("$.senha").value(acessoAtualizado.getSenha()));
    }


    private Acesso criarAcesso() {
        Acesso acesso = new Acesso();
        acesso.setEmail("exemplo@gmail.com");
        acesso.setSenha("123456");
        return acesso;
    }
}