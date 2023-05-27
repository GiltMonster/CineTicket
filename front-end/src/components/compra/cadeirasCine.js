import "../../style/cadeirasCine.css";

export default function CadeirasCine() {
    const seats = Array.from({ length: 175 }, (_, index) => index + 1);

    const handleSeatClick = (seatNumber) => {
        const seat = document.getElementById(`seat-${seatNumber}`);
        if (seat && !seat.classList.contains("occupied")) {
            seat.classList.toggle("selected");
        }
    };

    const isSpaceBetween = (seatNumber) => {
        const spaceSeats = [27, 43, 59, 75, 91, 107, 123, 139, 155, 169];
        return spaceSeats.includes(seatNumber);
    };

    const hasBottomMargin = (seatNumber) => {
        return (
            (seatNumber >= 52 && seatNumber <= 67) ||
            (seatNumber >= 116 && seatNumber <= 131)
        );
    };

    return (
        <div className="container-cadeira">
            <div className="seats">
                {seats.map((seatNumber) => {
                    const seatClassName = isSpaceBetween(seatNumber) ? "seat space" : "seat";
                    const seatStyle = hasBottomMargin(seatNumber) ? { marginBottom: "15px" } : {};

                    return (
                        <div
                            key={seatNumber}
                            id={`seat-${seatNumber}`}
                            className={seatClassName}
                            style={seatStyle}
                            onClick={() => handleSeatClick(seatNumber)}
                        >
                            {seatNumber}
                        </div>
                    );
                })}
            </div>
            <div className="screen">Tela</div>
            <div className="legend">
                <div className="legend-item">
                    <div className="seat available"></div>
                    <span>Disponível</span>
                </div>
                <div className="legend-item">
                    <div className="seat selected"></div>
                    <span>Selecionada</span>
                </div>
                <div className="legend-item">
                    <div className="seat occupied"></div>
                    <span>Ocupada</span>
                </div>
            </div>
        </div>
    );
}
