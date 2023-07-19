package com.core.resource;

import com.core.dto.PessoaDto;
import com.core.entities.Pessoa;
import com.core.service.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResource {

    @Autowired
    PessoaService pessoaService;

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Pessoa>> pageFindAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage
    ) {
        return ResponseEntity.ok(pessoaService.findAllPessoa(page, linesPerPage));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Pessoa> findPessoa(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(pessoaService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletingPessoa(
            @PathVariable Long id
    ) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(
            @RequestBody @Valid PessoaDto pessoaDto
    ) {
        return ResponseEntity.ok(pessoaService.createNewPessoa(pessoaDto));
    }

    @PutMapping
    public ResponseEntity<Pessoa> update(
            @RequestBody @Valid PessoaDto pessoaDto
    ) {
        return ResponseEntity.ok(pessoaService.updatePessoa(pessoaDto));
    }
}
