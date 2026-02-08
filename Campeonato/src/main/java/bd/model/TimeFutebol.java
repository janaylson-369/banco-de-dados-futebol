package bd.model;

public class TimeFutebol {
    // Campos principais (do banco de dados)
    private int id_time;
    private String nome;
    private String caminhoEscudo; // Corrigido para minúscula (camelCase)
    private Integer id_cidade;
    private Integer id_tecnico;
    
    // Campos auxiliares (apenas para exibição na lista, não gravam no banco)
    private String nomeCidade;
    private String nomeTecnico;

    // Construtor vazio (obrigatório para o DAO funcionar bem)
    public TimeFutebol() {}

    // Construtor completo
    public TimeFutebol(int id_time, String nome, String caminhoEscudo, Integer id_cidade, Integer id_tecnico) {
        this.id_time = id_time;
        this.nome = nome;
        this.caminhoEscudo = caminhoEscudo;
        this.id_cidade = id_cidade;
        this.id_tecnico = id_tecnico;
    }

    // --- GETTERS E SETTERS ---

    public int getId() {
        return id_time;
    }

    public void setId(int id_time) {
        this.id_time = id_time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // Getter e Setter para o Escudo
    public String getCaminhoEscudo() {
        return caminhoEscudo;
    }

    public void setCaminhoEscudo(String caminhoEscudo) {
        this.caminhoEscudo = caminhoEscudo;
    }

    public Integer getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(Integer id_cidade) {
        this.id_cidade = id_cidade;
    }

    public Integer getIdtecnico() {
        return id_tecnico;
    }

    public void setIdtecnico(Integer id_tecnico) {
        this.id_tecnico = id_tecnico;
    }
    
    // --- Getters e Setters dos Auxiliares (Cidade e Técnico) ---
    // Esses são os que faltavam para o listar() funcionar
    
    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }
}