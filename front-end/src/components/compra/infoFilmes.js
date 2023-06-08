import React, { useEffect, useState } from "react";
import "../../style/infoFilme.css";
import CadeirasCine from "./cadeirasCine";
import { useParams } from "react-router-dom";
import { keyV3, keyV4 } from "../../apiKeys";
import axios from "axios";
import Loading from "../loading";
import Accordion from "./Accordion";

function InfoFilme() {
  let { filmeId } = useParams();

  const [filme, setFilme] = useState({});
  const [selectedSeats, setSelectedSeats] = useState([]);

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

  const handleSeatClick = (seatNumber) => {
    setSelectedSeats((prevSelectedSeats) => {
      if (prevSelectedSeats.includes(seatNumber)) {
        return prevSelectedSeats.filter((seat) => seat !== seatNumber);
      } else {
        return [...prevSelectedSeats, seatNumber];
      }
    });
  };

  useEffect(() => {
    carregarFilmes();
  }, []);

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
                  (DUB)
                </span>
              </div>

              <div className="bottom">
                <span className="white-space">Data:</span>
                <span className="bold fontHigh">17/05/2021</span>
              </div>
              <div className="bottom">
                <span className="white-space">Horário:</span>
                <span className="bold fontHigh">20:00</span>
              </div>
              <div className="bottom">
                <span className="white-space">Sala:</span>
                <span className="bold fontHigh send-left">01</span>
              </div>
              <div className="bottom generos">
                {filme?.genres?.map((genre, index) => (
                  <button type="button" title="acao" className="showtimes-box language-box" key={index}>{genre?.name}</button>
                ))}
              </div>

              <div className="dinheiro">
                <h1>R$100</h1>
              </div>

            </div>
          </div>
        </div>
        <CadeirasCine handleSeatClick={handleSeatClick} selectedSeats={selectedSeats} />
      </div>
      <Accordion selectedSeats={selectedSeats} />
    </>
  ) : (
    <Loading />
  );
}

export default InfoFilme;

