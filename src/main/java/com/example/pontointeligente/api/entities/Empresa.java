package com.example.pontointeligente.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "empresa")
@Data
@ToString
public class Empresa implements Serializable {

    @Serial
    private static final long serialVersionUID = 7136302106222149165L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Funcionario> funcionario;

    @PreUpdate
    public void preUpdate (){
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void PersistUpdate (){
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }
}
