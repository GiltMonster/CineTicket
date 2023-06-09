import React, { useState, useEffect } from "react";
import CadeirasSelecionadas from "./CadeirasSelecionadas";
import FormaPagamento from "./FormaPagamento";
import "../../style/Accordion.css";

function Accordion({ selectedSeats, valorTotal, toggleSeatsLock }) {
  const [isExpanded, setExpanded] = useState(false);
  const [activeTab, setActiveTab] = useState(0);

  useEffect(() => {
    setExpanded(selectedSeats.length > 0);
  }, [selectedSeats]);

  const handleConfirmarClick = () => {
    toggleSeatsLock();
    setActiveTab(1);
  };

  const calcularTotal = () => {
    valorTotal = selectedSeats.length * 25
    return valorTotal;
  };

  return (
    <div className="accordion">
      <div className={`accordion-header-first ${activeTab === 0 ? "active" : ""}`}>
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
      <div className={`accordion-header ${activeTab === 1 ? "active" : ""}`}>
        Forma de Pagamento
      </div>
      {activeTab === 1 && (
        <div className={`accordion-content ${activeTab === 1 ? "active" : ""}`}>
          <FormaPagamento valorTotal={calcularTotal()} />
        </div>
      )}
    </div>
  );
}

export default Accordion;
