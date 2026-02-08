<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bd.dao.TimeFutebolDAO" %>
<%@ page import="bd.model.TimeFutebol" %>

<%
    // Lógica para carregar os dados do time antes de mostrar o formulário
    String idStr = request.getParameter("id");
    TimeFutebol time = null;
    
    if (idStr != null) {
        TimeFutebolDAO dao = new TimeFutebolDAO();
        time = dao.buscar(Integer.parseInt(idStr));
    }
    
    // Se não achou o time, volta pro início
    if (time == null) {
        response.sendRedirect("listarTimes.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Time</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/casdastrostyle.css">
</head>
<body>
    <div class="container">
        <div class="header-cadastro">
            <h1>Editar Time: <%= time.getNome() %></h1>
        </div>

        <form method="post" action="EditarTimeServlet">
            
            <%-- O ID fica escondido aqui. O usuário não vê, mas é enviado pro Servlet --%>
            <input type="hidden" name="id" value="<%= time.getId() %>">

            <div class="form-group">
                <label>Nome do Time:</label>
                <input type="text" name="nome" value="<%= time.getNome() %>" required class="form-control">
            </div>

            <div class="form-group">
                <label>URL do Escudo (Imagem):</label>
                <input type="text" name="escudo" value="<%= (time.getCaminhoEscudo() == null) ? "" : time.getCaminhoEscudo() %>" placeholder="Cole o link da imagem aqui" class="form-control">
            </div>
     
            <div class="form-group">
                <label>Cidade (Sede):</label>
                <input type="text" name="cidade" value="<%= time.getNomeCidade() %>" required class="form-control">
            </div>
            
            <div class="form-group">
                <label>Nome do Técnico:</label>
                <input type="text" name="tecnico" value="<%= time.getNomeTecnico() %>" required class="form-control">
            </div>

            <div class="form-actions" style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                <a href="listarTimes.jsp" class="btn btn-secondary" style="margin-left: 10px;">Cancelar</a>
            </div>
            
        </form>
    </div>
</body>
</html>