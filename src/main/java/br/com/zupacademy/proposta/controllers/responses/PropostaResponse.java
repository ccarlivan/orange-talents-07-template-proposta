package br.com.zupacademy.proposta.controllers.responses;

import br.com.zupacademy.proposta.models.Proposta;

import java.util.List;
import java.util.stream.Collectors;

public class PropostaResponse {
    private String documento;
    private String nome;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
    }

    public static List<PropostaResponse> converte(List<Proposta> propostas) {
        return propostas.stream().map(PropostaResponse::new).collect(Collectors.toList());
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}
