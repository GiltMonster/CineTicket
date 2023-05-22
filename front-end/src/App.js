import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Filme from "./pages/Filme";
import NotFound from "./pages/NotFound";
import Header from "./components/headbar/Header";

function App() {
  return (

    <>
      <Header />
      <Routes>
        <Route path="/" index element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/filme" element={<Filme />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>

  );
}

export default App;
