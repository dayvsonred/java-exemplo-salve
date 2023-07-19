package com.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_MEMBROS_eeeeee", schema = "jsf")
public class MembrosRemover implements Serializable {

    @Id
    private Long idprojeto;
    @Id
    private Long idpessoa;
}
