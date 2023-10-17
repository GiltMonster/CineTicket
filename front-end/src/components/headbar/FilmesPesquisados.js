import React, { useEffect, useState } from 'react';
import '../../style/FilmesPesquisados.css';
import { keyV4 } from '..//vercel/path0/front-end/src/pages';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import Loading from '../loading';
import filmeReserva from "../../resource/img/filmeReserva.png"

export default function FilmesPesquisados() {
    const [pesquisa, setPesquisa] = useState();
    const [filme, setFilme] = useState([]);
    const [loading, setLoading] = useState(true);

    let { nomeFilme } = useParams();

    useEffect(() => {
        setPesquisa(nomeFilme);
        buscarFilme();
    }, []);

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
                setLoading(false);
            })
            .catch(function (error) {
                console.error(error);
                setLoading(false);
            });
    }

    return (
        <>
            <div className='title'>
                <h1>Resultados da sua pesquisa:</h1>
                <h3>{pesquisa}</h3>
            </div>

            {loading ? (
                <Loading />
            ) : filme.length > 0 ? (
                <div id="movies-grid" className="movies-grid">
                    {filme.map(movie => (
                        <div className='movie-item' key={movie.id}>
                            <Link className='name-movie' to={`/filme/${movie.id}`}>
                                {movie.poster_path ? (
                                    <img src={`https://image.tmdb.org/t/p/original/${movie.poster_path}`} alt={movie.title} />
                                ) : (
                                    <img src={filmeReserva} alt={movie.title} />
                                )}
                                <h3>{movie.title}</h3>
                            </Link>
                        </div>
                    ))}
                </div>
            ) : (
                <p>Nenhum resultado encontrado.</p>
            )}
        </>
    );
}
