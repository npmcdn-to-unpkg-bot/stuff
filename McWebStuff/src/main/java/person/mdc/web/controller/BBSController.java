package person.mdc.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import person.mdc.web.model.domain.bbs.MessageBoard;
import person.mdc.web.model.dto.bbs.MessageBoardDTO;
import person.mdc.web.model.entities.bbs.MessageBoardEntity;
import person.mdc.web.model.entities.bbs.MessageBoardRepository;

@RestController("bbsController")
@RequestMapping(value = "rest/bbs")
public class BBSController {
	
	@Autowired
	private MessageBoardRepository messagBoardRepository;
	
	@RequestMapping(value = "/messageBoard", method = {RequestMethod.GET})
	public Collection<MessageBoardDTO> getMessageBoards() {
		List<MessageBoardDTO> boards = new ArrayList<>();
		Iterable<MessageBoardEntity> itMBE = this.messagBoardRepository.findAll();
		for(MessageBoardEntity mbe : itMBE) {
			boards.add(new MessageBoard(mbe).getMessageBoardDTO());
		}
		return boards;
	}
}
