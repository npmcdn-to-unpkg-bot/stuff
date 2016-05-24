package person.mdc.web.model.domain.bbs;

import person.mdc.web.model.dto.bbs.MessageBoardDTO;
import person.mdc.web.model.entities.bbs.MessageBoardEntity;

public class MessageBoard {
	private Long id;
	private String name;
	private String description;
	
	public MessageBoard() {
	}
	
	public MessageBoard(MessageBoardEntity mbe) {
		this.id = mbe.getId();
		this.name = mbe.getName();
		this.description = mbe.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MessageBoardDTO getMessageBoardDTO() {
		return new MessageBoardDTO(getId(), getName(), getDescription());
	}
	
}
