package mcstuff.bbs.model.message;

import java.io.Serializable;
import java.util.List;

import mcstuff.bbs.model.BBS;

public class MessageWall  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BBS bbs;
	private List<MessageBoard> messageBoards;
}
