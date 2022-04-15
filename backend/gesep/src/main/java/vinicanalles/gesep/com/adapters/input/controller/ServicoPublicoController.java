package vinicanalles.gesep.com.adapters.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicanalles.gesep.com.core.domain.dto.ServicoPublicoDto;
import vinicanalles.gesep.com.core.domain.entities.ServicoPublico;
import vinicanalles.gesep.com.repository.ServicoPublicoRepository;

import java.util.Optional;

import static vinicanalles.gesep.com.adapters.input.controller.mapping.GesepRouteMapping.CONTEXTO_GESEP_SP;

@RestController
@RequestMapping(CONTEXTO_GESEP_SP)
public class ServicoPublicoController {

    private ObjectMapper mapper;
    private ServicoPublicoRepository servicoPublicoRepository;

    @Autowired
    public ServicoPublicoController(ServicoPublicoRepository servicoPublicoRepository) {
        this.servicoPublicoRepository = servicoPublicoRepository;
        mapper = new ObjectMapper();
    }

    @GetMapping
    public Iterable<ServicoPublico> findAll() {
        return servicoPublicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoPublico> findById(@PathVariable long id) {
        Optional<ServicoPublico> optServicoPublico = servicoPublicoRepository.findById(id);
        return optServicoPublico.map(servicoPublico -> new ResponseEntity<>(servicoPublico, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ServicoPublico> saveServicoPublico(@RequestBody ServicoPublicoDto servicoPublicoDto) {
        ServicoPublico servicoPublico = mapper.convertValue(servicoPublicoDto, ServicoPublico.class);
        ServicoPublico servicoPublicoCreated = servicoPublicoRepository.save(servicoPublico);

        return new ResponseEntity<>(servicoPublicoCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServicoPublico> updateServicoPublico(@RequestBody ServicoPublicoDto servicoPublicoDto, @PathVariable("id") long id) {
        if (servicoPublicoRepository.existsById(id)) {
            ServicoPublico servicoPublico = mapper.convertValue(servicoPublicoDto, ServicoPublico.class);
            servicoPublico.setId(id);
            ServicoPublico servicoPublicoCreated = servicoPublicoRepository.save(servicoPublico);

            return new ResponseEntity<>(servicoPublicoCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ServicoPublico> deleteServicoPublico(@PathVariable("id") long id) {
        Optional<ServicoPublico> optServicoPublico = servicoPublicoRepository.findById(id);
        if (optServicoPublico.isPresent()) {
            ServicoPublico servicoPublico = optServicoPublico.get();

            servicoPublicoRepository.delete(servicoPublico);

            return new ResponseEntity<>(servicoPublico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
