import { Link } from "react-router-dom";
import "../style/login.css"

export default function Login() {
    return (
        <div className="Login">
            <form>
                <label for="login">Login:</label>
                <input type="text" id="username" name="username" placeholder="E-mail" required />
                <input type="password" id="password" name="password" placeholder="Senha" required />
                <Link href="#" class="esqueceu">Esqueceu sua senha?</Link>
                <p>
                    <input type="submit" value="Entrar" />
                </p>

                <div class="links">
                    Ainda não tem conta?
                    <Link href="#"> Cadastre-se</Link>
                </div>
            </form>
        </div>
    )
}