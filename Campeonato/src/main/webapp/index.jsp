<%-- 
    Document   : index.jsp
    Created on : Feb 1, 2026, 5:45:37 PM
    Author     : janailson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal - Campeonato BR</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>⚽ Gestão de Campeonato</h1>
        <p style="margin-bottom: 20px; color: #666;">Selecione uma opção abaixo:</p>
        
        <div class="menu-buttons">
            <a href="Campeonato.jsp" class="btn btn-primary">🏆 Listar Campeonatos</a>
            <a href="listarTimes.jsp" class="btn btn-primary">👕 Gerenciar Times</a>
            <a href="cadastrarTime.jsp" class="btn btn-primary">➕ Cadastrar Novo Time</a>
        </div>
    </div>
</body>
</html>