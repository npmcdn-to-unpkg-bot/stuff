package person.mdc.web.model.entities.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<PrivilegeEntity, Long>{
	PrivilegeEntity findByName(@Param("name") String name);
}
