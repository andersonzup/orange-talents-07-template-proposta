package br.com.zup.proposta.estadoproposta;

import br.com.zup.proposta.exception.PropostaNotFoundException;
import br.com.zup.proposta.novaproposta.Proposta;
import br.com.zup.proposta.novaproposta.PropostaRepository;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/propostas")
public class EstadoPropostaController {

    PropostaRepository propostaRepository;

    public EstadoPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @GetMapping(path = "/{id}")
    public String buscaEstadoProposta(@PathVariable("id") Long id) throws PropostaNotFoundException {
        Proposta proposta = propostaRepository.findById(id).orElseThrow(() -> new PropostaNotFoundException(id));
        return "Olá "+proposta.getNome()+" o Status da proposta "+id+" está como "+proposta.getEstado();
    }
}
