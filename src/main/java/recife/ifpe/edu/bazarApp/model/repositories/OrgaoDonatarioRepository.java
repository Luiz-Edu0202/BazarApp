package recife.ifpe.edu.bazarApp.model.repositories;

import org.springframework.stereotype.Repository;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static recife.ifpe.edu.bazarApp.Utils.Mapeador.mapearResultSetParaOrgaoDonatario;

@Repository
public class OrgaoDonatarioRepository
{
    private final DataSource dataSource;

    public OrgaoDonatarioRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void salvar(OrgaoDonatario orgao)
    {
        String sql = "INSERT INTO orgao_donatario (nome, descricao, senha, endereco, telefone, horario_funcionamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            stmt.setString(1, orgao.getNome());
            stmt.setString(2, orgao.getDescricao());
            stmt.setString(3, orgao.getSenha());
            stmt.setString(4, orgao.getEndereco());
            stmt.setString(5, orgao.getTelefone());
            stmt.setString(6, orgao.getHorarioFuncionamento());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys())
            {
                if (generatedKeys.next()) {
                    orgao.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar órgão donatário", e);
        }
    }

    public OrgaoDonatario buscarPorId(int id)
    {
        String sql = "SELECT * FROM orgao_donatario WHERE id = ?";
        OrgaoDonatario orgao = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {
                    orgao = mapearResultSetParaOrgaoDonatario(rs);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao buscar órgão por ID", e);
        }
        return orgao;
    }

    public List<OrgaoDonatario> listarTodos() {
        String sql = "SELECT * FROM orgao_donatario";
        List<OrgaoDonatario> lista = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                lista.add(mapearResultSetParaOrgaoDonatario(rs));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao listar órgãos donatários", e);
        }
        return lista;
    }

    public OrgaoDonatario buscarPorNome(String nome)
    {
        String sql = "SELECT * FROM orgao_donatario WHERE nome = ?";
        OrgaoDonatario orgao = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    orgao = mapearResultSetParaOrgaoDonatario(rs);
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Erro ao buscar órgão por nome", e);
        }
        return orgao;
    }
}
