import axios from "axios";
import { useRef, useState } from "react";
import { Link } from "react-router-dom";
import { keyV3 } from "../../apiKeys";
import "../../style/Header.css";
import { AiOutlineSearch } from "react-icons/ai";

export default function PesquisaFilmes() {

    let urlFilme = `https://api.themoviedb.org/3/search/movie?api_key=${keyV3}&language=pt-BR&append_to_response=videos,images&query=`;

    const nomeFilme = useRef({});
    const [filme, setFilme] = useState();

    function buscarFilme() {
        axios.get(urlFilme + nomeFilme.current.value)
            .then((resp) => {
                console.log(resp.data.results)
                setFilme(resp.data);
            }).catch((error) => {
                console.error(error);
            })
    }

    return (
        <>
            <input type="text" id="pesquisa" placeholder="Pesquisar:" ref={nomeFilme} />
            <Link className="search-icon" to={`/filme/${filme?.id}`} onClick={buscarFilme}><AiOutlineSearch/></Link>
        </>

    )
}