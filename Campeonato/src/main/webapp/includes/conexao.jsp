<%-- 
    Document   : conexao
    Created on : Feb 6, 2026, 2:46:23 PM
    Author     : janailson
--%>

<%@ page import="java.sql.*" %>
<%
    Connection conn = null;
    try {
        Class.forName("org.postgresql.Driver");
        // CONFIRA SE A SENHA DO SEU BANCO ESTÁ CORRETA ABAIXO
        String url = "jdbc:postgresql://localhost:5432/campeonatoBR";
        String user = "postgres";
        String password = "123"; 
        
        conn = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
        out.println("Erro de conexão: " + e.getMessage());
    }
%>