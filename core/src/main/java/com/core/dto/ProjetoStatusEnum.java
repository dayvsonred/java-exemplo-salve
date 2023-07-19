package com.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjetoStatusEnum {

    EM_ANALISE("em análise"),
    ANALISE_REALIZADA("análise realizada"),
    ANALISE_APROVADA("análise aprovada"),
    INICIADO("iniciado"),
    PLANEJADO("planejado"),
    EM_ANDAMENTO("em andamento"),
    ENCERRADO("encerrado"),
    CANCELADO("cancelado");

    private final String status;

    @JsonCreator
    public static ProjetoStatusEnum create(String status) {
        for (ProjetoStatusEnum e : ProjetoStatusEnum.values()) {
            if (e.getStatus().equals(status)) {
                return e;
            }
        }
        return CANCELADO;
    }
}
