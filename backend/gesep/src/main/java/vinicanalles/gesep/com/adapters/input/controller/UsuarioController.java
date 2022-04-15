package vinicanalles.gesep.com.adapters.input.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicanalles.gesep.com.core.domain.dto.UsuarioDto;
import vinicanalles.gesep.com.core.domain.entities.Usuario;
import vinicanalles.gesep.com.repository.UsuarioRepository;

import java.util.Optional;

import static vinicanalles.gesep.com.adapters.input.controller.mapping.GesepRouteMapping.CONTEXTO_GESEP_USUARIO;

@RestController
@RequestMapping(CONTEXTO_GESEP_USUARIO)
public class UsuarioController {

    private ObjectMapper mapper;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
        mapper = new ObjectMapper();
    }

    @GetMapping
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable long id) {
        Optional<Usuario> optUser = usuarioRepository.findById(id);
        return optUser.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = mapper.convertValue(usuarioDto, Usuario.class);
        Usuario usuarioCreated = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Usuario> updateUser(@RequestBody UsuarioDto usuarioDto, @PathVariable("id") long id) {
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = mapper.convertValue(usuarioDto, Usuario.class);
            usuario.setId(id);
            Usuario usuarioCreated = usuarioRepository.save(usuario);

            return new ResponseEntity<>(usuarioCreated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") long id) {Optional<Usuario> optUser = usuarioRepository.findById(id);
        if (optUser.isPresent()) {
            Usuario usuario = optUser.get();

            usuarioRepository.delete(usuario);

            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
