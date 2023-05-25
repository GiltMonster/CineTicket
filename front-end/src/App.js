import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Filme from "./pages/Filme";
import NotFound from "./pages/NotFound";
import Header from "./components/headbar/Header";
import FilmesPesquisados from "./components/headbar/FilmesPesquisados";

function App() {
  return (

    <>
      <Header />
      <Routes>
        <Route path="/" index element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/pesquisarFilme/:nomeFilme" element={<FilmesPesquisados/>} />
        <Route path="/filme/:filmeId" element={<Filme />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>

  );
}

export default App;
