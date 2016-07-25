package mcstuff.bbs.model.message;

import java.io.Serializable;

import mcstuff.bbs.model.security.User;

public class Message  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private MessageThread messageThread;
	private Integer threadIndex;
	private User author;
	private String subject;
	private String content;
	
}
