<%-- 
    Document   : jogadores
    Created on : Feb 6, 2026, 4:19:30 PM
    Author     : janailson
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="includes/conexao.jsp" %> 

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Elenco do Time</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/listtime.css">
</head>
<body>
    <div class="container-list"> <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h1 style="margin-bottom: 0;">Elenco do Time</h1>
            <a href="cadastrarJogador.jsp?idTime=<%= request.getParameter("idTime") %>" class="btn btn-primary">+ Novo Jogador</a>
        </div>

        <table class="table-custom"> <thead>
                <tr>
                    <th>Nome do Jogador</th>
                    <th>Posição</th>
                    <th>Camisa</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="actions-cell">
                                        <a href="editarJogador.jsp?id=<" class="btn btn-edit">✏️</a>
                                        <a href="excluirJogador.jsp?id=) %>" class="btn btn-delete">🗑️</a>
                                    </td>
                                </tr>
                
            </tbody>
        </table>

        <div style="margin-top: 20px;">
            <a href="listarTimes.jsp" class="btn btn-secondary">Voltar para Times</a>
        </div>
    </div>
</body>
</html>
