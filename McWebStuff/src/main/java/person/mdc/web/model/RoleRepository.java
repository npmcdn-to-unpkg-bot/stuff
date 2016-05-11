package person.mdc.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByName(@Param("name") String name);
}
