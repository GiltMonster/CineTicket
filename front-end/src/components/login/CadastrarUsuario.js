import { useState } from "react";
import "../../style/Cadastro.css";
import axios from "axios";
import img from "../../resource/img/movies.jpg";

export default function Cadastro() {

    const [isCadastroSucesso, setIsCadastroSucesso] = useState(false);


    const [email, setEmail] = useState("");
    const [nome, setNome] = useState("");
    const [sobrenome, setSobrenome] = useState("");
    const [dataNascimento, setDataNascimento] = useState("");
    const [telefone, setTelefone] = useState("");
    const [endereco, setEndereco] = useState("");
    const [senha, setSenha] = useState("");

    const data = {
        email: email,
        nome: nome,
        sobrenome: nome,
        dataNascimento: dataNascimento,
        telefone: telefone,
        endereco: endereco,
        senha: senha
    }

    const handleFormSubmit = (event) => {
        event.preventDefault();
        // Lógica de cadastro aqui
        console.log(`STADO: Nome: ${nome}, Sobrenome: ${sobrenome}, data de nasc: ${dataNascimento}, telefone: ${telefone}, endereco: ${endereco}, senha: ${senha}`)
        axios.post("http://localhost:8080/api/clientes", data)
            .then((response) => {
                console.log(response);
            }).catch(function (error) {
                console.error(error);
            });
        // Simulando o cadastro bem-sucedido após 2 segundos
        setTimeout(() => {
            setIsCadastroSucesso(true);
        }, 2000);
    };

    return (
        <>
            <img className="imgLogin" src={img} alt={`Imagem de filmes`} />
            <div className="SignupForm">
                {!isCadastroSucesso ? (
                    <form onSubmit={handleFormSubmit}>
                        <h2>Cadastro</h2>
                        <label htmlFor="email">E-mail:</label>
                        <input type="email" id="email" name="email" placeholder="E-mail" required onChange={(e) => setEmail(e.target.value)} />
                        <label htmlFor="firstName">Nome:</label>
                        <input type="text" id="firstName" name="firstName" placeholder="Nome" required onChange={(e) => setNome(e.target.value)} />
                        <label htmlFor="secondName">Sobrenome:</label>
                        <input type="text" id="secondName" name="secondName" placeholder="Sobrenome" required onChange={(e) => setSobrenome(e.target.value)} />
                        <label htmlFor="dataNascimento">Data de nascimento:</label>
                        <input type="date" id="dataNascimento" name="dataNascimento" placeholder="Data de nascimento" required onChange={(e) => setDataNascimento(e.target.value)} />
                        <label htmlFor="telefone">Telefone:</label>
                        <input type="tel" id="telefone" name="telefone" placeholder="Telefone" required onChange={(e) => setTelefone(e.target.value)} />
                        <label htmlFor="endereco">Endereço</label>
                        <input type="text" id="endereco" name="endereco" placeholder="Endereço" required onChange={(e) => setEndereco(e.target.value)} />
                        <label htmlFor="password">Senha:</label>
                        <input type="password" id="password" name="password" placeholder="Senha" required onChange={(e) => setSenha(e.target.value)} />
                        <input type="submit" value="Cadastrar" />
                    </form>
                ) : (
                    <div className="SuccessMessage">
                        <p>Cadastro realizado com sucesso!</p>
                    </div>
                )}
            </div>
        </>
    );
}
