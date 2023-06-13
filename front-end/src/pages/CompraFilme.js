import { useEffect, useState } from "react";
import Accordion from "../components/compra/Accordion";
import CadeirasCine from "../components/compra/cadeirasCine";
import InfoFilme from "../components/compra/infoFilmes";
import "../style/CompraFilme.css";
import axios from "axios";

export default function CompraFilme() {

    const [logged, setLogged] = useState({});
    const [login, setLogin] = useState({});

    const [filmeCompra, setFilmeCompra] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [valorTotal, setValorTotal] = useState(0);
    const [isSeatsLocked, setSeatsLocked] = useState(false);

    const [infoSection, setInfoSection] = useState({
        filmeId: "",
        dataSection: "",
        sala: "",
        horario: "",
        legDub: ""
    });

    const dataIngresso = {
        quantidade: selectedSeats.length,
        cadeiras: selectedSeats,
        valorUnitario: 25.00,
        emailCliente: login?.email,
        idSessao: infoSection.sala
    };


    // Função para fazer as requisições POST
    function salvaObjetos() {
        //https://axios-http.com/ptbr/docs/post_example
        // Dados da requisição 1
        const requestDataSection = {
            dataSessao: "2023-05-20",
            idFilme: infoSection.filmeId,
            nomeFilme: filmeCompra?.title,
            idSala: infoSection.sala
        };

        // Dados da requisição 2
        const requestDataIngresso = dataIngresso;

        // Envia as requisições POST em paralelo usando axios.all()
        axios.all([
            //sessoes:
            axios.post('http://localhost:8080/api/sessoes', requestDataSection),
            axios.post('http://localhost:8080/api/ingressos', requestDataIngresso),
        ])
            .then(axios.spread((response1, response2) => {
                // Processa as respostas individuais
                // Imprime os dados da resposta 1 no console
                console.log(response1.data);
                // Imprime os dados da resposta 2 no console
                console.log(response2.data);
            }))
            .catch(error => {
                // Trata os erros caso ocorra algum problema em alguma das requisições
                console.error(error);
            });
    }

    function handleSeatClick(seatNumber) {
        setSelectedSeats((prevSelectedSeats) => {
            if (prevSelectedSeats.includes(seatNumber)) {
                return prevSelectedSeats.filter((seat) => seat !== seatNumber);
            } else {
                return [...prevSelectedSeats, seatNumber];
            }
        });

        // Calcular o valor total
        const total = selectedSeats.length * 25;
        setValorTotal(total);
    };

    function toggleSeatsLock() {
        setSeatsLocked((prevIsSeatsLocked) => !prevIsSeatsLocked);
    }

    function verificaLogado() {
        if (logged === undefined) {
            console.log("Logue")

        }else{
            const logado = localStorage.getItem('login')
            setLogged(logado);
            // Converte este json para objeto
            setLogin(JSON.parse(logado));
        }
    }

    useEffect(() => {
        // Recupera o json do localStorage
        verificaLogado();

        console.log("Info da sessao:");
        console.log(infoSection);
        //mostra o objeto pro ingresso:
        console.log("Dados do ingresso:");
        console.log(dataIngresso);

    }, [selectedSeats, logged])

    return (
        <div className="containerCompra">
            <div className="cadeirasEaccordion">
                <InfoFilme setInfoSection={setInfoSection} setFilmeCompra={setFilmeCompra} />
                <CadeirasCine handleSeatClick={handleSeatClick} selectedSeats={selectedSeats} isSeatsLocked={isSeatsLocked} />
            </div>
            <Accordion selectedSeats={selectedSeats} valorTotal={valorTotal} toggleSeatsLock={toggleSeatsLock} isLogado={login} />
        </div>
    )
}