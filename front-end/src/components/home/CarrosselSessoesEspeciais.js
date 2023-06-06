import React, { useEffect, useState } from "react";
import "../../style/CarrosselSessoesEspeciais.css";
import { Link } from "react-router-dom";

export default function CarrosselSessoesEspeciais({ slides }) {
    const [slideIndex, setSlideIndex] = useState(0);

    useEffect(() => {
        const interval = setInterval(() => {
            plusSlides(1);
        }, 5000);

        return () => {
            clearInterval(interval);
        };
    }, [slideIndex]);

    const plusSlides = (n) => {
        let newIndex = slideIndex + n;
        const slidesCount = slides.length;

        if (newIndex >= slidesCount) {
            newIndex = 0;
        }
        if (newIndex < 0) {
            newIndex = slidesCount - 1;
        }
        setSlideIndex(newIndex);
    };

    const currentSlide = (n) => {
        setSlideIndex(n - 1);
    };

    useEffect(() => {
        showSlides();
    }, [slideIndex]);

    const showSlides = () => {
        const slidesElements = document.getElementsByClassName("mySlides");
        const dots = document.getElementsByClassName("dot");

        for (let i = 0; i < slidesElements.length; i++) {
            slidesElements[i].style.display = "none";
        }

        for (let i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }

        slidesElements[slideIndex].style.display = "block";
        dots[slideIndex].className += " active";
    };

    return (
        <div>
            <div className="slideshow-container">
                {slides.map((slide, index) => (
                    <div className="mySlides fade" key={index}>
                        <Link to={`/listas/${slide.listId}/${slide.mesParametro}`}>
                        <img src={slide.image} style={{ width: "100%" }} alt={`Slide ${index + 1}`} />
                        </Link>
                    </div>
                ))}

                <a className="prev" onClick={() => plusSlides(-1)}>
                    &#10094;
                </a>
                <a className="next" onClick={() => plusSlides(1)}>
                    &#10095;
                </a>
            </div>

            <div style={{ textAlign: "center" }}>
                {slides.map((_, index) => (
                    <span
                        className={`dot ${slideIndex === index ? "active" : ""}`}
                        onClick={() => currentSlide(index + 1)}
                        key={index}
                    ></span>
                ))}
            </div>
        </div>
    );
}
