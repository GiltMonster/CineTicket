import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { keyV3, keyV4 } from "../.env";
import Banner from "../components/filmes/Banner";
import { ContextFilm } from "../context/filmeContext";
import Loading from "../components/loading";

export default function Filme() {

    let { filmeId } = useParams();

    const [filme, setFilme] = useState({});

    function carregarFilmes() {

        const options = {
            method: 'GET',
            url: `https://api.themoviedb.org/3/movie/${filmeId}?api_key=${keyV3}&append_to_response=videos,images`,
            params: {
                include_adult: 'false',
                include_video: 'true',
                language: 'pt-BR',
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

    useEffect(() => {
        carregarFilmes();
        console.log(filme)
    }, []);

    return (

        filme ?

        <>
            <ContextFilm.Provider value={filme}>
                <Banner/>
            </ContextFilm.Provider>
        </>

        :

        <Loading/>
    )
}