package com.core.resource;

import com.core.dto.PessoaDto;
import com.core.dto.ProjetoDto;
import com.core.entities.Pessoa;
import com.core.entities.Projeto;
import com.core.service.ProjetoService;
import com.core.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/projeto")
public class ProjetoResource {

    @Autowired
    ProjetoService projetoService;

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Projeto>> pageFindAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage
    ) {
        return ResponseEntity.ok(projetoService.findAllProjeto(page, linesPerPage));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Projeto> find(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(projetoService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleting(
            @PathVariable Long id
    ) {
        projetoService.deleteProjeto(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Projeto> create(
            @RequestBody @Valid ProjetoDto projetoDto
    ) {
        return ResponseEntity.ok(projetoService.createNewProjeto(projetoDto));
    }
}
