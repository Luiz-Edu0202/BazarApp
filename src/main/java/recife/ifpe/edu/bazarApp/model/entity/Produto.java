package recife.ifpe.edu.bazarApp.model.entity;

import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan
public class Produto {
    private int codigo;
    private String nome;
    private String descricao;

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
