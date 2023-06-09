import React, { useState, useEffect } from "react";
import CadeirasSelecionadas from "./CadeirasSelecionadas";
// import FormaPagamento from "./FormaPagamento";
import "../../style/Accordion.css";

function Accordion({ selectedSeats }) {
  const [isExpanded, setExpanded] = useState(false);
  const [activeTab, setActiveTab] = useState(0);

  useEffect(() => {
    setExpanded(selectedSeats.length > 0);
  }, [selectedSeats]);

  const handleTabClick = (tabIndex) => {
    setActiveTab(tabIndex);
  };

  const handleConfirmarClick = () => {
    setActiveTab(1);
  };

  const calcularTotal = () => {
    return selectedSeats.length * 25;
  };

  return (
    <div className="accordion">
      <div className={`accordion-header-first ${activeTab === 0 ? "active" : ""}`} onClick={() => handleTabClick(0)}>
        Seleção de Assentos
      </div>
      {isExpanded && activeTab === 0 && (
        <div className={`accordion-content ${activeTab === 0 ? "active" : ""}`}>
          <CadeirasSelecionadas selectedSeats={selectedSeats} />
          <div className="total">
            <h3>Total: {calcularTotal().toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</h3>
          </div>
          <button className="accordion-confirm-button" onClick={handleConfirmarClick}>Confirmar</button>
        </div>
      )}
      <div className={`accordion-header ${activeTab === 1 ? "active" : ""}`} onClick={() => handleTabClick(1)}>
        Forma de Pagamento
      </div>
      {activeTab === 1 && (
        <div className={`accordion-content ${activeTab === 1 ? "active" : ""}`}>
          {/* <FormaPagamento /> */}
          <h1>Teste</h1>
        </div>
      )}
    </div>
  );
}

export default Accordion;
