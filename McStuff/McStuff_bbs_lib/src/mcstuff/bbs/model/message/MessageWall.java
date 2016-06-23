package mcstuff.bbs.model.message;

import java.util.List;

public class MessageWall {

	/**
	 * 
	 */
	public List<MessageBoard> messageboards;
	/**
	 * 
	 */
	public Integer id;
	/**
	 * Getter of messageboards
	 */
	public List<MessageBoard> getMessageboards() {
	 	 return messageboards; 
	}
	/**
	 * Setter of messageboards
	 */
	public void setMessageboards(List<MessageBoard> messageboards) { 
		 this.messageboards = messageboards; 
	}
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

}
