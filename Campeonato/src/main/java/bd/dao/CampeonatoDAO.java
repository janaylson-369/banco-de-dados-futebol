package bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import bd.util.ConnectionFactory;
import bd.model.Campeonato;

public class CampeonatoDAO {
    
    public void salvar(Campeonato campeonato) throws Exception {
        String sql = "INSERT INTO campeonato(nome) "+"VALUES (?)";

        try(Connection c = ConnectionFactory.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)){
            
            ps.setString(1, campeonato.getNome());
            ps.execute();
        }
    }

    public List<Campeonato> listar() throws Exception{
        List<Campeonato> lista = new ArrayList<>();

        String sql = "SELECT * FROM campeonato";

        try(Connection c = ConnectionFactory.getConnection(); 
                Statement st = c.createStatement()){
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    lista.add(new Campeonato(
                        rs.getInt("id"),
                        rs.getString("nome")
                    ));
                }
        }
        return lista;
    }

    public Campeonato buscar(int id) throws Exception {
        String sql = "SELECT * FROM campeonato WHERE id=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    return new Campeonato(
                    rs.getInt("id"), 
                    rs.getString("nome"));
                }
        }
        return null;
    }

    public void atualizar(Campeonato campeonato) throws Exception {
        String sql = "UPDATE campeonato SET nome=? WHERE id=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

                ps.setString(1, campeonato.getNome());
                ps.execute();
        }
    }

    public void excluir(int id) throws Exception{
        String sql = "DELETE FROM canpeonato WHERE id=?";

        try(Connection c = ConnectionFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
                
                ps.setInt(1, id);
                ps.execute();
        }
    }
}
