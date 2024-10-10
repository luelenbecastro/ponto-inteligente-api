package com.example.pontointeligente.api.entities;

import com.example.pontointeligente.api.enums.TipoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lancamento")
@AllArgsConstructor
@Data
@ToString
public class Lancamento implements Serializable {

    @Serial
    private static final long serialVersionUID = -334942965673806265L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Column(name = "localizacao", nullable = true)
    private String localizacao;

    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private Date dataAtualizacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Funcionario funcionario;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }
}
