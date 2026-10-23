package recife.ifpe.edu.bazarApp.model.entity;

import org.springframework.boot.persistence.autoconfigure.EntityScan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Lote {
    private int id;
    private Date dataEntrega;
    private String observacao;

    private OrgaoDonatario orgaoDonatario;
    private OrgaoFiscalizador orgaoFiscalizador;


    private List<Produto> produtos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public OrgaoDonatario getOrgaoDonatario() {
        return orgaoDonatario;
    }

    public OrgaoFiscalizador getOrgaoFiscalizador() {
        return orgaoFiscalizador;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setOrgaoDonatario(OrgaoDonatario orgaoDonatario) {
        this.orgaoDonatario = orgaoDonatario;
    }

    public void setOrgaoFiscalizador(OrgaoFiscalizador orgaoFiscalizador) {
        this.orgaoFiscalizador = orgaoFiscalizador;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
