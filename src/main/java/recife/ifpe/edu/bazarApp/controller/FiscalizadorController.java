package recife.ifpe.edu.bazarApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recife.ifpe.edu.bazarApp.model.entity.Lote;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import recife.ifpe.edu.bazarApp.service.LoteService;
import recife.ifpe.edu.bazarApp.service.OrgaoService;

import java.util.List;

@Controller
@RequestMapping("/fiscalizador")
public class FiscalizadorController
{

    private final LoteService loteService;
    private final OrgaoService orgaoService;

    public FiscalizadorController(LoteService loteService, OrgaoService orgaoService)
    {
        this.loteService = loteService;
        this.orgaoService = orgaoService;
    }

    private OrgaoFiscalizador verificarSessao(HttpSession session)
    {
        if (!"fiscalizador".equals(session.getAttribute("tipoUsuario")))
        {
            return null;
        }
        return (OrgaoFiscalizador) session.getAttribute("usuarioLogado");
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model)
    {
        OrgaoFiscalizador logado = verificarSessao(session);
        if (logado == null) return "redirect:/login";

        List<Lote> meusLotes = loteService.listarLotesDoFiscalizador(logado.getId());
        model.addAttribute("lotes", meusLotes);

        return "dashboard-fiscalizador";
    }

    @GetMapping("/lotes/novo")
    public String formNovoLote(HttpSession session, Model model)
    {
        if (verificarSessao(session) == null) return "redirect:/login";

        Lote lote = new Lote();
        lote.setOrgaoDonatario(new OrgaoDonatario());

        model.addAttribute("lote", new Lote());
        model.addAttribute("orgaosDonatarios", orgaoService.listarTodosDonatarios());

        return "form-lote";
    }

    @PostMapping("/lotes/salvar")
    public String salvarLote(@ModelAttribute Lote lote, HttpSession session)
    {
        OrgaoFiscalizador logado = verificarSessao(session);
        if (logado == null) return "redirect:/login";

        loteService.salvarNovoLote(lote, logado);

        return "redirect:/fiscalizador/dashboard";
    }
}