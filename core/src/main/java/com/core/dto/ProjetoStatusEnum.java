package com.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjetoStatusEnum {

    ANALISE,
    ANALISE_REALIZADA,
    ANALISE_APROVADA,
    INICIADO,
    PLANEJADO,
    ANDAMENTO,
    ENCERRADO,
    CANCELADO;
}
