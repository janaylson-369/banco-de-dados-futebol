package bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bd.util.ConnectionFactory;
import bd.model.TimeFutebol;
import java.sql.SQLException;

public class TimeFutebolDAO {

    // --- CADASTRO COMPLETO (Time + Cidade + Tecnico) ---
    public void salvarCompleto(String nomeTime, String nomeCidade, String nomeTecnico) throws Exception {
        Connection c = ConnectionFactory.getConnection();
        c.setAutoCommit(false); 
        
        PreparedStatement psCidade = null;
        PreparedStatement psTecnico = null;
        PreparedStatement psTime = null;
        ResultSet rsCidade = null;
        ResultSet rsTecnico = null;

        try {
            // 1. INSERIR CIDADE (recupera id_cidade)
            String sqlCidade = "INSERT INTO cidade (nome) VALUES (?) RETURNING id_cidade";
            psCidade = c.prepareStatement(sqlCidade);
            psCidade.setString(1, nomeCidade);
            rsCidade = psCidade.executeQuery();
            
            int idCidade = 0;
            if (rsCidade.next()) idCidade = rsCidade.getInt("id_cidade");

            // 2. INSERIR TÉCNICO (recupera id_tecnico)
            String sqlTecnico = "INSERT INTO tecnico (nome) VALUES (?) RETURNING id_tecnico";
            psTecnico = c.prepareStatement(sqlTecnico);
            psTecnico.setString(1, nomeTecnico);
            rsTecnico = psTecnico.executeQuery();
            
            int idTecnico = 0;
            if (rsTecnico.next()) idTecnico = rsTecnico.getInt("id_tecnico");

            // 3. INSERIR O TIME (usa id_time, id_cidade, id_tecnico)
            String sqlTime = "INSERT INTO Time_futebol (nome, caminho_escudo, id_cidade, id_tecnico) VALUES (?, ?, ?, ?)";
            psTime = c.prepareStatement(sqlTime);
            psTime.setString(1, nomeTime);
            psTime.setString(2, ""); // Escudo vazio inicialmente
            psTime.setInt(3, idCidade);
            psTime.setInt(4, idTecnico);
            
            psTime.execute();

            c.commit(); 
        } catch (SQLException e) {
            c.rollback(); 
            throw e;
        } finally {
            if(rsCidade != null) rsCidade.close();
            if(rsTecnico != null) rsTecnico.close();
            if(psCidade != null) psCidade.close();
            if(psTecnico != null) psTecnico.close();
            if(psTime != null) psTime.close();
            c.close();
        }
    }

    // --- LISTAR COM NOMES ---
    public List<TimeFutebol> listar() throws Exception {
        List<TimeFutebol> lista = new ArrayList<>();
        
        // Busca os dados do time + nome da cidade + nome do técnico
        String sql = "SELECT t.id_time, t.nome, t.caminho_escudo, " +
                     "c.nome AS nome_cidade, te.nome AS nome_tecnico " +
                     "FROM Time_futebol t " +
                     "INNER JOIN cidade c ON t.id_cidade = c.id_cidade " +
                     "INNER JOIN tecnico te ON t.id_tecnico = te.id_tecnico " +
                     "ORDER BY t.id_time";

        try(Connection c = ConnectionFactory.getConnection();
            Statement st = c.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                TimeFutebol time = new TimeFutebol();
                time.setId(rs.getInt("id_time"));
                time.setNome(rs.getString("nome"));
                
                // Trata o escudo
                String escudo = rs.getString("caminho_escudo");
                time.setCaminhoEscudo(escudo != null ? escudo : ""); 
                
                // Preenche os nomes que vieram do JOIN
                time.setNomeCidade(rs.getString("nome_cidade"));
                time.setNomeTecnico(rs.getString("nome_tecnico"));
                
                lista.add(time);         
            }
        }
        return lista;
    }

    // --- BUSCAR POR ID (Com JOIN para trazer os nomes) ---
    public TimeFutebol buscar(int id) throws Exception {
        String sql = "SELECT t.id_time, t.nome, t.caminho_escudo, t.id_cidade, t.id_tecnico, " +
                     "c.nome AS nome_cidade, te.nome AS nome_tecnico " +
                     "FROM Time_futebol t " +
                     "INNER JOIN cidade c ON t.id_cidade = c.id_cidade " +
                     "INNER JOIN tecnico te ON t.id_tecnico = te.id_tecnico " +
                     "WHERE t.id_time = ?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                TimeFutebol time = new TimeFutebol();
                time.setId(rs.getInt("id_time"));
                time.setNome(rs.getString("nome"));
                time.setCaminhoEscudo(rs.getString("caminho_escudo"));
                time.setId_cidade(rs.getInt("id_cidade"));
                time.setIdtecnico(rs.getInt("id_tecnico"));
                
                // Preenche os nomes auxiliares
                time.setNomeCidade(rs.getString("nome_cidade"));
                time.setNomeTecnico(rs.getString("nome_tecnico"));
                
                return time;
            }
        }
        return null;
    }

    // --- ATUALIZAR COMPLETO (Time + Cidade + Tecnico) ---
    public void atualizarCompleto(TimeFutebol time) throws Exception {
        Connection c = ConnectionFactory.getConnection();
        c.setAutoCommit(false); // Inicia transação

        try {
            // 1. Descobrir quais são os IDs de cidade e técnico deste time
            int idCidade = 0;
            int idTecnico = 0;
            
            String sqlBuscaIds = "SELECT id_cidade, id_tecnico FROM Time_futebol WHERE id_time = ?";
            try (PreparedStatement psBusca = c.prepareStatement(sqlBuscaIds)) {
                psBusca.setInt(1, time.getId());
                ResultSet rs = psBusca.executeQuery();
                if (rs.next()) {
                    idCidade = rs.getInt("id_cidade");
                    idTecnico = rs.getInt("id_tecnico");
                }
            }

            // 2. Atualizar nome da Cidade
            String sqlCidade = "UPDATE cidade SET nome = ? WHERE id_cidade = ?";
            try (PreparedStatement psC = c.prepareStatement(sqlCidade)) {
                psC.setString(1, time.getNomeCidade());
                psC.setInt(2, idCidade);
                psC.execute();
            }

            // 3. Atualizar nome do Técnico
            String sqlTecnico = "UPDATE tecnico SET nome = ? WHERE id_tecnico = ?";
            try (PreparedStatement psT = c.prepareStatement(sqlTecnico)) {
                psT.setString(1, time.getNomeTecnico());
                psT.setInt(2, idTecnico);
                psT.execute();
            }

            // 4. Atualizar Time (Nome e Escudo)
            String sqlTime = "UPDATE Time_futebol SET nome = ?, caminho_escudo = ? WHERE id_time = ?";
            try (PreparedStatement psTime = c.prepareStatement(sqlTime)) {
                psTime.setString(1, time.getNome());
                psTime.setString(2, time.getCaminhoEscudo());
                psTime.setInt(3, time.getId());
                psTime.execute();
            }

            c.commit(); // Salva tudo
        } catch (Exception e) {
            c.rollback();
            throw e;
        } finally {
            c.close();
        }
    }

    // --- ATUALIZAR SIMPLES (Só o time) ---
    public void atualizar(TimeFutebol time) throws Exception {
        String sql = "UPDATE Time_futebol SET nome=?, id_cidade=?, id_tecnico=? WHERE id_time=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, time.getNome());
            ps.setObject(2, time.getId_cidade());
            ps.setObject(3, time.getIdtecnico());
            ps.setInt(4, time.getId());

            ps.execute();
        }
    }

    // --- EXCLUIR ---
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Time_futebol WHERE id_time=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }
}