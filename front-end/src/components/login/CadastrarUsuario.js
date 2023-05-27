import React, { useState } from "react";
import "../../style/Cadastro.css";

export default function Cadastro() {
    const [isCadastroSucesso, setIsCadastroSucesso] = useState(false);

    const handleFormSubmit = (event) => {
        event.preventDefault();
        // Lógica de cadastro aqui

        // Simulando o cadastro bem-sucedido após 2 segundos
        setTimeout(() => {
            setIsCadastroSucesso(true);
        }, 2000);
    };

    return (
        <div>
            <h2>Cadastro</h2>
            <div className="SignupForm">
                {!isCadastroSucesso ? (
                    <form onSubmit={handleFormSubmit}>
                        <label htmlFor="name">Nome:</label>
                        <input type="text" id="name" name="name" placeholder="Nome" required />
                        <label htmlFor="email">E-mail:</label>
                        <input type="email" id="email" name="email" placeholder="E-mail" required />
                        <label htmlFor="password">Senha:</label>
                        <input type="password" id="password" name="password" placeholder="Senha" required />
                        <input type="submit" value="Cadastrar" />
                    </form>
                ) : (
                    <div className="SuccessMessage">
                        <p>Cadastro realizado com sucesso!</p>
                    </div>
                )}
            </div>
        </div>
    );
}
