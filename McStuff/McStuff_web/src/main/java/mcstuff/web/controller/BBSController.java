package mcstuff.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mcstuff.web.model.domain.bbs.MessageBoard;
import mcstuff.web.model.dto.bbs.MessageBoardDTO;
import mcstuff.web.model.entities.bbs.MessageBoardEntity;
import mcstuff.web.model.entities.bbs.MessageBoardRepository;

@RestController("bbsController")
@RequestMapping(value = "rest/bbs")
public class BBSController {

	@Autowired
	private MessageBoardRepository messagBoardRepository;

	@RequestMapping(value = "/messageBoard", method = {
		RequestMethod.GET
	})
	public Collection<MessageBoardDTO> getMessageBoards() {
		final List<MessageBoardDTO> boards = new ArrayList<>();
		final Iterable<MessageBoardEntity> itMBE = messagBoardRepository.findAll();
		for (final MessageBoardEntity mbe : itMBE) {
			boards.add(new MessageBoard(mbe).getMessageBoardDTO());
		}
		return boards;
	}
}
