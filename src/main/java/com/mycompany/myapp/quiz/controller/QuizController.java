package com.mycompany.myapp.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.member.dto.MemberDTO;
import com.mycompany.myapp.member.dto.MemberDtoContainStudyroom;
import com.mycompany.myapp.quiz.dto.ChatRoomInfoOfMember;
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.QuizDtoForList;
import com.mycompany.myapp.quiz.dto.SelectQuizNumAndSubjectVO;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;
import com.mycompany.myapp.quiz.service.QuizService;
import com.mycompany.myapp.quiz.service.QuizServiceInterface;
import com.mycompany.myapp.studyroom.domain.StudyroomDto;
import com.mycompany.myapp.studyroom.domain.SubjectDto;
import com.mycompany.myapp.studyroom.service.StudyroomService;
import com.mycompany.myapp.utils.PageMaker;
import com.mycompany.myapp.utils.QuizSearchCriteria;

@Controller
public class QuizController {
	private QuizServiceInterface ser;
	private StudyroomService studyroomService;
	@Autowired
	public void setQuizServiceInterface(QuizService ser) {
		this.ser=ser;
	}
	@Autowired
	public void setStudyroomService(StudyroomService studyroomService) {
		this.studyroomService=studyroomService;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/listQuiz", method=RequestMethod.GET)
	public String getListQuiz(QuizDtoForList dto,HttpServletRequest request,
			Model model,QuizSearchCriteria qscri) {
		List<QuizDtoForList> quizList=ser.getQuizListforLit(qscri);
		
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(qscri);
		pageMaker.setTotalCount(ser.countTotQuiz(qscri));
		System.out.println("set totla coiunt: "+ser.countTotQuiz(qscri));
		System.out.println(quizList);
		model.addAttribute("scri",qscri);
		model.addAttribute("quizList",quizList);
		model.addAttribute("pageMaker",pageMaker);
		
		return "Quiz/listQuiz";
	}
	@RequestMapping(value="/makeQuiz", method=RequestMethod.GET)
	public String getMakeQuiz(QuizDto dto,HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		MemberDTO memberDto=(MemberDTO) session.getAttribute("user");
		List<Integer> cntRollInStudy=studyroomService.selectMemberFromAllMatch(memberDto.getNum());
		List<SubjectDto> subjectList = studyroomService.getSubjectList();
		List<ChatRoomInfoOfMember> chatroomList;
		if(cntRollInStudy.size()!=0) {
			chatroomList=ser.getChatroomListofMember(memberDto.getNum());
		}else {
			chatroomList=null;
		}
		model.addAttribute("chatroomList",chatroomList);
		model.addAttribute("subjectList",subjectList);
		return "Quiz/makeQuiz";
	}
	@RequestMapping(value="/makeQuiz", method=RequestMethod.POST)
	public String postMakeQuiz(QuizDto dto,HttpServletRequest request) {
		HttpSession session=request.getSession();
		MemberDTO memberDto=(MemberDTO) session.getAttribute("user");
		//방번호 있으면 방번호 집어넣기
		System.out.println("test1");
		System.out.println(memberDto.getId());
		System.out.println(memberDto.getNum());
		//		dto.setMakerNum(memberDto.getNum());
		System.out.println("test");
		ser.insertQuiz(dto.getQuizList(),memberDto.getNum());
		return "redirect:/listQuiz";
	}
	@RequestMapping(value="/resultQuiz", method=RequestMethod.GET)
	public String getResultQuiz(QuizDto dto,HttpServletRequest request,Model model,SolveQuizVo answerVo) {
		
		return "Quiz/resultQuiz";
	}
	@RequestMapping(value="/resultQuiz", method=RequestMethod.POST)
	public String postResultQuiz(QuizDto dto,HttpServletRequest request,Model model,
			SolveQuizVo answerVo) {
		List<SolveQuizVo> answerList=answerVo.getAnswerList();
		//정답 확인 
		//세션에 문제 리스트 저장하면 여기서 가져오기
		Map<SolveQuizVo,QuizDto> returnList=new HashMap<>();
		
		for(SolveQuizVo quizVo:answerList) {
			System.out.println("resultQuiz Post");
			
			String[] temAnswer=quizVo.getAnswer().split(",");
			System.out.println(temAnswer[0]);
			if(temAnswer.length==1) {
				quizVo.setAnswer(temAnswer[0]);
			}
			quizVo.setCheckAnswer(ser.checkAnswer(quizVo,ser.getQuizOne(quizVo.getNum())));
			returnList.put(quizVo, ser.getQuizOne(quizVo.getNum()));
		}
		System.out.println("resultQuiz: "+answerList.get(0).getAnswer());
		model.addAttribute("answerList",returnList);
		return "Quiz/resultQuiz";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/solveQuiz", method=RequestMethod.GET)
	public String getSolveQuiz(SelectQuizNumAndSubjectVO solveVo,QuizDto dto,
			HttpServletRequest request,Model model,SolveQuizVo answerVo,HttpSession session) {
			
//		List<MemberDtoContainStudyroom> roomList=(List<MemberDtoContainStudyroom>) session.getAttribute("chatroomList");
//		Map<Integer,String> subList=new HashMap<Integer,String>();
//		for( MemberDtoContainStudyroom room: roomList) {
//			if(!subList.containsKey(room.getSubjectNum())) {
//				subList.put(room.getSubjectNum(), room.getSubject());
//			}
//		}
		System.out.println((Map<Integer,String>)session.getAttribute("subList"));
		model.addAttribute("chatroomList", session.getAttribute("chatroomList"));
		model.addAttribute("subList", (Map<Integer,String>)session.getAttribute("subList"));
		return "Quiz/solveQuiz";
	}
	@RequestMapping(value="/solveQuiz", method=RequestMethod.POST)
	public String postSolveQuiz(SelectQuizNumAndSubjectVO solveVo,QuizDto dto,
			HttpServletRequest request,Model model,SolveQuizVo answerVo) {
		//long subjectNum=(long)request.getAttribute("subjectNum");
		
		
		List<QuizDto> quizList=ser.getQuizList(solveVo.getSubjectNum());
		Map<Integer,QuizDto> selectMap=ser.selectQuizList(quizList, solveVo.getNumOfQuestion());
		//세션에도 퀴즈 리스트 저장하기
		
		model.addAttribute("dtos", selectMap);
		return "Quiz/solveQuiz";
	}
	@RequestMapping(value="/updateQuiz", method=RequestMethod.GET)
	public String getUpdateQuiz(Model model) {
		int makerNum=3;//나중에 삭제
		System.out.println(ser.getQuizListByMakerNum(makerNum).size());
		model.addAttribute("quizList",ser.getQuizListByMakerNum(makerNum));
		return "Quiz/updateQuiz";
	}
	@RequestMapping(value="/updateQuiz", method=RequestMethod.POST)
	public String postUpdateQuiz(Model model) {
		//int makerNum=3;//나중에 삭제
		
		return "Quiz/updateQuiz";
	}
	@RequestMapping(value="/updateQuizDetail/num/{num}",method=RequestMethod.GET)
	public String getUpdateQuizDetail(QuizDto quizDto,Model model,@PathVariable("num") long num) {
		QuizDto quiz=ser.getQuizOne(num);
		model.addAttribute("quiz",quiz);
		return "Quiz/updateQuizDetail";
	}
	@RequestMapping(value="/updateQuizDetail/num/{num}",method=RequestMethod.POST)
	public String postUpdateQuizDetail(QuizDto quizDto,Model model,@PathVariable("num") long num,HttpSession session) {
		quizDto.setMakerNum(((MemberDTO)session.getAttribute("user")).getNum());
		ser.updateQuiz(quizDto);
		return "redirect:/listQuiz";
	}
	@RequestMapping(value="/deleteQuiz/num/{num}",method=RequestMethod.GET)
	public String getDeleteQuiz(QuizDto quizDto,Model model,@PathVariable("num") long num) {
		//session을 이용하여 작성자가 맞는지 확인 필요
		ser.deletQuiz(quizDto);
		return "redirect:/listQuiz";
	}
	
}
