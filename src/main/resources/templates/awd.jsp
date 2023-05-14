<%@ page import="br.senac.sp.projeto.cineticketoficial.entity.Cliente" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Informações do Cliente</h1>
<% Cliente cliente = (Cliente) request.getAttribute("clienteSearch"); %>
<p>
    <label for="nome">nome:</label>
    <input type="text" id="nome" name="nome" value="<%= cliente.getNome() %>">
</p>
<p>
    <label for="sobrenome">nome:</label>
    <input type="text" id="sobrenome" name="sobrenome" value="<%= cliente.getSobrenome() %>">
</p>
<p>
    <label for="dataNascimento">nome:</label>
    <input type="date" id="dataNascimento" name="dataNascimento" value="<%= cliente.getDataNascimento() %>">
</p>
<p>
    <label for="telefone">nome:</label>
    <input type="tel" id="telefone" name="telefone" value="<%= cliente.getTelefone() %>">
</p>
<p>
    <label for="endereco">nome:</label>
    <input type="text" id="endereco" name="endereco" value="<%= cliente.getEndereco() %>">
</p>
<div>

</div>

<div th:if="${not clienteSearch.isPresent()}">
    <p>Cliente não encontrado</p>
</div>
</body>
</html>