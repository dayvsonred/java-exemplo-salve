package com.core.service;

import com.core.dto.PessoaDto;
import com.core.entities.Pessoa;
import com.core.entities.User;
import com.core.repositories.PessoaRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class PessoaService {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa findById(Long pessoaId){
        try {
            return pessoaRepository.findById(pessoaId).orElseThrow(
                    () -> new NotFoundException("Not Found PessoaId")
            );
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found pessoaId in DB with id: " + pessoaId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In find pessoaId in DB with id: " + pessoaId);
        }
    }

    public Pessoa createNewPessoa(PessoaDto pessoaDto){
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(pessoaDto.getNome());
            pessoa.setCpf(pessoaDto.getCpf());
            pessoa.setFuncionario(pessoaDto.getFuncionario());
            pessoa.setDatanascimento(pessoaDto.getDatanascimento());
            return pessoaRepository.save(pessoa);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Pessoa to User: " + pessoaDto.getNome());
        }
    }

    public void deletePessoa(Long pessoaId){
        try {
            Pessoa pessoa = this.findById(pessoaId);

            pessoaRepository.delete(pessoa);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on create new Pessoa to ID: " + pessoaId);
        }
    }

    public Pessoa updatePessoa(PessoaDto pessoaDto){
        try {
            Pessoa pessoa = this.findById(pessoaDto.getId());

            pessoa.setNome(pessoaDto.getNome());
            pessoa.setCpf(pessoaDto.getCpf());
            pessoa.setDatanascimento(pessoaDto.getDatanascimento());
            pessoa.setFuncionario(pessoaDto.getFuncionario());

            return pessoaRepository.save(pessoa);
        }catch (ResponseStatusException n){
            log.error(n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "ERROR Not found Pessoa in DB with id: " + pessoaDto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on Update new Pessoa to pessoaDto: " + pessoaDto.getId());
        }
    }

    public Page<Pessoa> findAllPessoa(Integer page, Integer linesPerPage){
        try {
            Pageable paging = PageRequest.of(page, linesPerPage);
            return pessoaRepository.findAll(paging);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Pessoa this page: " + page);
        }
    }

    public Page<Pessoa> findAllPessoaToLink(Integer page, Integer linesPerPage, Long idProjeto){
        try {
            Pageable paging = PageRequest.of(page, linesPerPage);
            return pessoaRepository.allPessoaWithPagination(paging, idProjeto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Pessoa this page: " + page);
        }
    }

    public Page<Pessoa> findAllPessoaInProject(Integer page, Integer linesPerPage, Long idProjeto){
        try {
            Pageable paging = PageRequest.of(page, linesPerPage);
            return pessoaRepository.allPessoaInProjectWithPagination(paging, idProjeto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR on find pages of Pessoa this page: " + page);
        }
    }

    public Pessoa findPessoaForAddProjeto(Long pessoaId){
        try {
            Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(
                    () -> new NotFoundException("Not Found PessoaId")
            );
            //O sistema deve permitir associar membros aos projetos que tem atribuição funcionário
            if(pessoa.getFuncionario()){
                return pessoa;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ERROR Not pessoa not is a funcionario id: " + pessoaId);
        }catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ERROR Not found pessoaId in DB with id: " + pessoaId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In find pessoaId in DB with id: " + pessoaId);
        }
    }

    public List<Pessoa> findListPessoainProjeto(Long projetoId){
        try {
            return pessoaRepository.listPessoaInProjectWithPagination(projetoId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("ERROR In find pessoa in projetoId: " + projetoId);
        }
    }


}
