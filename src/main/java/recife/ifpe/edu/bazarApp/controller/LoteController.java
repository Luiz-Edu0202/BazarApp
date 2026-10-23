package recife.ifpe.edu.bazarApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import recife.ifpe.edu.bazarApp.model.entity.Lote;
import recife.ifpe.edu.bazarApp.service.LoteService;

@Controller
@RequestMapping("/lotes")
public class LoteController
{

    private final LoteService loteService;

    public LoteController(LoteService loteService)
    {
        this.loteService = loteService;
    }

    @GetMapping("/{id}")
    public String verDetalhes(@PathVariable int id, HttpSession session, Model model)
    {
        if (session.getAttribute("usuarioLogado") == null)
        {
            return "redirect:/login";
        }

        Lote lote = loteService.buscarLoteCompleto(id);

        if (lote == null)
        {
            return "redirect:/login";
        }

        model.addAttribute("lote", lote);
        return "detalhe-lote";
    }
}