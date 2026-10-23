package recife.ifpe.edu.bazarApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import recife.ifpe.edu.bazarApp.service.OrgaoService;

@Controller
public class CadastroController
{

    private final OrgaoService orgaoService;

    public CadastroController(OrgaoService orgaoService)
    {
        this.orgaoService = orgaoService;
    }

    @GetMapping("/cadastro")
    public String exibirCadastro()
    {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@RequestParam String tipo,
                                    @RequestParam String nome,
                                    @RequestParam String senha,
                                    @RequestParam(required = false) String descricao,
                                    @RequestParam(required = false) String endereco,
                                    @RequestParam(required = false) String telefone,
                                    @RequestParam(required = false) String horarioFuncionamento)
    {

        if ("fiscalizador".equals(tipo))
        {
            OrgaoFiscalizador fiscalizador = new OrgaoFiscalizador();
            fiscalizador.setNome(nome);
            fiscalizador.setSenha(senha);
            fiscalizador.setDescricao(descricao);
            orgaoService.cadastrarFiscalizador(fiscalizador);
        }
        else if ("donatario".equals(tipo))
        {
            OrgaoDonatario donatario = new OrgaoDonatario();
            donatario.setNome(nome);
            donatario.setSenha(senha);
            donatario.setDescricao(descricao);
            donatario.setEndereco(endereco);
            donatario.setTelefone(telefone);
            donatario.setHorarioFuncionamento(horarioFuncionamento);
            orgaoService.cadastrarDonatario(donatario);
        }
        return "redirect:/login";
    }
}
