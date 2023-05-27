import { Link } from "react-router-dom";
import "../style/login.css"

export default function Login() {
    return (
        <div className="Login">
            <form>
                <label for="login">Login:</label>
                <input type="email" id="username" name="username" placeholder="E-mail" required />
                <input type="password" id="password" name="password" placeholder="Senha" required />
                <Link to={"/login/redefinirSenha"} class="esqueceu">Esqueceu sua senha?</Link>
                <p>
                    <input type="submit" value="Entrar" />
                </p>

                <div class="links">
                    Ainda não tem conta?
                    <Link to={"/login/cadastro"}> Cadastre-se</Link>
                </div>
            </form>
        </div>
    )
}