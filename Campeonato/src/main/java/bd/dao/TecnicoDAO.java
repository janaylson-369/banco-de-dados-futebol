package bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bd.util.ConnectionFactory;
import bd.model.Tecnico;

public class TecnicoDAO {
    
    public void salvar(Tecnico tecnico) throws Exception {
        String sql = "INSERT INTO tecnico (nome) VALUES (?)"; // Tabela minúscula

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, tecnico.getNome());
            ps.execute();
        }
    }

    public List<Tecnico> listar() throws Exception{
        List<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT * FROM tecnico"; // Tabela minúscula

        try(Connection c = ConnectionFactory.getConnection(); 
            Statement st = c.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Tecnico(
                    rs.getInt("id_tecnico"), // NOVO NOME DA COLUNA NO BANCO
                    rs.getString("nome")
                ));
            }
        }
        return lista;
    }

    public Tecnico buscar(int id) throws Exception {
        String sql = "SELECT * FROM tecnico WHERE id_tecnico=?"; // WHERE id_tecnico

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Tecnico(
                    rs.getInt("id_tecnico"), 
                    rs.getString("nome")
                );
            }
        }
        return null;
    }

    public void atualizar(Tecnico tecnico) throws Exception {
        String sql = "UPDATE tecnico SET nome=? WHERE id_tecnico=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, tecnico.getNome());
            ps.setInt(2, tecnico.getId());
            ps.execute();
        }
    }

    public void excluir(int id) throws Exception{
        String sql = "DELETE FROM tecnico WHERE id_tecnico=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }
}