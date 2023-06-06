import { useEffect, useState } from "react";
import CarrosselFilmes from "../components/home/carrosselFilmes";
import axios from 'axios';
import { keyV4 } from "../apiKeys";
import Loading from "../components/loading";
import CarrosselSessoesEspeciais from "../components/home/CarrosselSessoesEspeciais";
import CartazSessaoGames from "../resource/img/CartazSessaoGames.png";
import CartazSessaoLiteratura from "../resource/img/CartazSessaoLiteratura.png";
import CartazSessaoDevs from "../resource/img/CartazSessaoDevs.png";
import CartazSessaoNatal from "../resource/img/CartazSessaoNatal.png";
import CartazSessaoBiografias from "../resource/img/CartazSessaoBiografias.png";
import CartazSessaoClassicos from "../resource/img/CartazSessaoClassicos.png";

export default function Home() {

    const [filmes, setFilmes] = useState()

    const slides = [
        {
            image: CartazSessaoDevs,
            caption: "Sessão DEV's",
            listId: 8255087
        },
        {
            image: CartazSessaoBiografias,
            caption: "Sessão Biografias",
            listId: 8255081
        },
        {
            image: CartazSessaoGames,
            caption: "Sessão de Games",
            listId: 8255029
        },
        {
            image: CartazSessaoLiteratura,
            caption: "Sessão Literatura",
            listId: 8255059
        },
        {
            image: CartazSessaoClassicos,
            caption: "Sessão Clássicos",
            listId: 8255581
        },
        {
            image: CartazSessaoNatal,
            caption: "Sessão Natal",
            listId: 8255541
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