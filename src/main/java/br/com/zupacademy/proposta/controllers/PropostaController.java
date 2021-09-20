package br.com.zupacademy.proposta.controllers;

import br.com.zupacademy.proposta.controllers.requests.PropostaRequest;
import br.com.zupacademy.proposta.controllers.responses.AnaliseResponse;
import br.com.zupacademy.proposta.controllers.responses.PropostaResponse;
import br.com.zupacademy.proposta.controllers.requests.AnaliseRequest;
import br.com.zupacademy.proposta.models.Proposta;
import br.com.zupacademy.proposta.repositories.PropostaRepository;
import br.com.zupacademy.proposta.service.AnaliseProposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseProposta analiseProposta;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Proposta proposta = request.toProposta(propostaRepository);
        if(proposta == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(request.getDocumento()+" -> já existe");
        }

        propostaRepository.save(proposta);
        try {
            AnaliseResponse analiseResponse = analiseProposta.consulta(new AnaliseRequest(proposta));
            proposta.setEstado(analiseResponse.getResultadoSolicitacao());
        }catch (FeignException e){
            proposta.setEstado("COM_RESTRICAO");
        }
        propostaRepository.save(proposta);

        URI consulta = builder.path("/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(consulta).build();
    }

    @GetMapping
    public List<PropostaResponse> lista(){
        List<Proposta> propostas = propostaRepository.findAll();
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID());
        return PropostaResponse.converte(propostas);
    }
}
