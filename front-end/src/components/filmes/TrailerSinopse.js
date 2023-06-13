import React, { useContext, useState } from "react";
import { ContextFilm } from "../../context/filmeContext";
import Loading from "../loading";
import { Link } from "react-router-dom";
import { addDays, format } from "date-fns";

import "../../style/TrailerSinopse.css";

export default function TrailerSinopse() {
  const filme = useContext(ContextFilm);
  const [selectedDate, setSelectedDate] = useState(new Date());

  const showtimesData = [
    { room: "Sala 1", time: "18:45", type: "DUB" },
    { room: "Sala 2", time: "20:50", type: "LEG" },
    { room: "Sala 3", time: "21:35", type: "DUB" },
    { room: "Sala 4", time: "23:50", type: "LEG" },
    { room: "Sala 5", time: "19:30", type: "DUB" },
    // Adicione mais objetos para mais salas, horários e opções de dublagem/legendagem
  ];

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  const today = new Date();
  const tomorrow = addDays(today, 1);
  const dayAfterTomorrow = addDays(today, 2);

  return filme ? (
    <div>
      <div className="trailer-and-sinopse">
        <div className="sinopse">
          {/* <h2>Sinopse</h2> */}
          {filme.overview ? (
            <p>{filme.overview}</p>
          ) : (
            <p>A sinopse deste filme não está disponível no momento. Por favor, verifique novamente mais tarde ou consulte informações adicionais sobre o filme.</p>
          )}
        </div>
        <div className="showtime-items-container">
          <div className="date-selector">
            <button
              className={`date-button ${format(selectedDate, "yyyy-MM-dd") === format(today, "yyyy-MM-dd") ? "selected" : ""}`}
              onClick={() => handleDateChange(today)}
            >
              Hoje
            </button>
            <button
              className={`date-button ${format(selectedDate, "yyyy-MM-dd") === format(tomorrow, "yyyy-MM-dd") ? "selected" : ""}`}
              onClick={() => handleDateChange(tomorrow)}
            >
              {format(tomorrow, "dd/MM")}
            </button>
            <button
              className={`date-button ${format(selectedDate, "yyyy-MM-dd") === format(dayAfterTomorrow, "yyyy-MM-dd") ? "selected" : ""}`}
              onClick={() => handleDateChange(dayAfterTomorrow)}
            >
              {format(dayAfterTomorrow, "dd/MM")}
            </button>

          </div>
          <div className="showtime-items">
            {showtimesData.map((showtime, index) => (
              <div className="showtime-item" key={index}>
                <div className="time-box">
                  <div className="room">{showtime.room}</div>
                  <Link className="time" to={`/filme/${filme.id}/info/${showtime.room}/${showtime.time}/${showtime.type}`}>
                    {showtime.time}
                  </Link>
                  <div className="type">{showtime.type}</div>
                </div>
              </div>
            ))}
          </div>
        </div>

        <div className="trailer">
          {/* <h2>Trailer</h2> */}
          <iframe
            width="560"
            height="315"
            src={`https://www.youtube.com/embed/${filme?.videos?.results[0]?.key}`}
            frameBorder="0"
            allowFullScreen
            title="Trailer"
          ></iframe>
        </div>
      </div>
    </div>
  ) : (
    <Loading />
  );
}
