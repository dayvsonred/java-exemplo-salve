package com.core.dto;

import com.core.entities.Pessoa;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoDto {

    private Long id;
    private String nome;
    private LocalDate data_inicio;
    private LocalDate data_previsao_fim;
    private LocalDate data_fim;
    @Size( max = 50000)
    private String descricao;
    private ProjetoStatusEnum status;
    private double orcamento;
    private String risco;
    private Boolean idgerente;
    private List<Pessoa> membros;
}
