import { createContext } from "react";

export const userContext = createContext({
    logado:false,
    email:"",
    nome:"",
    sobrenome:"",
    dataNascimento:"",
    telefone:"",
    endereco:"",
    senha:""
});