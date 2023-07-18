package com.core.dto;

import com.core.entities.Membros;
import lombok.*;

import javax.persistence.Column;
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

    @NotNull(message = "Id may not be null")
    private Long id;
    @NotNull(message = "nome may not be null")
    @NotBlank(message = "nome may not be blank")
    private String nome;
    private LocalDate data_inicio;
    private LocalDate data_previsao_fim;
    private LocalDate data_fim;
    @Size( max = 50000)
    private String descricao;
    @Size( max = 45)
    private String status;
    private double orcamento;
    @Size( max = 45)
    private double risco;
    @NotNull(message = "idgerente may not be null")
    private Long idgerente;
    private List<Membros> membros;
}
