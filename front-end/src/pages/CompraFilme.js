import { useState } from "react";
import Accordion from "../components/compra/Accordion";
import CadeirasCine from "../components/compra/cadeirasCine";
import InfoFilme from "../components/compra/infoFilmes";
import "../style/CompraFilme.css"

export default function CompraFilme() {

    const [selectedSeats, setSelectedSeats] = useState([]);

    const handleSeatClick = (seatNumber) => {
        setSelectedSeats((prevSelectedSeats) => {
            if (prevSelectedSeats.includes(seatNumber)) {
                return prevSelectedSeats.filter((seat) => seat !== seatNumber);
            } else {
                return [...prevSelectedSeats, seatNumber];
            }
        });
    };

    return (
        <div className="containerCompra">
            <InfoFilme />
            <div className="cadeirasEaccordion">
                <CadeirasCine handleSeatClick={handleSeatClick} selectedSeats={selectedSeats} />
                <Accordion selectedSeats={selectedSeats} />
            </div>
        </div>
    )
}