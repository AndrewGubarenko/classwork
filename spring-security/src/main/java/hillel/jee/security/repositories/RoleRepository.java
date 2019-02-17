package hillel.jee.security.repositories;

import hillel.jee.security.domain.Role;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
  Set<Role> findAllByNameIn(Set<String> names);
}
