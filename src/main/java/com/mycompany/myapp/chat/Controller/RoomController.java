package com.mycompany.myapp.chat.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.chat.dao.ChatRoomRepository;

@Controller
@RequestMapping(value="/chat")
public class RoomController {
	private final ChatRoomRepository repository;
	public RoomController(ChatRoomRepository repository) {
		this.repository=repository;
	}
	//채팅방 목록 조회
	@GetMapping(value="/rooms")
	public ModelAndView rooms() {
		ModelAndView mv=new ModelAndView("/rooms");
		mv.addObject("list",repository.findAllRooms());
		return mv;
	}
	//채팅방 개설
	@PostMapping(value="/rooms")
	public String create(@RequestParam String name,RedirectAttributes rttr) {
		rttr.addFlashAttribute("roomName",repository.createChatRoomDTO(name));
		return "redirect:/chat/rooms";
	}
	//채팅방 조회
	@GetMapping(value="/room/{roomId}")
	public String getRoom(@PathVariable String roomId,Model model) {
		model.addAttribute("room",repository.findRoomById(roomId));
		return "room";
	}
	
}
