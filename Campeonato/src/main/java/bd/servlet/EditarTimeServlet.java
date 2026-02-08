package bd.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bd.dao.TimeFutebolDAO;
import bd.model.TimeFutebol;

@WebServlet(name = "EditarTimeServlet", urlPatterns = {"/EditarTimeServlet"})
public class EditarTimeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        try {
            // Recebe os dados
            int id = Integer.parseInt(request.getParameter("id"));
            String nomeTime = request.getParameter("nome");
            String nomeCidade = request.getParameter("cidade");
            String nomeTecnico = request.getParameter("tecnico");
            String urlEscudo = request.getParameter("escudo"); // Novo campo

            // Monta o objeto
            TimeFutebol time = new TimeFutebol();
            time.setId(id);
            time.setNome(nomeTime);
            time.setNomeCidade(nomeCidade);
            time.setNomeTecnico(nomeTecnico);
            time.setCaminhoEscudo(urlEscudo);

            // Chama o DAO atualizado
            TimeFutebolDAO dao = new TimeFutebolDAO();
            dao.atualizarCompleto(time);

            // Redireciona para a lista
            response.sendRedirect("listarTimes.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?erro=" + e.getMessage());
        }
    }
}