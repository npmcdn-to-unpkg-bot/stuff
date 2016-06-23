package mcstuff.bbs.model.message;

import java.util.List;

public class MessageBoard {

	/**
	 * 
	 */
	public Integer id;
	/**
	 * 
	 */
	public List<Message> messages;
	/**
	 * Getter of id
	 */
	public Integer getId() {
	 	 return id; 
	}
	/**
	 * Setter of id
	 */
	public void setId(Integer id) { 
		 this.id = id; 
	}
	/**
	 * Getter of messages
	 */
	public List<Message> getMessages() {
	 	 return messages; 
	}
	/**
	 * Setter of messages
	 */
	public void setMessages(List<Message> messages) { 
		 this.messages = messages; 
	} 

}
