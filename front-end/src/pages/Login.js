import { Link } from "react-router-dom";
import "../style/login.css"
import axios from "axios";
import { useEffect, useState } from 'react';
import { UserContext } from "../context/userContext";


export default function Login() {

    const [login, setLogin] = useState();
    const [email, setEmail] = useState();
    const [senha, setSenha] = useState();

    const data = {
        email: email,
        senha: senha
    }

    function logar() {
        console.log(`STADO: email: ${email} senha: ${senha}`)
        const url = 'http://localhost:8080/api/acessos';

        axios.post(url, data)
            .then((response) => {

                if (response.status == 200) {
                    setLogin({
                        logado: true,
                        email: response.data.email,
                        nome: "",
                        sobrenome: "",
                        dataNascimento: "",
                        telefone: "",
                        endereco: "",
                        senha: ""
                    })
                    localStorage.setItem('login', JSON.stringify(login));
                }
            }).catch((error) => {
                console.error(error);
            });
    }

    useEffect(() => {
        logar();

    }, [login]);


    return (
            <div className="conteiner_login">
                <div className="fundo_form">
                    <div className="form_login">
                        <h1 for="login">Login:</h1>
                        <input type="email" id="username" name="username" placeholder="E-mail" required onChange={(e) => setEmail(e.target.value)} />
                        <input type="password" id="password" name="password" placeholder="Senha" required onChange={(e) => setSenha(e.target.value)} />
                        <Link to={"/login/redefinirSenha"} className="links">Esqueceu sua senha?</Link>
                        <p>
                            <input type="submit" value="Entrar" onClick={logar} />
                        </p>

                        <div className="cadastre_se">
                            Ainda não tem conta?
                            <Link className="links" to={"/login/cadastro"}> Cadastre-se</Link>
                        </div>
                    </div>
                </div>
            </div>
    )
}