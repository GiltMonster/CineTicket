import { useEffect, useState } from "react";
import CarrosselFilmes from "../components/home/carrosselFilmes";
import axios from 'axios';
import { keyV4 } from "../apiKeys";
import Loading from "../components/loading";

export default function Home() {

    const [filmes, setFilmes] = useState()

    function carregarFilmes() {
        const options = {
            method: 'GET',
            url: 'https://api.themoviedb.org/3/discover/movie',
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
                setFilmes(response.data.results);
            })
            .catch(function (error) {
                console.error(error);
            });
    
    }

 
    useEffect(()=>{
        carregarFilmes();
    }, []);


    return (
       
        filmes ?
                  
        <CarrosselFilmes filme={filmes}/>
            
        :

        <Loading/> 

    )
}