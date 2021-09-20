package br.com.zupacademy.proposta.service;

import br.com.zupacademy.proposta.controllers.requests.AnaliseRequest;
import br.com.zupacademy.proposta.controllers.responses.AnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="analise", url = "http://localhost:9999/api/solicitacao")
public interface AnaliseProposta {
    @PostMapping
    AnaliseResponse consulta(@RequestBody AnaliseRequest analise);
}
