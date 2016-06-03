package mcstuff.web.model.domain.bbs;

import mcstuff.web.model.entities.bbs.BBSInfoEntity;

public class BBSInfo {
	private Long id;
	private String tag;
	
	public BBSInfo() {
	}

	public BBSInfo(BBSInfoEntity bbsInfoEntity) {
		super();
		this.id = bbsInfoEntity.getId();
		this.tag = bbsInfoEntity.getTag();
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
	
	
}
