package SG.com.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SG.com.common.CommandMap;
import SG.com.member.service.PointService;
import SG.com.member.service.JoinService;
import SG.com.member.service.MemberService;
//마이페이지 컨트롤러 
@Controller
public class MemberController 
{
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="pointService")
	private PointService pointService;
	//마이페이지 
	@RequestMapping(value = "/mypage")
	public String mypage(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		String mem_num = session.getAttribute("MEMBER_NO").toString();
		String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		Map<String, Object> sumTradeMoney = memberService.sumTradeMoney(commandMap.getMap());
		
		model.addAttribute("sumPoint", sumPoint.get("SUM"));
		model.addAttribute("sumTradeMoney", sumTradeMoney.get("SUM"));
		
		return "mypage";
	}
	
	//회원 정보 조회
	@RequestMapping(value = "/memberInfo")
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
      
		return "memberInfo";
	}
	
	//회원 정보 수정 폼
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(Model model) 
	{
		return "memUpdateForm";
	}
	
	//회원 정보 수정 처리
	@RequestMapping(value = "/memberUpdateAction")
	public String memUpdateAction(Model model) 
	{
		return "memUpdateAction";
	}
	
	//회원 정보 삭제
	@RequestMapping(value = "/memberDeleteForm")
	public String memDeleteForm(Model model) 
	{
		return "memDeleteForm";
	}
	
	//회원 정보 삭제 처리
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(Model model) 
	{
		return "redirect:main";
	}
		
}
