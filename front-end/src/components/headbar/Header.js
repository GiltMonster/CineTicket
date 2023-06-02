import React from "react";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import "../../style/Header.css";
import PesquisaFilmes from "./Pesquisa";
import{FaUserCircle } from "react-icons/fa"
import Laranja from "../../resource/img/laranja png.png";
import { Link } from "react-router-dom";

export default function Header() {
    return (
        <header>
            <div className="logo">
                <Link to={"/"}>
                    <img src={Laranja} alt="Logo"/>
                </Link>
                {/* <img src="cinema_logo.png" alt="Logo do Cinema"/> */}
            </div>
            <div className="search-box">
                <PesquisaFilmes/>
            </div>
            <div className="icon_login">
                <Link to={"/login"}><FaUserCircle/></Link>
            </div>
        </header>
    );
}