package br.senac.sp.projeto.cineticketoficial.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class CadastroDTO {
    private String email;
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private String senha;

    public boolean possuiAtributosNulos() {
        return email == null ||
                nome == null ||
                dataNascimento == null ||
                telefone == null ||
                endereco == null ||
                senha == null;
    }
}
