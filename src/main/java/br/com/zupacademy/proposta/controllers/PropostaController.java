package br.com.zupacademy.proposta.controllers;

import br.com.zupacademy.proposta.controllers.requests.PropostaRequest;
import br.com.zupacademy.proposta.models.Proposta;
import br.com.zupacademy.proposta.repositories.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Proposta proposta = request.toProposta(propostaRepository);
        if(proposta == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(request.getDocumento()+" -> já existe");
        }

        propostaRepository.save(proposta);

        URI consulta = builder.path("/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(consulta).build();
    }
}
