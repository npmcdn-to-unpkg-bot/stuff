package person.mdc.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import person.mdc.web.model.entities.bbs.MessageBoard;
import person.mdc.web.model.entities.bbs.MessageBoardRepository;

@RestController("bbsController")
@RequestMapping(value = "rest/bbs")
public class BBSController {
	
	@Autowired
	private MessageBoardRepository messagBoardRepository;
	
	@RequestMapping(value = "/messageBoard", method = {RequestMethod.GET})
	public Collection<MessageBoard> getMessageBoards() {
		List<MessageBoard> boards = new ArrayList<>(); 
		boards.addAll((Collection<? extends MessageBoard>) this.messagBoardRepository.findAll());
		return boards;
	}
}
