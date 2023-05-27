import React, { useEffect, useRef } from "react";
import "../../style/carrosselFilmes.css";
import Loading from "../loading";
import { Link } from "react-router-dom";

export default function CarrosselFilmes(filmes) {
  const movieContainerRef = useRef(null);
  const movieWidthRef = useRef(0);
  let currentIndex = 0;
  let interval = null;

  useEffect(() => {
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

    return () => {
      clearInterval(interval);
    };
  }, []);

  const scrollLeft = () => {
    const movieContainer = movieContainerRef.current;
    currentIndex--;
    if (currentIndex < 0) {
      currentIndex = 0;
    }

    const translateXValue = -currentIndex * movieWidthRef.current;
    movieContainer.style.transform = `translateX(${translateXValue}px)`;
  };

  const scrollRight = () => {
    const movieContainer = movieContainerRef.current;
    const containerWidth = movieContainer.offsetWidth;
    const moviesWidth = (movieWidthRef.current + 10) * (movieContainer.children.length);
  
    const maxScroll = moviesWidth - containerWidth;
    let scrollAmount = currentIndex * (movieWidthRef.current + 10);
    scrollAmount += movieWidthRef.current + 10;
  
    if (scrollAmount > maxScroll) {
      scrollAmount = maxScroll;
    }
  
    movieContainer.style.transform = `translateX(-${scrollAmount}px)`;
    currentIndex = Math.floor(scrollAmount / (movieWidthRef.current + 10));
  };

  return (

    <>

<div className="title-filme">
    <h1>Filmes de pesquisa populares:</h1>

</div>

    
      {


        filmes ?


          <div className="container">
            <div className="carousel">
              <div className="movie-container" ref={movieContainerRef}>
                {

                  filmes.filme.map(filme => {
                    return (
                      <div className="movie" key={filme.id}>
                        <Link to={`/filme/${filme.id}`}>
                          <img src={`https://image.tmdb.org/t/p/original/${filme.poster_path}`} alt={`Imagem do filme: ${filme.title}`} />
                          <h2 className="title">{filme.title}</h2>
                        </Link>
                      </div>
                    )
                  })
                }
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

          <Loading />

      }
    </>
  );
}
