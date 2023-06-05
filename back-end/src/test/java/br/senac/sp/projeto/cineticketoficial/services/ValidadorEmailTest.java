package br.senac.sp.projeto.cineticketoficial.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorEmailTest {
    private final ValidadorEmail validadorEmail = new ValidadorEmail();
    String email;
    boolean response;

    @Test
    void testEmailValido() {
        email = "yuri@gmail.com";

        response = validadorEmail.validate(email);

        assertTrue(response, "Esse é um email valido e nosso validador deve confirmar isso");
    }


    @Test
    void testEmailSemArroba() {
        email = "yurigmail.com";

        response = validadorEmail.validate(email);

        assertFalse(response, "Esse NÃO é um email valido, pois não possui @.");
    }

    @Test
    void testEmailSemNome() {
        email = "@gmail.com";

        response = validadorEmail.validate(email);

        assertFalse(response, "Esse NÃO é um email valido, pois não possui nada antes @.");
    }

    @Test
    void testEmailSemDominio() {
        email = "yuri@.com";

        response = validadorEmail.validate(email);

        assertFalse(response, "Esse NÃO é um email valido, pois não possui o Domino");
    }

    @Test
    void testEmailNull() {
        response = validadorEmail.validate(null);

        assertFalse(response, "Esse NÃO é um email valido");
    }

    @Test
    void testEmailSemPonto() {
        email = "yuri@gmailcom";

        response = validadorEmail.validate(email);

        assertFalse(response, "Esse NÃO é um email valido, pois não possui um ponto depois do dominio");
    }

    @Test
    void testEmailsemExtencao() {
        email = "yuri@gmail.";

        response = validadorEmail.validate(email);

        assertFalse(response, "Esse não é um email valido, pois não possui extenção");
    }
}