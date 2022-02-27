package com.mycompany.myapp.chat.Controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import com.mycompany.myapp.chat.domain.Chat;
import com.mycompany.myapp.chat.service.ChatService;
import com.mycompany.myapp.member.dto.MemberDTO;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate messageTemplate;
	
	//채팅창으로 입장
	@RequestMapping(value="/enter/chat.do", method=RequestMethod.GET)
	public ModelAndView enterChat(@RequestParam("roomNo") int roomNo,
									ModelAndView mv, HttpSession session) {
		
		List<Chat> firstList=chatService.selectFirtChatList(roomNo);
		mv.addObject("roomNo", roomNo);
		mv.addObject("firstList", firstList);
		mv.setViewName("chat/chatting");
		return mv;
	}
	@RequestMapping(value="/test/enter.do")
	public String enter() {
		return "chat/change";
	}
	//채팅 내역 가져오기//ajax로 이용하는 url
	@RequestMapping("/chat/chatList.do")
	@ResponseBody
	public List<Chat> selectChatList(@RequestParam int roomNo,@RequestParam int endNo){
		Map<String,Object> map=new HashMap<>();
		List<Chat> list=chatService.selectChatList(map);
		System.out.println("chatList");
		return list;
	}
	//채팅메세지 전달
	@MessageMapping("/hello/{roomNo}")
	@SendTo("/subscribe/chat/{roomNo}")
	public Chat broadCating(Chat chat) {
		Map<String,Object> map=new HashMap<>();
		map.put("chat", chat);
		
		chat.setSendDate(new Date(System.currentTimeMillis()));
		System.out.println(chat.getSendDate().toString());
		System.out.println("broadCating: "+chat);
		
		chatService.InsertChat(chat);
		SimpleDateFormat dateForm=new SimpleDateFormat("YY/MM/DD HH:mm");
		chat.setNowTime(dateForm.format(chat.getSendDate()));
		return chat;
		
	}
	//파일 업로드
	
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String fileUpload(@RequestParam("roomNo") int roomNo,Model model) {
		model.addAttribute("roomNo",roomNo);
		return "/chat/file";
	}
	
	@RequestMapping(value="/file", method = RequestMethod.POST)
	@ResponseBody
	public Chat fileUpload(MultipartHttpServletRequest request ,Model model) {
	        
			int roomNo=Integer.parseInt(request.getParameter("roomNo"));
			String savePath="/resources/uploadFile";
	        String rootUploadDir = request.getSession().getServletContext().getRealPath(savePath);// C:/Upload
	        System.out.println("save path: "+rootUploadDir);
	        File dir = new File(rootUploadDir); 
	        
	        if(!dir.exists()) { //업로드 디렉토리가 존재하지 않으면 생성
	            dir.mkdirs();
	        }
	        
	        Iterator<String> iterator = request.getFileNames(); //업로드된 파일정보 수집(2개 - file1,file2)
	        
	        int fileLoop = 0;
	        String uploadFileName;
	        MultipartFile mFile = null;
	        String orgFileName = ""; //진짜 파일명
	        String sysFileName = ""; //변환된 파일명
	        String extension="";
	        ArrayList<String> list = new ArrayList<String>();
	        
	        while(iterator.hasNext()) {
	            fileLoop++;
	            
	            uploadFileName = iterator.next();
	            mFile = request.getFile(uploadFileName);
	            
	            orgFileName = mFile.getOriginalFilename();    
	            System.out.println("orgFileName: "+orgFileName);
	            System.out.println("contain "+orgFileName.contains("."));
	            
	            extension=orgFileName.substring(orgFileName.lastIndexOf("."));
	            //String extension=orgFileName.split(".")[1];
	            
	            if(orgFileName != null && orgFileName.length() != 0) { //sysFileName 생성
	                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDDHHmmss-" + fileLoop);
	                Calendar calendar = Calendar.getInstance();
	                sysFileName = simpleDateFormat.format(calendar.getTime()); //sysFileName: 날짜-fileLoop번호
	                System.out.println("sysFileName "+sysFileName);
	                System.out.println("orgFileName "+orgFileName);
	                try {
	                    mFile.transferTo(new File(dir + File.separator + sysFileName+extension)); // C:/Upload/file/sysFileName
	                    list.add("원본파일명: " + orgFileName + ", 시스템파일명: " + sysFileName);
	                    
	                }catch(Exception e){
	                    list.add("파일 업로드 중 에러발생!!!");
	                }
	            }//if
	        }//while
	        String fileName=sysFileName+extension;
	        Chat chat=new Chat();
	        HttpSession session=request.getSession();
	        MemberDTO dto= (MemberDTO)session.getAttribute("user");
	        chat.setNickname(dto.getNickname());
	        chat.setSrNo(roomNo);
	        chat.setSender(Integer.toString(dto.getNum()));
	        chat.setChatContent(orgFileName);
	        chat.setSendDate(new Date(System.currentTimeMillis()));
	        chat.setDateType("f");
	        chat.setSysName(fileName);
	        
	        chatService.InsertChat(chat);
	        messageTemplate.convertAndSend("/subscribe/chat/"+roomNo, chat);
	        System.out.println("file upload end");
	        return chat;
	    }

}
