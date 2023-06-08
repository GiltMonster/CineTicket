import React, { useState, useEffect } from "react";
import "../../style/Accordion.css";

function Accordion({ selectedSeats }) {
  const [isExpanded, setExpanded] = useState(selectedSeats.length > 0);

  useEffect(() => {
    setExpanded(selectedSeats.length > 0);
  }, [selectedSeats]);

  return (
    <div>
      <div className="accordion-header" onClick={() => {}}>
        {isExpanded ? "Informações do Assento" : "Seleção de Assentos"}
      </div>
      {isExpanded && (
        <div className="accordion-content">
          <div>{`${selectedSeats.length} cadeiras selecionadas:`}</div>
          <div>{selectedSeats.join(", ")}</div>
        </div>
      )}
    </div>
  );
}

export default Accordion;
