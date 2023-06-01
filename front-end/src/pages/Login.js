import { Link } from "react-router-dom";
import "../style/login.css"

export default function Login() {
    return (
        <div className="conteiner_login">
            <div className="fundo_form">
                <form className="form_login">
                    <h1 for="login">Login:</h1>
                    <input type="email" id="username" name="username" placeholder="E-mail" required />
                    <input type="password" id="password" name="password" placeholder="Senha" required />
                    <Link to={"/login/redefinirSenha"} className="links">Esqueceu sua senha?</Link>
                    <p>
                        <input type="submit" value="Entrar" />
                    </p>

                    <div className="cadastre_se">
                        Ainda não tem conta?
                        <Link className="links" to={"/login/cadastro"}> Cadastre-se</Link>
                    </div>
                </form>
            </div>
        </div>
    )
}