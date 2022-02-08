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
import com.mycompany.myapp.quiz.dto.QuizDto;
import com.mycompany.myapp.quiz.dto.SelectQuizNumAndSubjectVO;
import com.mycompany.myapp.quiz.dto.SolveQuizVo;
import com.mycompany.myapp.quiz.service.QuizService;
import com.mycompany.myapp.quiz.service.QuizServiceInterface;

@Controller
public class QuizController {
	private QuizServiceInterface ser;
	@Autowired
	public void setQuizServiceInterface(QuizService ser) {
		this.ser=ser;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value="/makeQuiz", method=RequestMethod.GET)
	public String getMakeQuiz(QuizDto dto,HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		MemberDTO memberDto=(MemberDTO) session.getAttribute("user");
		model.addAttribute("chatroomList",ser.getChatroomListofMember(memberDto.getNum()));
		return "Quiz/makeQuiz";
	}
	@RequestMapping(value="/makeQuiz", method=RequestMethod.POST)
	public String postMakeQuiz(QuizDto dto,HttpServletRequest request) {
		HttpSession session=request.getSession();
		MemberDTO memberDto=(MemberDTO) session.getAttribute("user");
		dto.setMakerNum(memberDto.getNum());
		ser.insertQuiz(dto.getQuizList());
		return "Quiz/makeQuiz";
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
			quizVo.setCheckAnswer(ser.checkAnswer(quizVo,ser.getQuizOne(quizVo.getNum())));
			returnList.put(quizVo, ser.getQuizOne(quizVo.getNum()));
		}
		System.out.println("resultQuiz: "+answerList.get(0).getAnswer());
		model.addAttribute("answerList",returnList);
		return "Quiz/resultQuiz";
	}
	@RequestMapping(value="/solveQuiz", method=RequestMethod.GET)
	public String getSolveQuiz(SelectQuizNumAndSubjectVO solveVo,QuizDto dto,
			HttpServletRequest request,Model model,SolveQuizVo answerVo) {
		
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
	public String postUpdateQuizDetail(QuizDto quizDto,Model model,@PathVariable("num") long num) {
		ser.updateQuiz(quizDto);
		return "redirect:/updateQuiz";
	}
	@RequestMapping(value="/deleteQuiz/num/{num}",method=RequestMethod.GET)
	public String getDeleteQuiz(QuizDto quizDto,Model model,@PathVariable("num") long num) {
		//session을 이용하여 작성자가 맞는지 확인 필요
		ser.deletQuiz(quizDto);
		return "redirect:/updateQuiz";
	}
}
