package mcstuff.bbs.model.message;

import java.io.Serializable;
import java.util.List;

public class MessageThread  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private MessageBoard messageBoard;
	private Message rootMessage;
	public List<Message> messages;
}
