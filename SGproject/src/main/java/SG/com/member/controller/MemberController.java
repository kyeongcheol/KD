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
//마이페이지 컨트롤러 
@Controller
public class MemberController 
{
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="pointService")
	private PointService pointService;
	
	@Resource(name ="loginService")
	private LoginService loginService;
	//마이페이지 
	@RequestMapping(value = "/mypage")
	public String mypage(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		String mem_num = session.getAttribute("MEMBER_NO").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		
		model.addAttribute("sumPoint", sumPoint.get("SUM"));
		
		return "mypage";
	}
	
	//회원 정보 조회 0713아침 변경
	@RequestMapping(value = "/memberInfo")
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
      /*  String mem_num = session.getAttribute("MEMBER_NO").toString();
		
		commandMap.getMap().put("MEMBER_NO", mem_num);
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		model.addAttribute("sumPoint", sumPoint.get("SUM"));*/
		System.out.println("진입");
		
		String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
		System.out.println(myInfo.get("MEMBER_ID"));
		model.addAttribute("myInfo", myInfo);
		

		
		return "Member/member_Info";
	}
	
	//회원 정보 수정 폼
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception
	{ 
        System.out.println("진입");
		
		String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
		System.out.println(myInfo.get("MEMBER_ID"));
		model.addAttribute("myInfo", myInfo);
		  
		String email = (String)myInfo.get("MEMBER_EMAIL");
		
		System.out.println(email);
		
		//입력한 email 주소를 @를 기준으로 쪼갬
		String [] split_email = email.split("@");
		
		//세션 영역 저장(이메일) : ex) ykc90831@gmail.com
	    session.setAttribute("email1", split_email[0].toString()); // ykc90831
	    session.setAttribute("email2", split_email[1].toString()); // gmail.com

		return "memUpdateForm";
	}
	
	//회원 정보 수정 처리
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
	
	//회원 정보 삭제
	@RequestMapping(value = "/memberDeleteForm")
	public String memDeleteForm(Model model) 
	{
		return "memDeleteForm";
	}
	
	//회원 정보 삭제 처리
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
