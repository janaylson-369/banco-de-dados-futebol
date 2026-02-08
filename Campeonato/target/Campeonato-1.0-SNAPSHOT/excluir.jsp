<%-- --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bd.dao.TimeFutebolDAO" %>
<%@ page import="bd.model.TimeFutebol" %>

<%
    // Recupera o ID da URL (ex: excluir.jsp?id=1)
    String idStr = request.getParameter("id");
    TimeFutebol time = null;

    try {
        if (idStr != null && !idStr.isEmpty()) {
            TimeFutebolDAO dao = new TimeFutebolDAO();
            time = dao.buscar(Integer.parseInt(idStr));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Se não achou o time, volta pra lista
    if (time == null) {
        response.sendRedirect("listarTimes.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Excluir Time</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/estiloPaginaForm.css">
</head>
<body>
    <div class="container">
        <h2 style="color: #d9534f;">Excluir Time</h2>
        <p>Tem certeza que deseja remover este time do sistema?</p>
        
        
        <form action="<%= request.getContextPath() %>/ExcluirTimeServlet" method="post">

            <%-- O ID fica escondido, mas é enviado para o Servlet saber quem apagar --%>
            <input type="hidden" name="id" value="<%= time.getId() %>">

            <div class="form-group">
                <label>Nome:</label>
                <span class="dados-exclusao"><%= time.getNome() %></span>
            </div>

            <div class="form-group">
                <label>Cidade:</label>
                <span class="dados-exclusao"><%= time.getNomeCidade() %></span>
            </div>

            <div class="form-group">
                <label>Técnico:</label>
                <span class="dados-exclusao"><%= time.getNomeTecnico() %></span>
            </div>

            <div class="form-actions" style="margin-top: 20px;">
                <button type="submit" class="btn" style="background-color: #d9534f; color: white; border: none; padding: 10px 20px; cursor: pointer;">Confirmar Exclusão</button>
                
                <a href="listarTimes.jsp" class="btn" style="background-color: #ccc; color: black; padding: 10px 20px; text-decoration: none; margin-left: 10px;">Cancelar</a>
            </div>

        </form>
    </div>
</body>
</html>