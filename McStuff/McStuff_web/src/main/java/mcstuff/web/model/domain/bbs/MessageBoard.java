package mcstuff.web.model.domain.bbs;

import mcstuff.web.model.dto.bbs.MessageBoardDTO;
import mcstuff.web.model.entities.bbs.MessageBoardEntity;

public class MessageBoard {
	private Long id;
	private String name;
	private String description;

	public MessageBoard() {
	}

	public MessageBoard(final MessageBoardEntity mbe) {
		id = mbe.getId();
		name = mbe.getName();
		description = mbe.getDescription();
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public MessageBoardDTO getMessageBoardDTO() {
		return new MessageBoardDTO(getId(), getName(), getDescription());
	}

	public String getName() {
		return name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
