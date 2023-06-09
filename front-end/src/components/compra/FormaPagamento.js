import React, { useState, useEffect } from "react";
import "../../style/FormaPagamento.css";

const FormaPagamento = () => {
    const [selectedPayment, setSelectedPayment] = useState("");
    const [cardNumber, setCardNumber] = useState("");
    const [expirationDate, setExpirationDate] = useState("");
    const [cvv, setCvv] = useState("");
    const [cardHolderName, setCardHolderName] = useState("");

    const handlePaymentChange = (event) => {
        setSelectedPayment(event.target.value);
    };

    const handleCardNumberChange = (event) => {
        setCardNumber(event.target.value);
    };

    const handleExpirationDateChange = (event) => {
        setExpirationDate(event.target.value);
    };

    const handleCvvChange = (event) => {
        setCvv(event.target.value);
    };

    const handleCardHolderNameChange = (event) => {
        setCardHolderName(event.target.value);
    };

    useEffect(() => {
        // Gerar valores aleatórios para simular preenchimento do cartão
        setCardNumber("1234 5678 9012 3456");
        setCardHolderName("Fulano Beltrano Cicrano");
        setExpirationDate("12/23");
        setCvv("123");
    }, []);

    return (
        <div className="forma-pagamento">
            <h3>Selecione a forma de pagamento:</h3>
            <div className={`payment-option ${selectedPayment === "cartaoCredito" ? "option-selected" : ""}`}>
                <label>
                    <input
                        type="radio"
                        name="payment"
                        value="cartaoCredito"
                        checked={selectedPayment === "cartaoCredito"}
                        onChange={handlePaymentChange}
                    />
                    Cartão de Crédito
                </label>
            </div>
            {selectedPayment === "cartaoCredito" && (
                <div>
                    <label>Número do cartão:</label>
                    <input
                        type="text"
                        value={cardNumber}
                        onChange={handleCardNumberChange}
                    />
                    <label>Nome do titular:</label>
                    <input
                        type="text"
                        value={cardHolderName}
                        onChange={handleCardHolderNameChange}
                    />
                    <label>Data de expiração:</label>
                    <input
                        type="text"
                        value={expirationDate}
                        onChange={handleExpirationDateChange}
                    />
                    <label>CVV:</label>
                    <input type="text" value={cvv} onChange={handleCvvChange} />
                </div>
            )}
            <div>
                <label className={`payment-option ${selectedPayment === "cartaoDebito" ? "option-selected" : ""}`}>
                    <input
                        type="radio"
                        name="payment"
                        value="cartaoDebito"
                        checked={selectedPayment === "cartaoDebito"}
                        onChange={handlePaymentChange}
                    />
                    Cartão de Débito
                </label>
            </div>
            {selectedPayment === "cartaoDebito" && (
                <div>
                    <label>Número do cartão:</label>
                    <input
                        type="text"
                        value={cardNumber}
                        onChange={handleCardNumberChange}
                    />
                    <label>Nome do titular:</label>
                    <input
                        type="text"
                        value={cardHolderName}
                        onChange={handleCardHolderNameChange}
                    />
                    <label>Data de expiração:</label>
                    <input
                        type="text"
                        value={expirationDate}
                        onChange={handleExpirationDateChange}
                    />
                    <label>CVV:</label>
                    <input type="text" value={cvv} onChange={handleCvvChange} />
                </div>
            )}
            <div className={`payment-option ${selectedPayment === "pix" ? "option-selected" : ""}`}>
                <label>
                    <input
                        type="radio"
                        name="payment"
                        value="pix"
                        checked={selectedPayment === "pix"}
                        onChange={handlePaymentChange}
                    />
                    Pix
                </label>
            </div>
            {selectedPayment === "pix" && (
                <div>
                    <p>Escaneie o QR code abaixo para realizar o pagamento:</p>
                    {/* Aqui você pode adicionar o código para exibir o QR code */}
                </div>
            )}
        </div>
    );
};

export default FormaPagamento;
