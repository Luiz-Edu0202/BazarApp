package recife.ifpe.edu.bazarApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import recife.ifpe.edu.bazarApp.model.entity.Lote;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;
import recife.ifpe.edu.bazarApp.service.LoteService;

import java.util.List;

@Controller
@RequestMapping("/donatario")
public class DonatarioController
{

    private final LoteService loteService;

    public DonatarioController(LoteService loteService)
    {
        this.loteService = loteService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model)
    {
        if (!"donatario".equals(session.getAttribute("tipoUsuario")))
        {
            return "redirect:/login";
        }

        OrgaoDonatario logado = (OrgaoDonatario) session.getAttribute("usuarioLogado");

        List<Lote> lotesRecebidos = loteService.listarLotesDoDonatario(logado.getId());
        model.addAttribute("lotes", lotesRecebidos);

        return "dashboard-donatario";
    }
}
