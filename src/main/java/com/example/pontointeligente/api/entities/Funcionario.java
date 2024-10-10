package com.example.pontointeligente.api.entities;

import com.example.pontointeligente.api.enums.PerfilEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Data
@ToString
public class Funcionario implements Serializable{

    @Serial
    private static final long serialVersionUID = 2447405188914645654L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "valor_hora", nullable = false)
    private BigDecimal valorHora;

    @Column(name = "qtd_horas_trabalho_dia", nullable = false)
    private Float qtdHorasTrabalhoDia;

    @Column(name = "qtd_horas_almoco", nullable = false)
    private Float qtdHorasAlmoco;

    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamento;

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
