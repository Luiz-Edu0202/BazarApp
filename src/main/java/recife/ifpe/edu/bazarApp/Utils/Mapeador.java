package recife.ifpe.edu.bazarApp.Utils;

import recife.ifpe.edu.bazarApp.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapeador
{
    static public Produto mapearResultSetParaProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setCodigo(rs.getInt("codigo"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setLoteID(rs.getInt("lote_id"));
        return produto;
    }

    static public OrgaoFiscalizador mapearResultSetParaOrgaoFiscalizador(ResultSet rs) throws SQLException {
        OrgaoFiscalizador orgao = new OrgaoFiscalizador();
        orgao.setId(rs.getInt("id"));
        orgao.setNome(rs.getString("nome"));
        orgao.setDescricao(rs.getString("descricao"));
        orgao.setSenha(rs.getString("senha"));
        return orgao;
    }

    static public OrgaoDonatario mapearResultSetParaOrgaoDonatario(ResultSet rs) throws SQLException {
        OrgaoDonatario orgao = new OrgaoDonatario();
        orgao.setId(rs.getInt("id"));
        orgao.setSenha(rs.getString("senha"));
        orgao.setNome(rs.getString("nome"));
        orgao.setEndereco(rs.getString("endereco"));
        orgao.setTelefone(rs.getString("telefone"));
        orgao.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
        orgao.setDescricao(rs.getString("descricao"));
        return orgao;
    }

    static public Lote mapearResultSetParaLote(ResultSet rs) throws SQLException {
        Lote lote = new Lote();
        lote.setId(rs.getInt("id"));

        java.sql.Date sqlDate = rs.getDate("data_entrega");
        if (sqlDate != null) {
            lote.setDataEntrega(new java.util.Date(sqlDate.getTime()));
        }

        lote.setObservacao(rs.getString("observacao"));

        OrgaoFiscalizador fiscalizador = new OrgaoFiscalizador();
        fiscalizador.setId(rs.getInt("orgao_fiscalizador_id"));
        lote.setOrgaoFiscalizador(fiscalizador);


        OrgaoDonatario donatario = new OrgaoDonatario();
        donatario.setId(rs.getInt("orgao_donatario_id"));
        lote.setOrgaoDonatario(donatario);

        return lote;
    }
}
