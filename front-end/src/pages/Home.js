import { useEffect, useState } from "react";
import CarrosselFilmes from "../components/home/carrosselFilmes";
import axios from 'axios';
import { keyV4 } from "../apiKeys";
import Loading from "../components/loading";
import CarrosselSessoesEspeciais from "../components/home/CarrosselSessoesEspeciais";

export default function Home() {

    const [filmes, setFilmes] = useState()

    const slides = [
        {
            image: "https://ingresso-a.akamaihd.net/prd/img/movie/super-mario-bros/e092a890-d427-4367-a845-6e9506262a58.jpg",
            caption: "Texto do Slide 1",
        },
        {
            image: "https://ingresso-a.akamaihd.net/prd/img/movie/super-mario-bros/e092a890-d427-4367-a845-6e9506262a58.jpg",
            caption: "Texto do Slide 2",
        },
        {
            image: "https://ingresso-a.akamaihd.net/prd/img/movie/super-mario-bros/e092a890-d427-4367-a845-6e9506262a58.jpg",
            caption: "Texto do Slide 3",
        },
        {
            image: "https://ingresso-a.akamaihd.net/prd/img/movie/super-mario-bros/e092a890-d427-4367-a845-6e9506262a58.jpg",
            caption: "Texto do Slide 4",
        },
    ];

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


    useEffect(() => {
        carregarFilmes();
    }, []);


    return (
        <>
            <div>
                <CarrosselSessoesEspeciais slides={slides} />
            </div>

            {
                filmes ?

                    <CarrosselFilmes filme={filmes} />

                    :

                    <Loading />
            }
        </>
    )
}