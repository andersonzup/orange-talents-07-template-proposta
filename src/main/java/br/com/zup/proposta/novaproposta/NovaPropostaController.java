package br.com.zup.proposta.novaproposta;


import br.com.zup.proposta.novaproposta.consultarestricao.EstadoPropostaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/propostas")
public class NovaPropostaController {

    private PropostaRepository propostaRepository;

    private EstadoPropostaService estadoPropostaService;

    public NovaPropostaController(PropostaRepository propostaRepository, EstadoPropostaService estadoPropostaService) {
        this.propostaRepository = propostaRepository;
        this.estadoPropostaService = estadoPropostaService;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    @Transactional
    public String criarProposta(@RequestBody @Valid NovaPropostaRequest request){
        Proposta proposta = propostaRepository.save(request.toModel());
        EstadoProposta estado = estadoPropostaService.getEstadoProposta(proposta);
        return "Proposta criada com id "+proposta.getId()+", Cliente: "+ estado;
    }




}
