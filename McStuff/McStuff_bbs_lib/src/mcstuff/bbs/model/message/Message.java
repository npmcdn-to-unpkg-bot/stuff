package mcstuff.bbs.model.message;

public class Message {

	/**
	 * 
	 */
	public Integer id;
	/**
	 * 
	 */
	public MessageThread messagethread;
	/**
	 * 
	 */
	public Integer threadIndex;
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
	 * Getter of messagethread
	 */
	public MessageThread getMessagethread() {
	 	 return messagethread; 
	}
	/**
	 * Setter of messagethread
	 */
	public void setMessagethread(MessageThread messagethread) { 
		 this.messagethread = messagethread; 
	}
	/**
	 * Getter of threadIndex
	 */
	public Integer getThreadIndex() {
	 	 return threadIndex; 
	}
	/**
	 * Setter of threadIndex
	 */
	public void setThreadIndex(Integer threadIndex) { 
		 this.threadIndex = threadIndex; 
	} 

}
