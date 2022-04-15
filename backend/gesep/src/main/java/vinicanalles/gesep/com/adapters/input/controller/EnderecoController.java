package vinicanalles.gesep.com.adapters.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicanalles.gesep.com.core.domain.dto.EnderecoDto;
import vinicanalles.gesep.com.core.domain.dto.UsuarioDto;
import vinicanalles.gesep.com.core.domain.entities.Endereco;
import vinicanalles.gesep.com.core.domain.entities.Usuario;
import vinicanalles.gesep.com.repository.EnderecoRepository;

import java.util.Optional;

import static vinicanalles.gesep.com.adapters.input.controller.mapping.GesepRouteMapping.CONTEXTO_GESEP_ENDERECO;

@RestController
@RequestMapping(CONTEXTO_GESEP_ENDERECO)
public class EnderecoController {

    private ObjectMapper mapper;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoController(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
        mapper = new ObjectMapper();
    }

    @GetMapping
    public Iterable<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable long id) {
        Optional<Endereco> optEndereco = enderecoRepository.findById(id);
        return optEndereco.map(endereco -> new ResponseEntity<>(endereco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Endereco> saveEndereco(@RequestBody EnderecoDto enderecoDto) {
        Endereco endereco = mapper.convertValue(enderecoDto, Endereco.class);
        Endereco enderecoCreated = enderecoRepository.save(endereco);

        return new ResponseEntity<>(enderecoCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Endereco> updateEndereco(@RequestBody EnderecoDto enderecoDto, @PathVariable("id") long id) {
        if (enderecoRepository.existsById(id)) {
            Endereco endereco = mapper.convertValue(enderecoDto, Endereco.class);
            endereco.setId(id);
            Endereco enderecoCreated = enderecoRepository.save(endereco);

            return new ResponseEntity<>(enderecoCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Endereco> deleteEndereco(@PathVariable("id") long id) {Optional<Endereco> optEndereco = enderecoRepository.findById(id);
        if (optEndereco.isPresent()) {
            Endereco endereco = optEndereco.get();

            enderecoRepository.delete(endereco);

            return new ResponseEntity<>(endereco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
