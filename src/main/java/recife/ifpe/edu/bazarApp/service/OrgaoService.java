package recife.ifpe.edu.bazarApp.service;

import org.springframework.stereotype.Service;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import recife.ifpe.edu.bazarApp.model.repositories.OrgaoDonatarioRepository;
import recife.ifpe.edu.bazarApp.model.repositories.OrgaoFiscalizadorRepository;

import java.util.List;

@Service
public class OrgaoService
{

    private final OrgaoFiscalizadorRepository fiscalizadorRepository;
    private final OrgaoDonatarioRepository donatarioRepository;

    public OrgaoService(OrgaoFiscalizadorRepository fiscalizadorRepository,
                        OrgaoDonatarioRepository donatarioRepository)
    {
        this.fiscalizadorRepository = fiscalizadorRepository;
        this.donatarioRepository = donatarioRepository;
    }


    public OrgaoFiscalizador autenticarFiscalizador(String nome, String senha)
    {
        OrgaoFiscalizador orgao = fiscalizadorRepository.buscarPorNome(nome);
        if (orgao != null && orgao.getSenha().equals(senha))
        {
            return orgao;
        }
        return null;
    }

    public OrgaoDonatario autenticarDonatario(String nome, String senha)
    {
        OrgaoDonatario orgao = donatarioRepository.buscarPorNome(nome);
        if (orgao != null && orgao.getSenha().equals(senha))
        {
            return orgao;
        }
        return null;
    }


    public void cadastrarFiscalizador(OrgaoFiscalizador orgao)
    {
        fiscalizadorRepository.salvar(orgao);
    }

    public void cadastrarDonatario(OrgaoDonatario orgao)
    {
        donatarioRepository.salvar(orgao);
    }


    public List<OrgaoDonatario> listarTodosDonatarios()
    {
        return donatarioRepository.listarTodos();
    }
}
