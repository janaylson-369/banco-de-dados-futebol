<%-- 
    Document   : list.jsp
    Created on : Feb 1, 2026, 6:30:18 PM
    Author     : janailson
--%>

<%-- --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bd.model.TimeFutebol" %>
<%@ page import="bd.dao.TimeFutebolDAO" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Times</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/listtime.css">
    <style>
        /* CSS Rápido para a imagem do escudo ficar bonita na tabela */
        .escudo-img {
            width: 40px;
            height: 40px;
            object-fit: cover;
            border-radius: 50%;
            border: 1px solid #ddd;
        }
        .escudo-placeholder {
            font-size: 24px;
        }
    </style>
</head>
<body>
    <div class="container" style="max-width: 1000px;"> 
        
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
            <h1 style="margin-bottom: 0; border: none;">Times Cadastrados</h1>
            <a href="cadastrarTime.jsp" class="btn btn-primary" style="width: auto; margin: 0;">+ Novo Time</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th style="width: 50px;">ID</th>
                    <th style="width: 60px;">Escudo</th>
                    <th>Nome do Time</th>
                    <th>Cidade (Sede)</th>
                    <th>Técnico</th>
                    <th style="width: 250px;">Ações</th> 
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        // 1. Instancia o DAO e busca a lista
                        TimeFutebolDAO dao = new TimeFutebolDAO();
                        List<TimeFutebol> lista = dao.listar();
                        
                        // 2. Loop para criar as linhas da tabela
                        for(TimeFutebol time : lista) {
                %>
                <tr>
                    <td><%= time.getId() %></td>
                    
                    <td style="text-align: center;">
                        <% if(time.getCaminhoEscudo() != null && !time.getCaminhoEscudo().isEmpty()) { %>
                            <img src="<%= time.getCaminhoEscudo() %>" class="escudo-img" alt="Escudo">
                        <% } else { %>
                            <span class="escudo-placeholder" title="Sem escudo">🛡️</span>
                        <% } %>
                    </td>

                    <td><strong><%= time.getNome() %></strong></td>
                    
                    <td><%= time.getNomeCidade() %></td>
                    <td><%= time.getNomeTecnico() %></td>
           
                    <td class="actions">
                        <a href="jogadores.jsp?idTime=<%= time.getId() %>" class="btn btn-view-players" title="Ver Elenco">
                            👟 Elenco
                        </a>

                        <a href="editar.jsp?id=<%= time.getId() %>" class="btn btn-edit">
                            ✏️
                        </a>

                        <a href="excluir.jsp?id=<%= time.getId() %>" class="btn btn-delete" onclick="return confirm('Tem certeza que deseja apagar o time <%= time.getNome() %>?');">
                            🗑️
                        </a>
                    </td>
                </tr>
                <% 
                        } // Fim do For
                    } catch(Exception e) {
                %>
                    <tr>
                        <td colspan="6" style="color: red; text-align: center;">
                            Erro ao carregar times: <%= e.getMessage() %>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <a href="index.jsp" class="btn btn-secondary" style="margin-top: 20px;">Voltar ao Menu</a>
    </div>
</body>
</html>