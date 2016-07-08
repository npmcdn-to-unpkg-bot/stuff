package mcstuff.bbs.model.message;

import java.util.List;

public class MessageThread {
	private Long id;
	private MessageBoard messageBoard;
	private Message rootMessage;
	public List<Message> messages;
}
