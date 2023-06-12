import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import CadeirasSelecionadas from "./CadeirasSelecionadas";
import FormaPagamento from "./FormaPagamento";
import "../../style/Accordion.css";

function Accordion({ selectedSeats, valorTotal, toggleSeatsLock, isLogado }) {
  const [isExpanded, setExpanded] = useState(false);
  const [activeTab, setActiveTab] = useState(0);
  const [isPaymentSelected, setPaymentSelected] = useState(false);
  const [isFinished, setFinished] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setExpanded(selectedSeats.length > 0);
  }, [selectedSeats]);

  const handleConfirmarClick = () => {
    toggleSeatsLock();
    setActiveTab(1);
    setPaymentSelected(false);
  };

  const handleBackClick = () => {
    toggleSeatsLock();
    setActiveTab(0);
  };

  const handleFinishClick = () => {
    setActiveTab(2);
    setFinished(true);

    setTimeout(() => {
      // Redireciona o usuário para a página inicial
      navigate("/");
    }, 5000);
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
          <button className="accordion-confirm-button" onClick={handleConfirmarClick}>Continuar</button>
        </div>
      )}
      <div className={`accordion-header ${activeTab === 1 ? "active" : ""}`}>
        Forma de Pagamento
      </div>{
        isLogado !== undefined && isLogado !== null ?
          activeTab === 1 && (
            <div className={`accordion-content ${activeTab === 1 ? "active" : ""}`}>
              <FormaPagamento valorTotal={calcularTotal()} onPaymentSelect={() => setPaymentSelected(true)} />
              <button className="accordion-back-button" onClick={handleBackClick}>Voltar</button>
              <button className="accordion-finish-button" disabled={!isPaymentSelected} onClick={handleFinishClick}>Finalizar Compra</button>
            </div>
          )
          
          :
          activeTab === 1 && (
            <div className="accordion-content">
              <h2>Logue para continuar:</h2>

              <div className="accordion-content-login">
                <Link className="loginAccordion" to={"/login"}>Login</Link>
                <button className="loginAccordion-back" onClick={handleBackClick}>Voltar</button>
              </div>
            </div>
          )
      }
      <div className={`accordion-header ${activeTab === 2 ? "active" : ""}`}>
        Finalizando Compra
      </div>
      {activeTab === 2 && isFinished && (
        <div className={`accordion-content ${activeTab === 2 ? "active" : ""}`}>
          <h2>Compra Finalizada!</h2>
          <p>Parabéns! Sua compra foi concluída com sucesso.</p>
          <p>Você será redirecionado para a página inicial em 5 segundos...</p>
        </div>
      )}
    </div>
  );
}

export default Accordion;
