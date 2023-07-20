package com.core.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "jsf", name = "TB_MEMBOS")
public class Membros implements Serializable {

    @Id
    private Long idprojeto;

    @Id
    private Long idpessoa;
}
