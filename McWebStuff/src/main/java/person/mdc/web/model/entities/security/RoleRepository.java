package person.mdc.web.model.entities.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long>{
	RoleEntity findByName(@Param("name") String name);
}
