package mcstuff.web.model.dto.bbs;

public class BBSInfoDTO {
	private Long id;
	private String tag;
	
	public BBSInfoDTO() {
	}

	public BBSInfoDTO(Long id, String tag) {
		super();
		this.id = id;
		this.tag = tag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		BBSInfoDTO other = (BBSInfoDTO) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		if (tag == null) {
			if (other.tag != null) return false;
		} else if (!tag.equals(other.tag)) return false;
		return true;
	}

	@Override
	public String toString() {
		return "BBSInfoDTO [id=" + id + ", tag=" + tag + "]";
	}
	
	
}
