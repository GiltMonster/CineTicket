import React, { useContext, useEffect, useState } from "react";
import "../../style/Banner.css";
import { ContextFilm } from "../../context/filmeContext";
import Loading from "../loading";
import TrailerSinopse from "./TrailerSinopse";

export default function Banner() {
  const filme = useContext(ContextFilm);
  const [colorRating, setColorRating] = useState();
  const [ageRating, setAgeRating] = useState(0);

  const genres = ["Ação", "Aventura", "Comédia"];

  const isMobile = window.innerWidth <= 834;

  // Função para mapear a idade para a cor correspondente
  function getAgeRatingColor(age) {
    if (age >= 18) {
      return "#000000";
    } else if (age >= 16) {
      return "#ff0000";
    } else if (age >= 14) {
      return "#ff4f00";
    } else if (age >= 12) {
      return "#ffff00";
    } else if (age >= 10) {
      return "#26a8fe";
    } else {
      setAgeRating('L');
      return "#008000";
    }
  }

  useEffect(() => {
    setColorRating(getAgeRatingColor(ageRating));
  }, []);

  return filme ? (
    <div className="movie-banner">
      <div className="trailer-sinopse">
        <TrailerSinopse />
      </div>
      <div className="movie-info">
        <h1>{filme.title}</h1>
        <div className="genres">
          {filme?.genres?.map((genre, index) => (
            <p className="genre-border" key={index}>
              {genre?.name}
            </p>
          ))}
        </div>
        <div className="age-rating" style={{ backgroundColor: colorRating }}>
          {ageRating}
        </div>
      </div>
      {isMobile ? (
        <img
          src={`https://image.tmdb.org/t/p/original/${filme.poster_path}`}
          alt={`Imagem do filme: ${filme.title}`}
          className="banner-image"
        />
      ) : (
        <img
          src={`https://image.tmdb.org/t/p/original/${filme.backdrop_path}`}
          alt={`Banner do Filme: ${filme.title}`}
          className="banner-image"
        />
      )}
      {!isMobile && (
        <div className="movie-poster">
          <img
            src={`https://image.tmdb.org/t/p/original/${filme.poster_path}`}
            alt={`Imagem do filme: ${filme.title}`}
            className="poster-image"
          />
        </div>
      )}
    </div>
  ) : (
    <Loading />
  );
}
