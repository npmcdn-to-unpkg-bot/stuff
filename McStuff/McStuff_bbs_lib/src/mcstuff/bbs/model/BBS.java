package mcstuff.bbs.model;

import mcstuff.bbs.model.message.MessageWall;
import java.util.List;
import mcstuff.bbs.model.message.MessageBoard;

public class BBS {
	private Long id;
	private String tag;
	private List<MessageWall> messageWalls;
}
