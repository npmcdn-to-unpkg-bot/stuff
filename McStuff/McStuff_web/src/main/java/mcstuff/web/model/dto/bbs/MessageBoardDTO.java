package mcstuff.web.model.dto.bbs;

public class MessageBoardDTO {
	private Long id;
	private String name;
	private String description;

	public MessageBoardDTO() {
	}

	public MessageBoardDTO(final Long id, final String name, final String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
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
