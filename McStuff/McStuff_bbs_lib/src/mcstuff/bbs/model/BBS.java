package mcstuff.bbs.model;

import java.io.Serializable;
import java.util.List;

import mcstuff.bbs.model.message.MessageWall;

public class BBS implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tag;
	private List<MessageWall> messageWalls;
}
