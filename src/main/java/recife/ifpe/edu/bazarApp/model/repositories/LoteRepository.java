package recife.ifpe.edu.bazarApp.model.repositories;

import org.springframework.stereotype.Repository;
import recife.ifpe.edu.bazarApp.model.entity.Lote;
import recife.ifpe.edu.bazarApp.model.entity.Produto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static recife.ifpe.edu.bazarApp.Utils.Mapeador.mapearResultSetParaLote;



@Repository
public class LoteRepository
{
    private final DataSource dataSource;

    private final OrgaoDonatarioRepository orgaoDonatarioRepository;
    private final OrgaoFiscalizadorRepository orgaoFiscalizadorRepository;
    private final ProdutoRepository produtoRepository;

    public LoteRepository(DataSource dataSource,
                          OrgaoDonatarioRepository orgaoDonatarioRepository,
                          OrgaoFiscalizadorRepository orgaoFiscalizadorRepository,
                          ProdutoRepository produtoRepository)
    {
        this.dataSource = dataSource;
        this.orgaoDonatarioRepository = orgaoDonatarioRepository;
        this.orgaoFiscalizadorRepository = orgaoFiscalizadorRepository;
        this.produtoRepository = produtoRepository;
    }

    public void salvar(Lote lote) {
        String sqlLote = "INSERT INTO lote (data_entrega, observacao, orgao_fiscalizador_id, orgao_donatario_id) VALUES (?, ?, ?, ?)";
        String sqlProduto = "INSERT INTO produto (nome, descricao, lote_id) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection())
        {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtLote = conn.prepareStatement(sqlLote, Statement.RETURN_GENERATED_KEYS))
            {

                stmtLote.setDate(1, new java.sql.Date(lote.getDataEntrega().getTime()));
                stmtLote.setString(2, lote.getObservacao());

                stmtLote.setInt(3, lote.getOrgaoFiscalizador().getId());
                stmtLote.setInt(4, lote.getOrgaoDonatario().getId());

                stmtLote.executeUpdate();

                try (ResultSet rs = stmtLote.getGeneratedKeys())
                {
                    if (rs.next())
                    {
                        lote.setId(rs.getInt(1));
                    }
                }
            }

            if (lote.getProdutos() != null && !lote.getProdutos().isEmpty())
            {
                try (PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto))
                {
                    for (Produto p : lote.getProdutos())
                    {
                        stmtProduto.setString(1, p.getNome());
                        stmtProduto.setString(2, p.getDescricao());
                        stmtProduto.setInt(3, lote.getId());
                        stmtProduto.executeUpdate();
                    }
                }
            }

            conn.commit();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao salvar lote e produtos", e);
        }
    }

    public Lote buscarPorId(int id)
    {
        String sql = "SELECT * FROM lote WHERE id = ?";
        Lote lote = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {

                    lote = mapearResultSetParaLote(rs);
                    lote.setOrgaoDonatario(orgaoDonatarioRepository.buscarPorId(lote.getOrgaoDonatario().getId()));
                    lote.setOrgaoFiscalizador(orgaoFiscalizadorRepository.buscarPorId(lote.getOrgaoFiscalizador().getId()));
                    lote.setProdutos(produtoRepository.listarPorLote(lote.getId()));
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao buscar órgão por ID", e);
        }
        return lote;
    }

    public List<Lote> listarPorOrgaoFiscalizador(int idFiscalizador)
    {
        String sql = "SELECT * FROM lote WHERE orgao_fiscalizador_id = ?";
        List<Lote> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idFiscalizador);

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    Lote lote = mapearResultSetParaLote(rs);
                    lista.add(lote);
                }
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao listar lotes", e);
        }
        return lista;
    }

    public List<Lote> listarPorOrgaoDonatario(int idDonatario)
    {
        String sql = "SELECT * FROM lote WHERE orgao_donatario_id = ?";
        List<Lote> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idDonatario);

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    Lote lote = mapearResultSetParaLote(rs);
                    lista.add(lote);
                }
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao listar lotes", e);
        }
        return lista;
    }
}
