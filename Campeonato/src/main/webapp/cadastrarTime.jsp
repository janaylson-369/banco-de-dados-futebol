<%-- 
    Document   : form.jsp
    Created on : Feb 1, 2026, 6:27:06 PM
    Author     : janailson
--%>


<%-- --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bd.dao.TimeFutebolDAO" %>
<%@ page import="java.sql.SQLException" %>
<%-- O Servlet vai preencher essas variáveis --%><%-- --%>

<%-- Captura mensagens enviadas pelo Servlet --%>
<%
    String mensagem = (String) request.getAttribute("mensagem");
    String tipoMensagem = (String) request.getAttribute("tipoMensagem");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Time</title>
    <%-- Seus arquivos CSS --%>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/casdastrostyle.css"> 
</head>
<body>
    <div class="container">
        
        <div class="header-cadastro">
            <h1>Cadastrar Novo Time</h1>
        </div>

        <%-- Exibe mensagem de erro ou sucesso se houver --%>
        <% if (mensagem != null && !mensagem.isEmpty()) { %>
            <div class="<%= tipoMensagem %>" style="padding: 10px; margin-bottom: 15px; border-radius: 5px; <%= tipoMensagem.equals("erro") ? "background-color: #f8d7da; color: #721c24;" : "background-color: #d4edda; color: #155724;" %>">
                <%= mensagem %>
            </div>
        <% } %>

        <form method="post" action="CadastrarTimeServlet">
            
            <div class="form-group">
                <label>Nome do Time:</label>
                <input type="text" name="nome" required placeholder="Ex: Flamengo" class="form-control">
            </div>
     
            <div class="form-group">
                <label>Cidade (Sede):</label>
                <input type="text" name="cidade" required placeholder="Ex: Rio de Janeiro" class="form-control">
            </div>
            
            <div class="form-group">
                <label>Nome do Técnico:</label>
                <input type="text" name="tecnico" required placeholder="Ex: Tite" class="form-control">
            </div>

            <div class="form-actions" style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary">Salvar Time</button>
                <a href="index.jsp" class="btn btn-secondary" style="margin-left: 10px;">Voltar</a>
            </div>
            
        </form>
    </div>
</body>
</html>