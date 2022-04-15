package vinicanalles.gesep.com.adapters.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicanalles.gesep.com.core.domain.dto.SolicitacaoDto;
import vinicanalles.gesep.com.core.domain.entities.Solicitacao;
import vinicanalles.gesep.com.repository.SolicitacaoRepository;

import java.util.Optional;

import static vinicanalles.gesep.com.adapters.input.controller.mapping.GesepRouteMapping.CONTEXTO_GESEP_SOLICITACOES;

@RestController
@RequestMapping(CONTEXTO_GESEP_SOLICITACOES)
public class SolicitacaoController {

    private ObjectMapper mapper;
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public SolicitacaoController(SolicitacaoRepository solicitacaoRepository){
        this.solicitacaoRepository = solicitacaoRepository;
        mapper = new ObjectMapper();
    }

    @GetMapping
    public Iterable<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> findById(@PathVariable long id) {
        Optional<Solicitacao> optSolicitacao = solicitacaoRepository.findById(id);
        return optSolicitacao.map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Solicitacao> saveSolicitacao(@RequestBody SolicitacaoDto solicitacaoDto) {
        Solicitacao solicitacao = mapper.convertValue(solicitacaoDto, Solicitacao.class);
        Solicitacao solicitacaoCreated = solicitacaoRepository.save(solicitacao);

        return new ResponseEntity<>(solicitacaoCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Solicitacao> updateSolicitacao(@RequestBody SolicitacaoDto solicitacaoDto, @PathVariable("id") long id) {
        if (solicitacaoRepository.existsById(id)) {
            Solicitacao solicitacao = mapper.convertValue(solicitacaoDto, Solicitacao.class);
            solicitacao.setIdSolicitacao(id);
            Solicitacao solicitacaoCreated = solicitacaoRepository.save(solicitacao);

            return new ResponseEntity<>(solicitacaoCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Solicitacao> deleteSolicitacao(@PathVariable("id") long id) {
        Optional<Solicitacao> optSolicitacao = solicitacaoRepository.findById(id);
        if (optSolicitacao.isPresent()) {
            Solicitacao solicitacao = optSolicitacao.get();

            solicitacaoRepository.delete(solicitacao);

            return new ResponseEntity<>(solicitacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
