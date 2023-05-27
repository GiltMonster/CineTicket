import React, { useEffect, useState } from 'react';
import '../../style/FilmesPesquisados.css';
import { keyV4 } from '../../apiKeys';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import Loading from '../loading';

export default function FilmesPesquisados() {

    const [pesquisa, setPesquisa] = useState();
    const [filme, setFilme] = useState();

    let { nomeFilme } = useParams();

    function buscarFilme() {

        const options = {
            method: 'GET',
            url: 'https://api.themoviedb.org/3/search/movie',
            params: {
                query: nomeFilme,
                include_adult: 'false',
                language: 'pt-BR',
                page: '1',
                region: 'br'
            },
            headers: {
                accept: 'application/json',
                Authorization: `Bearer ${keyV4}`
            }
        };

        axios
            .request(options)
            .then(function (response) {
                setFilme(response.data.results);
            })
            .catch(function (error) {
                console.error(error);
            });

    }

    useEffect(() => {

        setPesquisa(nomeFilme);
        buscarFilme();

    }, []);


    return (

        <>

            <div  className='title'>
                <h1>Resultados da sua pesquisa:</h1>
                <h3>{pesquisa}</h3> 
            </div>


            {
                filme ?
                    <div id="movies-grid" className="movies-grid">
                        {
                            filme.map(movie => {
                                return (
                                    <div className='movie-item' key={movie.id}>
                                        <Link to={`/filme/${movie.id}`}>
                                            <img src={`https://image.tmdb.org/t/p/original/${movie.poster_path}`} alt={movie.title} />
                                            <h3>{movie.title}</h3>
                                        </Link>
                                    </div>
                                )
                            })
                        }
                    </div>

                :
                    <Loading />
            }
        </>
    );
};
