package person.mdc.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findByUsername(@Param("username") String username);
}