package com.mycompany.myapp.member.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.service.MemberService;


@Controller
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberService;
	

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	
    
	
	@RequestMapping("login.do")
	public String login() {
		logger.info("post login");
		
		return "user/login";
	}
	
	
	@RequestMapping(value="/user/logincheck.do", method=RequestMethod.POST)
	public String login(MemberDTO dto, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		MemberDTO login = memberService.login(dto);
		
		if(login == null) {
			// 세션 저장
			session.setAttribute("user", null);
			rttr.addFlashAttribute("msg", false);
			// 조건문에 의해 login 값이 null 이라면, msg 라는 정보에 false라는
			// 값이 들어가서 전송된다. 이 값은 다른 페이지로 이동하거나 새로고침을 하면 없어지는 일회용값이다.
	} else {
			session.setAttribute("user", login);
	}
	return "redirect:/";
	
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
	
	
	
	// 회원가입 GET	
	@RequestMapping(value="/register.do",method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "user/register";
	}
	
	// 회원가입 POST
	@RequestMapping(value="/registerCheck.do",method = RequestMethod.POST)
	public String registerPOST(MemberDTO dto,RedirectAttributes redirectAttributes) throws Exception {
		
		String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
		dto.setPw(hashedPw);
		memberService.register(dto);
		redirectAttributes.addFlashAttribute("msg","REGISTERED");
		
		return "redirect:/user/login.do";
	}
	
	// 회원정보 수정 GET
	@RequestMapping(value="/update.do",method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		return "user/update";
	}
	
	// 회원정보 수정 POST
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberDTO dto, HttpSession session) throws Exception {
		memberService.memberUpdate(dto);
		session.invalidate();
		return "redirect:/";
	}
}