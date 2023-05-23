import React, { useEffect, useRef } from "react";
import "../../style/carrosselFilmes.css";
import Loading from "../loading";

export default function CarrosselFilmes(filmes) {
  const movieContainerRef = useRef(null);
  const movieWidthRef = useRef(0);
  let currentIndex = 0;
  let interval = null;

  const carousel = document.querySelector(".carousel");
  const movies = document.querySelectorAll(".movie");
  const movieContainer = movieContainerRef.current;
  movieWidthRef.current = movies[0].offsetWidth;

  function nextSlide() {
    currentIndex++;
    if (currentIndex >= movies.length - 2) {
      // Reinicie o carrossel no final
      currentIndex = 0;
    }

    const translateXValue = -currentIndex * movieWidthRef.current;
    movieContainer.style.transform = `translateX(${translateXValue}px)`;
  }

  // Inicie o carrossel automaticamente
  interval = setInterval(nextSlide, 5000);

  // Pausar o carrossel quando o mouse estiver sobre ele
  carousel.addEventListener("mouseover", () => {
    clearInterval(interval);
  });

  // Retomar o carrossel quando o mouse sair
  carousel.addEventListener("mouseout", () => {
    interval = setInterval(nextSlide, 5000);
  });

  // Pausar o carrossel quando o mouse estiver sobre um dos botões
  const scrollButtons = document.querySelectorAll(".scroll-button");
  scrollButtons.forEach((button) => {
    button.addEventListener("mouseover", () => {
      clearInterval(interval);
    });

    button.addEventListener("mouseout", () => {
      interval = setInterval(nextSlide, 5000);
    });
  });
  useEffect(() => {

    console.log(filmes)

    return () => {
      clearInterval(interval);
    };
  }, []);

  const scrollLeft = () => {
    const movieContainer = movieContainerRef.current;
    currentIndex--;
    if (currentIndex = 0) {
      // Voltar para o último filme
      currentIndex = 0;
    }

    const translateXValue = -currentIndex * movieWidthRef.current;
    movieContainer.style.transform = `translateX(${translateXValue}px)`;
  };

  const scrollRight = () => {
    const movieContainer = movieContainerRef.current;
    currentIndex++;
    if (currentIndex >= movieContainer.children.length - 3) {
      // Reiniciar o carrossel no início
      currentIndex = movieContainer.children.length - 3;
    }

    const translateXValue = -currentIndex * movieWidthRef.current;
    movieContainer.style.transform = `translateX(${translateXValue}px)`;
  };

  return (

  filmes ?    
    <div className="container">
      <div className="carousel">
        <div className="movie-container" ref={movieContainerRef}>
          <div className="movie">
            <a href="#">
              <img
                src={
                  "https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/36434a76-1343-4598-ad2f-fadff2122e94.jpg"
                }
                alt="Filme 1"
              />
              <h2 className="title">Batmão</h2>
            </a>
          </div>
          <div className="movie">
            <a href="#">
              <img
                src={
                  "https://ingresso-a.akamaihd.net/prd/img/movie/homem-aranha-atraves-do-aranhaverso-parte-1/216f2cb7-3a64-4b7a-a338-709bcde71147.webp"
                }
                alt="Filme 2"
              />
              <h2 className="title">Miranha</h2>
            </a>
          </div>
          <div className="movie">
            <a href="#">
              <img
                src={
                  "https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/36434a76-1343-4598-ad2f-fadff2122e94.jpg"
                }
                alt="Filme 3"
              />
              <h2 className="title">Guardiões da Galáxia Vol.3</h2>
            </a>
          </div>
          <div className="movie">
            <a href="#">
              <img
                src={
                  "https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/36434a76-1343-4598-ad2f-fadff2122e94.jpg"
                }
                alt="Filme 4"
              />
              <h2 className="title">Guardiões da Galáxia Vol.3</h2>
            </a>
          </div>
          <div className="movie">
            <a href="#">
              <img
                src={
                  "https://ingresso-a.akamaihd.net/prd/img/movie/guardioes-da-galaxia-vol-3/36434a76-1343-4598-ad2f-fadff2122e94.jpg"
                }
                alt="Filme 3"
              />
              <h2 className="title">Guardiões da Galáxia Vol.3</h2>
            </a>
          </div>
        </div>
      </div>
      <button className="scroll-button scroll-button-left" onClick={scrollLeft}>
        &lt;
      </button>
      <button className="scroll-button scroll-button-right" onClick={scrollRight}>
        &gt;
      </button>
    </div>

       :

    <Loading/>
  );
}
