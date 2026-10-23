package recife.ifpe.edu.bazarApp.model.entity;

import org.springframework.boot.persistence.autoconfigure.EntityScan;

public class Produto {
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer loteID;

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLoteID() {
        return loteID;
    }

    public void setLoteID(Integer loteID) {
        this.loteID = loteID;
    }
}
