package mcstuff.bbs.model;

import mcstuff.bbs.model.message.MessageWall;
import java.util.List;
import mcstuff.bbs.model.message.MessageBoard;

public class BBS {

	/**
	 * 
	 */
	public String tag;
	/**
	 * 
	 */
	public List<MessageWall> messagewalls;
	/**
	 * 
	 */
	public List<MessageBoard> messageboards;
	/**
	 * 
	 */
	public Integer id;
	/**
	 * Getter of tag
	 */
	public String getTag() {
	 	 return tag; 
	}
	/**
	 * Setter of tag
	 */
	public void setTag(String tag) { 
		 this.tag = tag; 
	}
	/**
	 * Getter of messagewalls
	 */
	public List<MessageWall> getMessagewalls() {
	 	 return messagewalls; 
	}
	/**
	 * Setter of messagewalls
	 */
	public void setMessagewalls(List<MessageWall> messagewalls) { 
		 this.messagewalls = messagewalls; 
	}
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
