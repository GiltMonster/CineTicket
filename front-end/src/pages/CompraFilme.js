import { useEffect, useState } from "react";
import Accordion from "../components/compra/Accordion";
import CadeirasCine from "../components/compra/cadeirasCine";
import InfoFilme from "../components/compra/infoFilmes";
import "../style/CompraFilme.css";

export default function CompraFilme() {

    const [logged, setLogged] = useState({});
    const [login, setLogin] = useState({});

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
        quantidade:  selectedSeats.length,
        cadeiras: selectedSeats,
        valorUnitario: 25.00,
        emailCliente: login.email,
        idSessao: infoSection.sala
    };

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
        
        console.log(infoSection);
        //mostra o objeto pro ingresso:
        console.log(dataIngresso);

    }, [selectedSeats, logged])

    return (
        <div className="containerCompra">
            <div className="cadeirasEaccordion">
                <InfoFilme setInfoSection={setInfoSection}/>
                <CadeirasCine handleSeatClick={handleSeatClick} selectedSeats={selectedSeats} isSeatsLocked={isSeatsLocked} />
            </div>
            <Accordion selectedSeats={selectedSeats} valorTotal={valorTotal} toggleSeatsLock={toggleSeatsLock} isLogado={login}/>
        </div>
    )
}