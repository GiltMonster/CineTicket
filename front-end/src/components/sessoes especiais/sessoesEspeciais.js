import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { keyV3 } from '../../.env';
import Loading from '../loading';
import '../../style/sessoesEspeciais.css';
import { useParams } from 'react-router-dom';

export default function SessoesEspeciais() {

  const { listId, mesParametro } = useParams();

  const [lista, setLista] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log(listId)
    buscarDetalhesLista();
  }, []);

  const buscarDetalhesLista = () => {
    const options = {
      method: 'GET',
      url: `https://api.themoviedb.org/3/list/${listId}`,
      params: {
        api_key: keyV3,
        language: 'pt-BR',
      },
    };

    axios
      .request(options)
      .then(function (response) {
        const sortedItems = response.data.items.sort((a, b) => a.title.localeCompare(b.title));
        setLista({ ...response.data, items: sortedItems });
        setLoading(false);
      })
      .catch(function (error) {
        console.error(error);
        setLoading(false);
      });
  };

  const obterDataFilme = (indice) => {
    const dataInicial = new Date(2023, mesParametro - 1, 1); // Mês no JavaScript de 0(Janeiro) a 11(dezembro)
    const dataFilme = new Date(dataInicial.getTime() + indice * 24 * 60 * 60 * 1000);
    const dia = String(dataFilme.getDate()).padStart(2, '0');
    const mes = String(dataFilme.getMonth() + 1).padStart(2, '0');
    return `${dia}/${mes}`;
  };

  return (
    <>
      <div className="se_title">
        <h1 className="se_main-title">Sessão Cine Ticket</h1>
        <h1 className="se_list-title">{lista ? lista.name : ''}</h1>
      </div>

      {loading ? (
        <Loading />
      ) : lista ? (
        <div id="se_movies-grid">
          {lista.items.map((item, index) => (
            <div className="se_movie-item" key={item.id}>
              <div className="se_movie-date">{obterDataFilme(index)}</div>
              <img
                src={`https://image.tmdb.org/t/p/original/${item.poster_path}`}
                alt={item.title}
              />
              <h3>{item.title}</h3>
            </div>
          ))}
        </div>
      ) : (
        <p>Não foi possível carregar os detalhes da lista.</p>
      )}
    </>
  );
}
