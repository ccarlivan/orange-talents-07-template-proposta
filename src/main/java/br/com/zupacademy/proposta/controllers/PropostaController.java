package br.com.zupacademy.proposta.controllers;

import br.com.zupacademy.proposta.controllers.requests.PropostaRequest;
import br.com.zupacademy.proposta.models.Proposta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Proposta proposta = request.toProposta();
        manager.persist(proposta);

        URI consulta = builder.path("/proposta/{id}").build(proposta.getId());
        return ResponseEntity.created(consulta).build();
    }
}
