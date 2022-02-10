package org.tester.chatting.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tester.chatting.room.domain.RoomVO;
import org.tester.chatting.room.service.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@RequestMapping(value = "/roomList", method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("roomList",roomService.list());
		return "roomList";
	}
	
	@RequestMapping(value = "/roomMake", method=RequestMethod.GET)
	public String make(Model model) {
		return "roomMake";
	}
	
	@RequestMapping(value = "/roomMake", method=RequestMethod.POST)
	public String make(RoomVO roomVO) {
		roomService.insert(roomVO);
		return "redirect:roomList";
	}
}
