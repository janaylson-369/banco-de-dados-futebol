package bd.model;

public class Jogador {
    
    private int id;
    private String nome;
    private int nCamisa;
    private int posicao;
    private Integer idTime;

    
    public Jogador() {

    }

    public Jogador(int id, String nome, int nCamisa, int posicao, Integer idTime) {
        this.id = id;
        this.nome = nome;
        this.nCamisa = nCamisa;
        this.posicao = posicao;
        this.idTime = idTime;
    }

    public Jogador(String nome, int nCamisa, int posicao, Integer idTime) {
        this.nome = nome;
        this.nCamisa = nCamisa;
        this.posicao = posicao;
        this.idTime = idTime;
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

    public int getnCamisa() {
        return nCamisa;
    }

    public void setnCamisa(int nCamisa) {
        this.nCamisa = nCamisa;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Integer getIdTime() {
        return idTime;
    }

    public void setIdTime(Integer idTime) {
        this.idTime = idTime;
    }

    

    
}
