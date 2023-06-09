import React from "react";
import "../../style/CadeirasSelecionadas.css";

function CadeirasSelecionadas({ selectedSeats }) {
    const sortedSeats = selectedSeats.sort((a, b) => a - b);

    return (
        <div>
            <div className="cadeiras-selecionadas">
                <h4>Cadeiras Selecionadas:</h4>
                {sortedSeats.map((seat) => (
                    <div className="cadeira" key={seat}>{seat}</div>
                ))}
            </div>
        </div>
    );
}

export default CadeirasSelecionadas;
