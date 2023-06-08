import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Filme from "./pages/Filme";
import NotFound from "./pages/NotFound";
import Header from "./components/headbar/Header";
import FilmesPesquisados from "./components/headbar/FilmesPesquisados";
import EsqueceuSenha from "./components/login/EsqueceuSenha";
import Cadastro  from "./components/login/CadastrarUsuario";
import SessoesEspeciais from "./components/sessoes especiais/sessoesEspeciais";
import CompraFilme from "./pages/CompraFilme";

function App() {
  return (

    <>
      <Header />
      <Routes>
        <Route path="/" index element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/login/redefinirSenha" element={<EsqueceuSenha />} />
        <Route path="/login/cadastro" element={<Cadastro />} />
        <Route path="/pesquisarFilme/:nomeFilme" element={<FilmesPesquisados/>} />
        <Route path="/filme/:filmeId" element={<Filme />} />
        <Route path="/filme/:filmeId/info" element={<CompraFilme />} />
        <Route path="/listas/:listId/:mesParametro" element={<SessoesEspeciais />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>

  );
}

export default App;
