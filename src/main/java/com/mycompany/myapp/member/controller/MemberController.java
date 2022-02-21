package com.mycompany.myapp.member.controller;




import org.slf4j.Logger;

import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;
import com.mycompany.myapp.member.service.MemberService;
import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.service.QuizService;




@Controller
public class MemberController {

	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberService memberService;
	private QuizService quizService;
	
	@Autowired
	public MemberController(MemberService memberService, QuizService quizService) {
		super();
		this.memberService = memberService;
		this.quizService = quizService;
	}

	// 로그인
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login(MemberDTO dto) {
		logger.info("post login");
		
		return "user/login";
	}
	
	//
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest request, Model model,RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		MemberDTO login = memberService.login(dto);
		
		System.out.println(dto.getPw());
		System.out.println(login.getPw());
		if(!BCrypt.checkpw(dto.getPw(), login.getPw())) {
			// 세션 저장
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			// 조건문에 의해 login 값이 null 이라면, msg 라는 정보에 false라는
			// 값이 들어가서 전송된다. 이 값은 다른 페이지로 이동하거나 새로고침을 하면 없어지는 일회용값이다.
	} else {
		System.out.println("login succece");
		
			List<MemberDtoContainStudyroom> chatroomList=memberService.getMemList(login);
			System.out.println("login session: " +chatroomList.size());
			Map<Integer,String> subList=new HashMap<Integer,String>();
			for (MemberDtoContainStudyroom sub : chatroomList) {
				if(!subList.containsKey(sub.getSubjectNum())) {
					subList.put(sub.getSubjectNum(), sub.getSubject());
				}
			}
			System.out.println("login session: " +subList.size());
			
			session.setAttribute("chatroomList", chatroomList);
			session.setAttribute("subList", subList);
			
			session.setAttribute("user", login);
			model.addAttribute("user", login);
			//rttr.addFlashAttribute("user",login);
			return "redirect:/";
	}
	return "redirect:/login.do";
	
	/*로그인이 실패하면 어떠한 값도 넘어오지 않으니 null이 되고
	 * 성공하면 매퍼에 있는 쿼리문에 대한 결과가 넘어오게 된다.
	 * 
	 * 조건문을 이용해 이 값이 있는지 없는지를 확인하고,
	 * 이에 맞게 세션 user에 값을 넣어 준다.
	 * user에는 매퍼에 있는 쿼리문의 결과가 들어가 있다.*/ 
}




	// 로그아웃 처리
	@RequestMapping(value="logout.do",method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("get logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//마이페이지
		@RequestMapping(value="/mypage.do")
		public String myPage() {
			return "/user/mypage";
		}
	
	
	// 회원가입 GET	
	@RequestMapping(value="/user/register.do",method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "user/register";
	}
	
	// 회원가입 POST
	@RequestMapping(value="/user/register.do",method = RequestMethod.POST)
	public String registerPOST(MemberDTO dto,RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("register post");
		String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
		dto.setPw(hashedPw);
		memberService.register(dto);
		redirectAttributes.addFlashAttribute("msg","REGISTERED");
		
		int result = memberService.idCheck(hashedPw);
		try {
			if(result == 1) {
				return "/user/login";
			}else if(result == 0) {
				memberService.register(dto);
			}
			// 여기에서 ~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기
			// 존재하지 않는다면 -> register
		} catch(Exception e) {
			throw new RuntimeException();
		}
		return "redirect:/login.do";
	}
	
	// 회원정보 수정 GET
	@RequestMapping(value="/user/update.do",method = RequestMethod.GET)
	public String registerUpdateView(HttpSession session,Model model) throws Exception {
		MemberDTO mDto=(MemberDTO) session.getAttribute("user");
		model.addAttribute("user",mDto);
		return "user/update";
	}
	
	// 회원정보 수정 POST
	@RequestMapping(value="/user/update.do", method=RequestMethod.POST)
	public String registerUpdate(MemberDTO dto, HttpSession session) throws Exception {
		String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
		dto.setPw(hashedPw);
		memberService.memberUpdate(dto);
		session.invalidate();
		return "redirect:/login.do";
	}
	

	//내가 가입한 스터디룸 목록 가져오기
	@RequestMapping(value="/user/getStudyrooms", method = RequestMethod.GET)
	public String getMyStudyrooms(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		MemberDTO user = (MemberDTO)session.getAttribute("user");
		List<ChatRoomInfoOfMember> chatroomList = quizService.getChatroomListofMember(user.getNum());
		model.addAttribute("chatroomList", chatroomList);
		
		return "/user/chatroomList";
	}
	
	// 아이디 중복체크
	// ResponseBody는 스프링에서 비동기 처리를 할 때 사용하는 어노테이션이다
	@ResponseBody
	@RequestMapping(value="/user/idCheck.do",method=RequestMethod.POST)
	public int postIdCheck(String id) throws Exception {
		int result = memberService.idCheck(id);	
		return result;
	}
	
	// 아이디 찾기 페이지 이동
	@RequestMapping(value = "/user/GoFindId.do")
	public String GofindId() throws Exception {
		return "/user/findIdForm";
	}
	
	// 아이디 찾기 실행
	@RequestMapping(value="/user/FindId.do", method = RequestMethod.POST)
	public String findIdAction(MemberDTO dto, Model model) throws Exception{
		MemberDTO user = memberService.userFindId(dto);
		
		if(user == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
			model.addAttribute("id", user.getId());
		}
		return "/user/findIdForm";
	}
	
	// 비밀번호 찾기 페이지 이동
	@RequestMapping(value="/user/findpw.do",method = RequestMethod.GET)
	public String GofindPw() throws Exception {
		return "/user/findPwForm";
	}
	
	// 비밀번호 찾기 실행
	@RequestMapping(value ="/user/findpw.do", method = RequestMethod.POST)
	public void findPwAction(@ModelAttribute MemberDTO dto, HttpServletResponse response) throws Exception{
		memberService.userFindPw(response, dto);

	}
	
	
	
	
}