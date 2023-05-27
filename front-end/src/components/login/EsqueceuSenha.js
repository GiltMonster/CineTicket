import React, { useState } from "react";
import "../../style/EsqueceuSenha.css";

export default function EsqueceuSenha() {
    const [email, setEmail] = useState("");
    const [enviado, setEnviado] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        // Aqui você pode adicionar a lógica para enviar o link de redefinição de senha para o e-mail fornecido
        // Por exemplo, você pode fazer uma requisição a uma API de envio de e-mails ou executar qualquer outra ação necessária

        // Exemplo de ação fictícia que simula o envio do link:
        setTimeout(() => {
            setEnviado(true);
        }, 2000);
    };

    return (
        <div className="PasswordRecovery">
            <h2>Recuperação de Senha</h2>
            {enviado ? (
                <p>Um e-mail com instruções de recuperação de senha foi enviado para {email}.</p>
            ) : (
                <>
                    <p>Informe seu e-mail para receber instruções de recuperação de senha</p>
                    <form onSubmit={handleSubmit}>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Seu e-mail"
                            required
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                        <div className="button-container">
                            <input type="submit" value="Enviar" />
                        </div>
                    </form>
                </>
            )}
        </div>
    );
}
