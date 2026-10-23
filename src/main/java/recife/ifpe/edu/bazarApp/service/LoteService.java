package recife.ifpe.edu.bazarApp.service;

import org.springframework.stereotype.Service;
import recife.ifpe.edu.bazarApp.model.entity.Lote;
import recife.ifpe.edu.bazarApp.model.entity.OrgaoFiscalizador;
import recife.ifpe.edu.bazarApp.model.repositories.LoteRepository;

import java.util.List;

@Service
public class LoteService
{

    private final LoteRepository loteRepository;

    public LoteService(LoteRepository loteRepository)
    {
        this.loteRepository = loteRepository;
    }

    public void salvarNovoLote(Lote lote, OrgaoFiscalizador fiscalizadorLogado)
    {
        lote.setOrgaoFiscalizador(fiscalizadorLogado);
        loteRepository.salvar(lote);
    }

    public Lote buscarLoteCompleto(int id)
    {
        return loteRepository.buscarPorId(id);
    }

    public List<Lote> listarLotesDoFiscalizador(int idFiscalizador)
    {
        return loteRepository.listarPorOrgaoFiscalizador(idFiscalizador);
    }

    public List<Lote> listarLotesDoDonatario(int idDonatario)
    {
        return loteRepository.listarPorOrgaoDonatario(idDonatario);
    }
}
