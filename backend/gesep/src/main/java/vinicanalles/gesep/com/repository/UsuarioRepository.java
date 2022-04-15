package vinicanalles.gesep.com.repository;

import org.springframework.data.repository.CrudRepository;
import vinicanalles.gesep.com.core.domain.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
