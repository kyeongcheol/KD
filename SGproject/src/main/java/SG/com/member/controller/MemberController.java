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
import org.springframework.web.bind.annotation.ResponseBody;

import SG.com.common.CommandMap;
import SG.com.member.service.PointService;
import SG.com.member.service.LoginService;
import SG.com.member.service.MemberService;
//���������� ��Ʈ�ѷ� 
@Controller
public class MemberController 
{
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="pointService")
	private PointService pointService;
	
	@Resource(name ="loginService")
	private LoginService loginService;
	//���������� 
	@RequestMapping(value = "/mypage")
	public String mypage(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		String mem_num = session.getAttribute("MEMBER_NO").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		
		model.addAttribute("sumPoint", sumPoint.get("SUM"));
		
		return "mypage";
	}
	
	//ȸ�� ���� ��ȸ 0713��ħ ����
	@RequestMapping(value = "/memberInfo")
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
      /*  String mem_num = session.getAttribute("MEMBER_NO").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		model.addAttribute("sumPoint", sumPoint.get("SUM"));*/
		System.out.println("����");
		
		String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
		System.out.println(myInfo.get("MEMBER_ID"));
		model.addAttribute("myInfo", myInfo);
		

		
		return "Member/member_Info";
	}
	
	//ȸ�� ���� ���� ��
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception
	{ 
        System.out.println("����");
		
		String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
		System.out.println(myInfo.get("MEMBER_ID"));
		model.addAttribute("myInfo", myInfo);
		  
		String email = (String)myInfo.get("MEMBER_EMAIL");
		
		System.out.println(email);
		
		//�Է��� email �ּҸ� @�� �������� �ɰ�
		String [] split_email = email.split("@");
		
		//���� ���� ����(�̸���) : ex) ykc90831@gmail.com
	    session.setAttribute("email1", split_email[0].toString()); // ykc90831
	    session.setAttribute("email2", split_email[1].toString()); // gmail.com

		return "memUpdateForm";
	}
	
	//ȸ�� ���� ���� ó��
	@RequestMapping(value = "/memberUpdateAction")
	public String memUpdateAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		String MEMBER_EMAIL = request.getParameter("MEMBER_EMAIL1")+"@"+request.getParameter("MEMBER_EMAIL2");
        Map<String, Object> memberMap = new HashMap<String, Object>();
		
        memberMap = commandMap.getMap();
        memberMap.put("MEMBER_EMAIL", MEMBER_EMAIL);
        memberService.updateMyinfo(memberMap);
        
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
	public String memDeleteAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
       String mem_num = session.getAttribute("MEMBER_NO").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		
		 memberService.deleteMember(commandMap.getMap());
		
		 session.invalidate();
		 
		return "redirect:main";
	}
}
