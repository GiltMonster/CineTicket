import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import "../../style/Header.css";

export default function PesquisaFilmes() {
  const [pesquisa, setPesquisa] = useState("");
  const navigate = useNavigate();

  const handleKeyPress = (e) => {
    if (e.key === "Enter") {
      navigate(`/pesquisarFilme/${pesquisa}`);
      window.location.reload();
    }
  };

  return (
    <>
      <Link
        reloadDocument
        className="search-icon"
        to={`/pesquisarFilme/${pesquisa}`}
      >
        <FaSearch />
      </Link>
      <input
        type="text"
        id="pesquisa"
        placeholder="Pesquisar:"
        value={pesquisa}
        onChange={(e) => setPesquisa(e.target.value)}
        onKeyDown={handleKeyPress}
      />
    </>
  );
}
