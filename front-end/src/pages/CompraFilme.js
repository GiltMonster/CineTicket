import { useEffect, useState } from "react";
import Accordion from "../components/compra/Accordion";
import CadeirasCine from "../components/compra/cadeirasCine";
import InfoFilme from "../components/compra/infoFilmes";
import "../style/CompraFilme.css";
import axios from "axios";

export default function CompraFilme() {

    const [logged, setLogged] = useState({});
    const [login, setLogin] = useState({});
    const [sessaoPesquisada, setSessaoPesquisada] = useState({});

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

    //sessao - FEITO
    const requestDataSection = {
        dataSessao: infoSection.dataSection,
        idFilme: parseInt(infoSection.filmeId),
        nomeFilme: filmeCompra?.title,
        idSala: formataIdSALA(infoSection.sala)
    };

    //sala - FEITO
    const requestDataSala = {
        idSala: formataIdSALA(infoSection.sala),
        legendado: legendadoDublado(infoSection.legDub)
    };

    const requestDataIngresso = {
        quantidade: selectedSeats.length,
        cadeiras: selectedSeats,
        valorUnitario: 25.00,
        emailCliente: login?.email,
        idSessao: sessaoPesquisada?.idSessao
    };
    
    useEffect(() => {
        // Recupera o json do localStorage
        verificaLogado();

        verificarIdFilmeExiste(requestDataSection.idFilme);

        console.log("Info da sessao:");
        console.log(requestDataSection);
        //mostra o objeto pro ingresso:
        console.log("Dados do ingresso:");
        console.log(requestDataIngresso);

        // console.log("Dados do sala:");
        // console.log(requestDataSala);

    }, [selectedSeats, logged, infoSection])
    
    function legendadoDublado(dl) {
        if (dl === "LEG") {
            return true;
        } else {
            return false;
        }
    }

    function formataIdSALA(sala) {
        return sala.replace(/ /g, "");
    }

    function verificarIdFilmeExiste(idFilme) {
        return axios
          .get(`http://localhost:8080/api/sessoes`)
          .then((res) => {
            const sessaoEncontrada = res.data.find(
              (sessao) => sessao.filme.idFilme === parseInt(idFilme)
            );
    
            if (sessaoEncontrada) {
              setSessaoPesquisada(sessaoEncontrada);
              console.log("achei");
              return true;
            } else {
              console.log("não achei");
              return false;
            }
          })
          .catch((error) => {
            console.error(error);
          });
      }
    
      function comprar() {
        const idFilme = requestDataSection.idFilme;
        verificarIdFilmeExiste(idFilme).then((idFilmeExiste) => {
          if (idFilmeExiste) {
              axios.post("http://localhost:8080/api/ingressos", requestDataIngresso)
              .then(
                axios.spread((response1) => {
                  console.log(response1.data);
                })
              )
              .catch((error) => {
                console.error(error);
              });
          } else {
            axios
              .post(`http://localhost:8080/api/sessoes`, requestDataSection)
              .then((res) => {
                console.log(res);
              })
              .catch((error) => {
                console.error(error);
              });
          }
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

        } else {
            const logado = localStorage.getItem('login')
            setLogged(logado);
            // Converte este json para objeto
            setLogin(JSON.parse(logado));
        }
    }


    return (
        <div className="containerCompra">
            <div className="cadeirasEaccordion">
                <InfoFilme setInfoSection={setInfoSection} setFilmeCompra={setFilmeCompra} />
                <CadeirasCine handleSeatClick={handleSeatClick} selectedSeats={selectedSeats} isSeatsLocked={isSeatsLocked} />
            </div>
            <Accordion selectedSeats={selectedSeats} valorTotal={valorTotal} toggleSeatsLock={toggleSeatsLock} isLogado={login} comprar={comprar} />
        </div>
    )
}