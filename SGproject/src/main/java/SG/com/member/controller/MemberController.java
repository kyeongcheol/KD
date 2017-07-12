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
//���������� ��Ʈ�ѷ� 
@Controller
public class MemberController 
{
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="pointService")
	private PointService pointService;
	//���������� 
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
	
	//ȸ�� ���� ��ȸ
	@RequestMapping(value = "/memberInfo")
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
      
		return "memberInfo";
	}
	
	//ȸ�� ���� ���� ��
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(Model model) 
	{
		return "memUpdateForm";
	}
	
	//ȸ�� ���� ���� ó��
	@RequestMapping(value = "/memberUpdateAction")
	public String memUpdateAction(Model model) 
	{
		return "memUpdateAction";
	}
	
	//ȸ�� ���� ����
	@RequestMapping(value = "/memberDeleteForm")
	public String memDeleteForm(Model model) 
	{
		return "memDeleteForm";
	}
	
	//ȸ�� ���� ���� ó��
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(Model model) 
	{
		return "redirect:main";
	}
		
}
