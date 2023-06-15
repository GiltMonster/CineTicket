import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import "../../style/Header.css";

export default function PesquisaFilmes() {
  const [pesquisa, setPesquisa] = useState("");
  const navigate = useNavigate();

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      realizarPesquisa();
    }
  };

  const realizarPesquisa = () => {
    if (pesquisa.trim() !== "") {
      navigate(`/pesquisarFilme/${pesquisa}`);
      window.location.reload();
    }
  };

  return (
    <>
      <input
        type="text"
        id="pesquisa"
        placeholder="Pesquisar:"
        value={pesquisa}
        onChange={(e) => setPesquisa(e.target.value)}
        onKeyDown={handleKeyPress}
      />
      <Link
        reloadDocument
        className={`search-icon ${pesquisa.trim() === "" ? "disabled" : ""}`}
        to={`/pesquisarFilme/${pesquisa}`}
        onClick={(e) => {
          if (pesquisa.trim() === "") {
            e.preventDefault();
          }
        }}
      >
        <FaSearch />
      </Link>
    </>
  );
}
