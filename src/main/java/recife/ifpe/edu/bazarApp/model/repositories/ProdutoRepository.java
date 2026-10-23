package recife.ifpe.edu.bazarApp.model.repositories;


import org.springframework.stereotype.Repository;
import recife.ifpe.edu.bazarApp.model.entity.Produto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static recife.ifpe.edu.bazarApp.Utils.Mapeador.mapearResultSetParaProduto;

@Repository
public class ProdutoRepository {
    private final DataSource dataSource;

    public ProdutoRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void salvar(Produto produto)
    {
        String sql = "INSERT INTO produto(nome, descricao, lote_id) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getLoteID());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next()) {
                    produto.setCodigo(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public List<Produto> listarPorLote(int loteId)
    {
        String sql = "SELECT * FROM produto WHERE lote_id = ?";
        List<Produto> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, loteId);

            try (ResultSet rs = stmt.executeQuery())
            {
                while (rs.next())
                {
                    Produto produto = mapearResultSetParaProduto(rs);
                    lista.add(produto);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
        return lista;
    }

}
