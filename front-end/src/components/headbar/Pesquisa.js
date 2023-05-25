import { useState} from "react";
import { Link } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import "../../style/Header.css";

export default function PesquisaFilmes() {

    const [pesquisa, setPesquisa] = useState()
    
    

    return (
        <>
            <Link className="search-icon" to={`/pesquisarFilme/${pesquisa}`}><FaSearch/></Link>
            <input type="text" id="pesquisa" placeholder="Pesquisar:" onChange={(e) => setPesquisa(e.target.value)}/>
        </>

    )
}