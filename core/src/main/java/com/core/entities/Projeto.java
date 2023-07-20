package com.core.entities;

import com.core.dto.ProjetoStatusEnum;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "jsf", name = "TB_PROJETO")
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate data_inicio;
    private LocalDate data_previsao_fim;
    private LocalDate data_fim;
    @Column(length = 50000, columnDefinition = "TEXT")
    private String descricao;
    private ProjetoStatusEnum status;
    private double orcamento;
    private String risco;
    @NotNull
    private Long idgerente;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "TB_MEMBROS",  schema = "jsf",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa")
    )
    private List<Pessoa> membros;

}
