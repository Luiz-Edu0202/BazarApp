package recife.ifpe.edu.bazarApp.model.repositories;

import org.springframework.stereotype.Repository;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static recife.ifpe.edu.bazarApp.Utils.Mapeador.mapearResultSetParaOrgaoFiscalizador;


@Repository
public class OrgaoFiscalizadorRepository
{

    private final DataSource dataSource;

    public OrgaoFiscalizadorRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    public void salvar(OrgaoFiscalizador orgao)
    {
        String sql = "INSERT INTO orgao_fiscalizador (nome, descricao, senha) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            stmt.setString(1, orgao.getNome());
            stmt.setString(2, orgao.getDescricao());
            stmt.setString(3, orgao.getSenha());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next()) {
                    orgao.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar órgão fiscalizador", e);
        }
    }


    public OrgaoFiscalizador buscarPorId(int id)
    {
        String sql = "SELECT * FROM orgao_fiscalizador WHERE id = ?";
        OrgaoFiscalizador orgao = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {
                    orgao = mapearResultSetParaOrgaoFiscalizador(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar órgão por ID", e);
        }
        return orgao;
    }


    public List<OrgaoFiscalizador> listarTodos() {
        String sql = "SELECT * FROM orgao_fiscalizador";
        List<OrgaoFiscalizador> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearResultSetParaOrgaoFiscalizador(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar órgãos fiscalizadores", e);
        }
        return lista;
    }


    public OrgaoFiscalizador buscarPorNome(String nome) {
        String sql = "SELECT * FROM orgao_fiscalizador WHERE nome = ?";
        OrgaoFiscalizador orgao = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orgao = mapearResultSetParaOrgaoFiscalizador(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar órgão por nome", e);
        }
        return orgao;
    }



}
