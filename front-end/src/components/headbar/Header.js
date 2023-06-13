import React, { useEffect, useState } from "react";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import "../../style/Header.css";
import PesquisaFilmes from "./Pesquisa";
import { FaUserCircle } from "react-icons/fa"
import Laranja from "../../resource/img/laranja png.png";
import { Link } from "react-router-dom";

export default function Header() {

    const [isPopupOpen, setIsPopupOpen] = useState(false);
    const [isLogado, setIsLogado] = useState();
    const [nome, setNome] = useState();

    function togglePopup() {
        setIsPopupOpen(!isPopupOpen);
    };

    function deslogar() {
        window.localStorage.clear('login');
    }

    function verificaLogado() {
        try {
            const logado = localStorage.getItem('login')
            setIsLogado(JSON.parse(logado));
            setNome(`${isLogado?.nome} ${isLogado?.sobrenome}`);
            // Se chegou até aqui, o JSON é válido
            console.log("JSON válido:", isLogado);
            // Faça o que precisa ser feito com o JSON aqui
          } catch (error) {
            console.log("Erro de parsing do JSON:", error);
            // Trate o erro de parsing do JSON aqui
          }
        // Converte este json para objeto

    }

    useEffect(() => {
        verificaLogado();
        console.log(isLogado);
    }, [isPopupOpen, nome])



    return (
        <header>
            <div className="logo">
                <Link to={"/"}>
                    <img src={Laranja} alt="Logo" />
                </Link>
                {/* <img src="cinema_logo.png" alt="Logo do Cinema"/> */}
            </div>
            <div className="search-box">
                <PesquisaFilmes />
            </div>
            <div onMouseEnter={togglePopup} onMouseLeave={togglePopup}>
                <Link className="icon_login" to={"/login"}><FaUserCircle /></Link>
                {isPopupOpen && (
                    <div className="profile-popup">
                        {
                            isLogado === undefined || isLogado === null ?
                                <div className="popup-login">
                                    <h4>Olá caro usuário, não gostaria de logar ou criar uma conta ??</h4>
                                    <Link className="login-button" to={"/login"} reloadDocument>Logar</Link>
                                </div>
                                :
                                <div className="popup-login">
                                    <h2>Ola, {nome}</h2>
                                    <Link className="logout-button" onClick={deslogar} reloadDocument>Deslogar</Link>
                                </div>
                        }
                    </div>
                )}
            </div>
        </header>
    );
}