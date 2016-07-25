package mcstuff.bbs.model.message;

import java.io.Serializable;
import java.util.List;

public class MessageBoard  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private MessageWall messageWall;
	private List<MessageThread> messageThreads;
}
