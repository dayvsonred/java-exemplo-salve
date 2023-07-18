package com.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_MEMBROS", schema = "jsf")
public class Membros implements Serializable {

    @Id
    private Long idprojeto;
    @Id
    private Long idpessoa;
}
