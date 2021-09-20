package br.com.zupacademy.proposta.controllers.requests;

import br.com.zupacademy.proposta.models.Proposta;

public class AnaliseRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public AnaliseRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = String.valueOf(proposta.getId());
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
