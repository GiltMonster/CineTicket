import React from "react";
import "../../style/infoFilme.css";
import CadeirasCine from "./cadeirasCine";

function InfoFilme() {
  return (
    <>
      <div className="caixa">
        <div className="poster">
          <img
            className="poster-img bottom send-left"
            src="https://ingresso-a.akamaihd.net/prd/img/movie/homem-aranha-atraves-do-aranhaverso-parte-1/216f2cb7-3a64-4b7a-a338-709bcde71147.webp"
            alt="Poster"
          />

          <div className="font-movie movie-box send-left">
            <div className="movie_information_box">
              <div className="show-small-version bold bottom">
                Miranha - Vol. 52
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
                <button type="button" title="acao" className="showtimes-box language-box">Ação</button>
                <button type="button" title="aventura" className="showtimes-box language-box">Aventura</button>
              </div>
            </div>
          </div>
        </div>
        <CadeirasCine />
      </div>
    </>
  );
}

export default InfoFilme;
