package com.core.repositories;

import com.core.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "SELECT p FROM Pessoa p " +
            "LEFT JOIN Membros m " +
            " on p.id = m.idpessoa " +
            "where p.funcionario is true " +
            "and m.idprojeto is null " +
            "or m.idprojeto in (SELECT mm.idprojeto from Membros mm where mm.idprojeto != :idProjeto) "
    )
    Page<Pessoa> allPessoaWithPagination(Pageable pageable, Long idProjeto);

    @Query(value = "SELECT p FROM Pessoa p " +
            "LEFT JOIN Membros m " +
            " on p.id = m.idpessoa " +
            "where p.funcionario is true " +
            "and m.idprojeto in (SELECT mm.idprojeto from Membros mm where mm.idprojeto = :idProjeto) "
    )
    Page<Pessoa> allPessoaInProjectWithPagination(Pageable pageable, Long idProjeto);

    @Query(value = "SELECT p FROM Pessoa p " +
            "LEFT JOIN Membros m " +
            " on p.id = m.idpessoa " +
            "where p.funcionario is true " +
            "and m.idprojeto in (SELECT mm.idprojeto from Membros mm where mm.idprojeto = :idProjeto) "
    )
    List<Pessoa> listPessoaInProjectWithPagination(Long idProjeto);

    @Query(value = "SELECT p FROM Pessoa p " +
            "where p.funcionario is true ")
    Page<Pessoa> allPessoaWithPaginationOld(Pageable pageable);
}
