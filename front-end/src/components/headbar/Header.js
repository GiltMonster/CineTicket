import React from "react";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import "../../style/Header.css";
import PesquisaFilmes from "./Pesquisa";
import{FaUserCircle } from "react-icons/fa"
import { Link } from "react-router-dom";

export default function Header() {
    return (
        <header>
            <div className="logo">
                <Link to={"/"}>HOME</Link>
                {/* <img src="cinema_logo.png" alt="Logo do Cinema"/> */}
            </div>
            <div className="search-box">
                <PesquisaFilmes/>
            </div>
            <div className="login">
                <Link to={"/login"}><FaUserCircle/></Link>
            </div>
        </header>
    );
}