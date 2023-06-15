import React from "react";
import "../../style/IngressoImpressao.css";

function IngressoImpressao({ ingressos }) {
  return (
    <div className="ingresso-impressao">
      <h2>Ingressos Adquiridos</h2>
      {ingressos.map((ingresso, index) => (
        <div key={index} className="ingresso">
          <h3>Cine Ticket</h3>
          <h4>Filme: {ingresso.filme}</h4>
          <p>Data da sessão: {formatarData(ingresso.dataSessao)}</p>
          <p>Hora da sessão: {ingresso.horaSessao}</p>
          <p>Sala: {ingresso.sala}</p>
          <p>Assento: {ingresso.assento}</p>
        </div>
      ))}
    </div>
  );
}

function formatarData(data) {
  const partes = data.split("-");
  const dia = partes[2];
  const mes = partes[1];
  const ano = partes[0];
  return `${dia}-${mes}-${ano}`;
}

export default IngressoImpressao;
