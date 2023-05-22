import React from "react";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import "../../style/Header.css";


export default function Header() {
    return (
        <header>
            <div className="logo">
                <img src="cinema_logo.png" alt="Logo do Cinema"/>
            </div>
            <div className="search-box">
                {/* <FontAwesomeIcon icon={faSearch} className="search-icon" /> */}
                <input type="text" placeholder="Buscar"/>
            </div>
            <div className="login">
                {/* <FontAwesomeIcon icon={faUser} className="user-icon"/> */}
                <a href="#">Login</a>
            </div>
        </header>
    );
}