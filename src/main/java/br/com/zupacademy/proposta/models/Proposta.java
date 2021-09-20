package br.com.zupacademy.proposta.models;

import br.com.zupacademy.proposta.enuns.EstadoProposta;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class Proposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private EstadoProposta estado;

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta() {
    }

    public void setEstado(String estado){
        if(estado.equals("SEM_RESTRICAO")){
            this.estado = EstadoProposta.ELEGIVEL;
        }else{
            this.estado = EstadoProposta.NAO_ELEGIVEL;
        }
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }
}
