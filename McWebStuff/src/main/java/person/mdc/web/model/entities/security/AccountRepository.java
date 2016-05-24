package person.mdc.web.model.entities.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
	AccountEntity findByUsername(@Param("username") String username);
}