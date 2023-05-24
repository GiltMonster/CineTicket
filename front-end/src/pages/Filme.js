import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { keyV4 } from "../apiKeys";

export default function Filme() {

    let { filmeId } = useParams();

    const [filme, setFilme] = useState({});

    function carregarFilmes() {
        const options = {
            method: 'GET',
            url: `https://api.themoviedb.org/3/movie/${filmeId}`,
            params: {
                include_adult: 'false',
                include_video: 'true',
                language: 'pt-BR',
                page: '1',
                sort_by: 'popularity.desc'
            },
            headers: {
                accept: 'application/json',
                Authorization: `Bearer ${keyV4}`
            }
        };
    
        axios
            .request(options)
            .then(function (response) {
                setFilme(response.data);
                console.log(response.data);
                
            })
            .catch(function (error) {
                console.error(error);
            });
    
    }
 
    useEffect(()=>{
        carregarFilmes();
        console.log(filme)
    }, []);

    return(
        <h1>Filme AQUI</h1>
    )    
}