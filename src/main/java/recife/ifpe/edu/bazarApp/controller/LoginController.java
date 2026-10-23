package recife.ifpe.edu.bazarApp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoDonatario;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import recife.ifpe.edu.bazarApp.service.OrgaoService;

@Controller
public class LoginController
{

    private final OrgaoService orgaoService;

    public LoginController(OrgaoService orgaoService)
    {
        this.orgaoService = orgaoService;
    }

    @GetMapping("/login")
    public String exibirLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(@RequestParam String nome,
                             @RequestParam String senha,
                             @RequestParam String tipo,
                             HttpSession session,
                             Model model)
    {

        if ("fiscalizador".equals(tipo))
        {
            OrgaoFiscalizador logado = orgaoService.autenticarFiscalizador(nome, senha);
            if (logado != null)
            {
                session.setAttribute("usuarioLogado", logado);
                session.setAttribute("tipoUsuario", "fiscalizador");
                return "redirect:/fiscalizador/dashboard";
            }
        } else if ("donatario".equals(tipo))
        {
            OrgaoDonatario logado = orgaoService.autenticarDonatario(nome, senha);
            if (logado != null)
            {
                session.setAttribute("usuarioLogado", logado);
                session.setAttribute("tipoUsuario", "donatario");
                return "redirect:/donatario/dashboard";
            }
        }

        model.addAttribute("erro", "Nome, senha ou tipo de usuário inválidos.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }
}