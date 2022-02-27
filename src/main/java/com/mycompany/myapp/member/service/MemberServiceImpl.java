package com.mycompany.myapp.member.service;



import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mycompany.myapp.member.dao.MemberDAO;
import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDao;
	

	public MemberServiceImpl(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	// 로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return memberDao.login(dto);
	}
	
	// 회원가입
	@Override
	public void register(MemberDTO dto) throws Exception {
		memberDao.register(dto);
		
	}
	

	// 회원정보 수정
	// Controller에서 보내는 파라미터들을 memberUpdate(MemberDTO dto)로 받고
	@Override
	public void memberUpdate(MemberDTO dto) throws Exception {
		// 받은 dto를 DAO로 보내준다
		memberDao.memberUpdate(dto);
		
	}

	@Override
	public MemberDTO selectMemberByNum(int memberNum) throws Exception {
		return memberDao.selectMemberByNum(memberNum);
	}
	
	@Override
	public List<MemberDtoContainStudyroom> getMemList(MemberDTO dto) {
		return memberDao.getStudyroomInfo(dto);
	}
	

	// 아이디 찾기
	@Override
	public MemberDTO userFindId(MemberDTO dto) throws Exception {
		return memberDao.userFindId(dto);
	}
	
	//아이디 중복체크
	@Override
	public int idCheck(String id) throws Exception {
		return memberDao.idCheck(id);
	}

	// 회원조회
	@Override
	public MemberDTO readMember(String id) throws Exception {
		return memberDao.readMember(id);
	}
	
	
	// 비밀번호 찾기 이메일발송
	@Override
	public void sendEmail(MemberDTO dto, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "limokyung929@gmail.com";
		String hostSMTPpwd = "Log157094*";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "limokyung929@gmail.com";
		String fromName = "Log157094*";
		String subject = "스터디룸 임시 비밀번호입니다";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "스터디룸 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += dto.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += dto.getPw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = dto.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); 

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		
	}
	
	// 비밀번호 찾기
	@Override
	public void userFindPw(HttpServletResponse response, MemberDTO dto) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		MemberDTO ck = memberDao.readMember(dto.getId());
		PrintWriter out = response.getWriter();
		// 아이디가 없으면
		if(memberDao.idCheck(dto.getId()) == 0) {
			out.print("등록되지 않은 아이디입니다.");
			out.close();
		}
		// 가입된 이메일이 아니면
		else if(!dto.getEmail().equals(ck.getEmail())) {
			out.print("등록되지 않은 이메일입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			dto.setPw(pw);
			
			// 비밀번호 변경 메일 발송
			sendEmail(dto, "findpw");
			// 비밀번호 변경			
			String hashedPw = BCrypt.hashpw(dto.getPw(),BCrypt.gensalt());
			dto.setPw(hashedPw);
			memberDao.userFindPw(dto);
			out.print("이메일로 임시 비밀번호를 발송하였습니다. 마이페이지에서 비밀번호를 변경해주세요");
			out.close();
		}
		
	}

	@Override
	public int deleteMember(String id) throws Exception {
		return memberDao.deleteMember(id);
	}
		
	
	
	
	

}	
