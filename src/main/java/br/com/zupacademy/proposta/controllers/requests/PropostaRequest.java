package br.com.zupacademy.proposta.controllers.requests;

import br.com.zupacademy.proposta.models.Proposta;
import br.com.zupacademy.proposta.repositories.PropostaRepository;
import br.com.zupacademy.proposta.validation.CPForCNPJ;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Optional;

public class PropostaRequest {
    @CPForCNPJ @NotBlank
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;


    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toProposta(PropostaRepository propostaRepository) {
        Optional<Proposta> proposta = propostaRepository.findByDocumento(documento);
        if(proposta.isPresent()){
            return null;
        }
        return new Proposta(documento,email,nome,endereco,salario);
    }

    @Override
    public String toString() {
        return "PropostaRequest{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }
}
