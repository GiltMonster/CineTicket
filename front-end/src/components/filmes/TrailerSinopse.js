import "../../style/TrailerSinopse.css"
import { useContext, useEffect, useState } from "react";
import { ContextFilm } from "../../context/filmeContext";
import Loading from "../loading";


export default function TrailerSinopse() {
    const filme = useContext(ContextFilm);

    
    
    const showtimesData = [
        { room: "Sala 1", time: "18:45", type: "DUB" },
        { room: "Sala 2", time: "20:50", type: "LEG" },
        { room: "Sala 3", time: "21:35", type: "DUB" },
        { room: "Sala 4", time: "23:50", type: "LEG" },
        { room: "Sala 5", time: "19:30", type: "DUB" },
        { room: "Sala 6", time: "20:00", type: "LEG" },
        // Adicione mais objetos para mais salas, horários e opções de dublagem/legendagem
    ];
    
    return (
        filme ?
        <div>
            <div className="trailer-and-sinopse">
                <div className="trailer">
                    <h2>Trailer</h2>
                    <iframe
                        width="560"
                        height="315"
                        src={`https://www.youtube.com/embed/${filme?.videos?.results[0]?.key}`}
                        frameBorder="0"
                        allowFullScreen
                        title="Trailer"
                    ></iframe>
                </div>

                <div className="sinopse">
                    <h2>Sinopse</h2>
                    <p>{filme.overview}</p>
                </div>
            </div>

            <div className="showtime-items">
                {showtimesData.map((showtime, index) => (
                    <div className="showtime-item" key={index}>
                        <div className="time-box">
                            <div className="room">{showtime.room}</div>
                            <a href="outra_tela.html" className="time">
                                {showtime.time}
                            </a>
                            <div className="type">{showtime.type}</div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
        :

        <Loading/>
    );
}
