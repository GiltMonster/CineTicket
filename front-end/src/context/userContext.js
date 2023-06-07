import { createContext } from "react";

export const UserContext = createContext({
    logado:false,
    email:"",
    nome:"",
    sobrenome:"",
    dataNascimento:"",
    telefone:"",
    endereco:"",
    senha:""
});