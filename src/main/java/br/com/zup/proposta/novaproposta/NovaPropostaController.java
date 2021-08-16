package br.com.zup.proposta.novaproposta;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/propostas")
public class NovaPropostaController {

    private PropostaRepository propostaRepository;

    public NovaPropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    @Transactional
    public String criarProposta(@RequestBody @Valid NovaPropostaRequest request){
        Proposta proposta = propostaRepository.save(request.toModel());
        return "Proposta criada com id "+proposta.getId();
    }
}
