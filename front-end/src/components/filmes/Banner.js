import React from "react";
import "../../style/Banner.css";

export default function Banner() {
  const ageRating = 14;
  const genres = ["Ação", "Aventura", "Comédia"];

  // Função para mapear a idade para a cor correspondente
  const getAgeRatingColor = (age) => {
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
      return "#008000";
    }
  };

  const ageRatingColor = getAgeRatingColor(ageRating);

  return (
    <div className="movie-banner">
      <div className="movie-info">
        <h1>Guardiões da Galaxia Vol. 3</h1>
        <div className="genres">
          {genres.map((genre, index) => (
            <p className="genre-border" key={index}>
              {genre}
            </p>
          ))}
        </div>
        <div className="age-rating" style={{ backgroundColor: ageRatingColor }}>
          {ageRating}
        </div>
      </div>
      <img
        src="https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/3abbc4de-fd40-48f8-81eb-c98a41af7745.webp"
        alt="Banner do Filme"
        className="banner-image"
      />
      <div className="movie-poster">
        <img
          src="https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/36434a76-1343-4598-ad2f-fadff2122e94.jpg"
          alt="Poster do Filme"
          className="poster-image"
        />
      </div>
    </div>
  );
}
