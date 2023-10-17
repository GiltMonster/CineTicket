import { useEffect, useState } from "react";
import CarrosselFilmes from "../components/home/carrosselFilmes";
import axios from 'axios';
import { keyV4 } from "../../../.env";
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
            listId: 8255087,
            mesParametro: 7
        },
        {
            image: CartazSessaoBiografias,
            listId: 8255081,
            mesParametro: 8
        },
        {
            image: CartazSessaoGames,
            listId: 8255029,
            mesParametro: 9
        },
        {
            image: CartazSessaoLiteratura,
            listId: 8255059,
            mesParametro: 10
        },
        {
            image: CartazSessaoClassicos,
            listId: 8255581,
            mesParametro: 11
        },
        {
            image: CartazSessaoNatal,
            listId: 8255541,
            mesParametro: 12
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
            {
                filmes ?
                    <div>
                        <CarrosselSessoesEspeciais slides={slides} />
                        <CarrosselFilmes filme={filmes} />
                    </div>
                    :
                    <Loading />
            }
        </>
    )
}