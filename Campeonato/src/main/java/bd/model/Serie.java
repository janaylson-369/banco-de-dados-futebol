package bd.model;

public class Serie {
    private int id;
    private String nome;
    private Integer idTime;
    private Integer idCampeonato;

    public Serie(int id, String nome, Integer idTime, Integer idCampeonato) {
        this.id = id;
        this.nome = nome;
        this.idTime = idTime;
        this.idCampeonato = idCampeonato;
    }

    public Serie(String nome, Integer idTime, Integer idCampeonato) {
        this.nome = nome;
        this.idTime = idTime;
        this.idCampeonato = idCampeonato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdtime() {
        return idTime;
    }

    public void setIdtime(Integer idTime) {
        this.idTime = idTime;
    }

    public Integer getIdcampeonato() {
        return idCampeonato;
    }

    public void setIdcampeonato(Integer idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    
    
}
