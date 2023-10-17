import React, { useEffect, useState } from "react";
import "../../style/infoFilme.css";
import { useParams } from "react-router-dom";
import { keyV3, keyV4 } from "../../.env";
import axios from "axios";
import Loading from "../loading";

function InfoFilme({setInfoSection, setFilmeCompra}) {
  let { filmeId } = useParams();
  let { room } = useParams();
  let { time } = useParams();
  let { type } = useParams();
  let { date } = useParams();

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
        setFilmeCompra(response.data);
        setFilme(response.data);
      })
      .catch(function (error) {
        console.error(error);
      });
      
    }
    
    function addInfoFilme(filmeId, room, time, type, date) {
      
      setInfoSection({
        filmeId: filmeId,
        dataSection: formataDataParaEnviar(date),
        sala: room,
        horario: time,
        legDub: type
      })
    }
    
    useEffect(() => {
      carregarFilmes();
      addInfoFilme(filmeId, room, time, type, date);
      
    }, [InfoFilme]);
    
  function formataData(data) {
    const novaData = data.split("-");
    return `${novaData[2]}/${novaData[1]}/${novaData[0]}`
  }
  
  function formataDataParaEnviar(data) {
    const novaData = data.split("-");
    const str = `${novaData[0]}-${novaData[1]}-${novaData[2]}`
    return `${str}`
  }

  if (Object.keys(filme).length === 0) {
    return <Loading />;
  }

  return filme ? (
    <>
      <div className="caixa">
        <div className="poster">
          <img
            className="poster-img bottom send-left"
            src={`https://image.tmdb.org/t/p/original/${filme.poster_path}`}
            alt={`Poster: ${filme.title}`}
          />

          <div className="font-movie movie-box send-left">
            <div className="movie_information_box">
              <div className="show-small-version bold bottom">
                {filme.title}
                <span className="showtimes-box-lang language-box" title="DUB" data-value="DUB">
                  ({type})
                </span>
              </div>

              <div className="bottom">
                <span className="white-space">Data:</span>
                <span className="bold fontHigh">{formataData(date)}</span>
              </div>
              <div className="bottom">
                <span className="white-space">Horário:</span>
                <span className="bold fontHigh">{time}</span>
              </div>
              <div className="bottom">
                <span className="white-space">Sala:</span>
                <span className="bold fontHigh send-left">{room}</span>
              </div>
              <div className="bottom generos">
                {filme?.genres?.map((genre, index) => (
                  <button type="button" title={genre?.name} className="showtimes-box language-box" key={index}>{genre?.name}</button>
                ))}
              </div>

              <div className="dinheiro">
                <h3>Ingresso: R$ 25,00</h3>
              </div>

            </div>
          </div>
        </div>
      </div>
    </>
  ) : (
    <Loading />
  );
}

export default InfoFilme;

