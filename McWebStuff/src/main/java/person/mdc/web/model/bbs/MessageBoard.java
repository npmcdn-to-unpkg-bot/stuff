package person.mdc.web.model.bbs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "MessageBoard")
@Table(name = "MESSAGE_BOARD")
public class MessageBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
