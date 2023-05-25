import React, { useEffect, useState } from 'react';
import '../../style/FilmesPesquisados.css';
import { keyV3, keyV4 } from '../../apiKeys';
import axios from 'axios';
import { useParams } from 'react-router-dom';

export default function FilmesPesquisados() {

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
                setFilme(response.data.results)
            })
            .catch(function (error) {
                console.error(error);
            });

    }

    useEffect(() => {
        buscarFilme();
        filmes();
    }, []);


    function filmes() {
        
        const moviesGrid = document.getElementById('movies-grid');

        if (filme) {

            filme.map(movie => {
                const movieItem = document.createElement('a');
                movieItem.classList.add('movie-item');
                movieItem.href = movie?.link;

                const movieImage = document.createElement('img');
                movieImage.src = `https://image.tmdb.org/t/p/original/${movie?.poster_path}`;
                movieImage.alt = movie?.title;

                const movieTitle = document.createElement('h3');
                movieTitle.textContent = movie?.title;

                movieItem.appendChild(movieImage);
                movieItem.appendChild(movieTitle);

                // Adicionar e remover classe no hover
                movieItem.addEventListener('mouseenter', () => {
                    movieItem.classList.add('hovered');
                });

                movieItem.addEventListener('mouseleave', () => {
                    movieItem.classList.remove('hovered');
                });

                moviesGrid.appendChild(movieItem);
            });
        } else {
            buscarFilme();

        }

    }

    return (
        <div id="movies-grid" className="movies-grid"></div>
    );
};
