package person.mdc.web.model.entities.bbs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBoardRepository extends PagingAndSortingRepository<MessageBoardEntity, Long> {
	List<MessageBoardEntity> findByName(@Param("name") String name);
}
