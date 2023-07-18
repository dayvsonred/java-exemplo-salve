package com.core.service;

import com.core.dto.PessoaDto;
import com.core.dto.ProjetoDto;
import com.core.entities.Pessoa;
import com.core.entities.Projeto;
import com.core.entities.User;
import com.core.repositories.PessoaRepository;
import com.core.repositories.ProjetoRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Slf4j
@Service
public class ProjetoService {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto findById(Long projetoId){
        try {
            return projetoRepository.findById(projetoId).orElseThrow(
                    () -> new NotFoundException("Not Found projetoId")
            );
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found projetoId in DB with id: " + projetoId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In find projetoId in DB with id: " + projetoId);
        }
    }

    public Projeto createNewProjeto(ProjetoDto projetoDto){
        try {
            Projeto projeto = new Projeto();
            projeto.setNome(projetoDto.getNome());
            projeto.setRisco(projetoDto.getRisco());
            projeto.setData_fim(projetoDto.getData_fim());
            projeto.setData_inicio(projetoDto.getData_inicio());
            projeto.setData_previsao_fim(projetoDto.getData_previsao_fim());
            projeto.setDescricao(projetoDto.getDescricao());
            projeto.setIdgerente(projetoDto.getIdgerente());
            projeto.setOrcamento(projetoDto.getOrcamento());
            projeto.setStatus(projetoDto.getStatus());
            return projetoRepository.save(projeto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new projeto with name: " + projetoDto.getNome());
        }
    }

    public void deleteProjeto(Long projetoId){
        try {
            Projeto projeto = this.findById(projetoId);

            projetoRepository.delete(projeto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new projeto to ID: " + projetoId);
        }
    }

    public Projeto updateProjeto(ProjetoDto projetoDto){
        try {
            Projeto projeto = this.findById(projetoDto.getId());

            projeto.setNome(projetoDto.getNome());
            projeto.setData_fim(projetoDto.getData_fim());
            projeto.setIdgerente(projetoDto.getIdgerente());
            projeto.setData_inicio(projetoDto.getData_inicio());
            projeto.setData_previsao_fim(projetoDto.getData_previsao_fim());
            projeto.setDescricao(projetoDto.getDescricao());
            projeto.setMembros(projetoDto.getMembros());
            projeto.setRisco(projetoDto.getRisco());
            projeto.setOrcamento(projetoDto.getOrcamento());
            projeto.setStatus(projetoDto.getStatus());


            return projetoRepository.save(projeto);
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "ERROR Not found projeto in DB with id: " + projetoDto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on Update new projeto to projetoDto: " + projetoDto.getId());
        }
    }

    public Page<Projeto> findAllProjeto(Integer page, Integer linesPerPage){
        try {
            Pageable paging = PageRequest.of(page, linesPerPage);
            return projetoRepository.findAll(paging);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of projeto this page: " + page);
        }
    }
}
