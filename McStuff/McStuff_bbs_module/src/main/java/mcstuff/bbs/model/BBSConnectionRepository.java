package mcstuff.bbs.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BBSConnectionRepository extends CrudRepository<BBSConnection, Long> {

}
