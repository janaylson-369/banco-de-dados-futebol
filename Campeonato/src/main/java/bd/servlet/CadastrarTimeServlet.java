package bd.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import bd.dao.TimeFutebolDAO;


// Define a URL que o formulário vai chamar
@WebServlet(name = "CadastrarTimeServlet", urlPatterns = {"/CadastrarTimeServlet"})
public class CadastrarTimeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Configurar codificação para aceitar acentos
        request.setCharacterEncoding("UTF-8");

        // 2. Receber os dados do formulário JSP
        String nomeTime = request.getParameter("nome");
        String nomeCidade = request.getParameter("cidade");
        String nomeTecnico = request.getParameter("tecnico");

        String mensagem = "";
        String tipoMensagem = "";

        try {
            // 3. Validar se os campos não estão vazios
            if (nomeTime != null && !nomeTime.isEmpty() && 
                nomeCidade != null && !nomeCidade.isEmpty() && 
                nomeTecnico != null && !nomeTecnico.isEmpty()) {

                // 4. Chamar o DAO para salvar tudo
                TimeFutebolDAO dao = new TimeFutebolDAO();
                dao.salvarCompleto(nomeTime, nomeCidade, nomeTecnico);

                mensagem = "Time cadastrado com sucesso!";
                tipoMensagem = "sucesso";
            } else {
                mensagem = "Preencha todos os campos!";
                tipoMensagem = "erro";
            }

        } catch (Exception e) {
            mensagem = "Erro ao cadastrar: " + e.getMessage();
            tipoMensagem = "erro";
            // Mostra o erro no console do servidor
        }

        // 5. Enviar mensagem de volta para o JSP e recarregar a página
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("tipoMensagem", tipoMensagem);
        
        request.getRequestDispatcher("cadastrarTime.jsp").forward(request, response);
    }
}