package person.mdc.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long>{
	Privilege findByName(@Param("name") String name);
}
