package person.mdc.web.model.bbs;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "messageBoards", path = "messageBoards")
public interface MessageBoardRepository extends PagingAndSortingRepository<MessageBoard, Long> {
	List<MessageBoard> findByName(@Param("name") String name);
}
