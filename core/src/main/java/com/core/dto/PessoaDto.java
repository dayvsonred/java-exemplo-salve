package com.core.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDto {

    @NotNull(message = "Id may not be null")
    private Long id;
    @NotNull(message = "nome may not be null")
    @NotBlank(message = "nome may not be blank")
    private String nome;
    private LocalDate datanascimento;
    private String cpf;
    private Boolean funcionario;
}
