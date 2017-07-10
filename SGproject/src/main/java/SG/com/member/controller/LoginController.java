package SG.com.member.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import SG.com.common.CommandMap;
import SG.com.member.service.LoginService;

@Controller
public class LoginController 
{
	@Resource(name="loginService")
	private LoginService loginService;
	
	//로그인 폼
	@RequestMapping(value = "/loginForm")
	public String login(Model model) 
	{
		return "loginForm";
	}
	
	//로그인 성공 여부 체크(로그인 처리)
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request)
	throws Exception
	{
		//해당 아이디로 검색하여 회원 정보 가져오기
		Map<String, Object> loginChk = loginService.selectId(commandMap.getMap());

		//가입 되어 있지 않으면
		if(loginChk == null)
		{
			//입력한 아이디가 없다는 메시지 출력
			model.addAttribute("message", "해당 아이디가 없습니다.");
			return "loginForm";
			
		}
		//해당 회원정보가 있으면
		else
		{
			 System.out.println("비밀번호1 : " + loginChk.get("MEMBER_PASSWORD") 
			    + "\n 비밀번호2 : " + commandMap.get("MEMBER_PASSWORD"));
			 
			//비밀번호가 일치하면
		    if(loginChk.get("MEMBER_PASSWORD").toString().
		    		equals(commandMap.get("MEMBER_PASSWORD").toString()))
		    {
		    	//loginChk Map을 "MEMBER" 영역에 저장
		    	model.addAttribute("MEMBER", loginChk);
		    	//세션 영역 저장(아이디, 회원 이름, 회원번호)
		    	session.setAttribute("MEMBER_ID", commandMap.get("MEMBER_ID"));
		    	session.setAttribute("MEMBER_NO", loginChk.get("MEMBER_NO"));
		    	session.setAttribute("MEMBER_NAME", loginChk.get("MEMBER_NAME"));
		    	
		    	return "redirect:/main";
		    }		    		    
		    	model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
		    	return "loginForm";		    
		}
	}
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request, HttpSession session, CommandMap commandMap) 
	{
		//getSession(false) : 현재 세션이 존재하면 기존 세션 리턴, 없으면 null값 리턴
		session = request.getSession(false);
		
		//현재 세션이 존재하면
		if(session != null)
		{
			//세션 소멸
			session.invalidate();
		}
		
		return "redirect:/main";
	}
	
	//아이디 찾기 폼 
	@RequestMapping(value = "/login/findIdForm")
	public String findIdForm(Model model) 
	{
		return "Member/find_Id";
	}
	
	//아이디 찾기 처리
	@RequestMapping(value = "/login/findId")
	public @ResponseBody String findId(Model model, CommandMap commandMap)
	throws Exception
	{
		//ajax jason data
		String idname = (String)commandMap.getMap().get("idname"); 
		String idemail = (String)commandMap.getMap().get("idemail"); 
		
		System.out.println("아이디 찾기");
		//json data -> commandMap에 쿼리 컬럼명과 매핑하여 넣어줌
		commandMap.put("MEMBER_NAME_FIND", idname);
		commandMap.put("MEMBER_EMAIL_FIND", idemail);
		
		//쿼리 반환 값 String 변수로 받음
		String findId = loginService.findId(commandMap.getMap());
        System.out.println("찾은 아이디 : " + findId);
        
		return findId;
	}
	
	//비밀번호 찾기 폼
	@RequestMapping(value = "/login/findPwForm")
	public String findPwForm(Model model)
	{
		return "Member/find_Pw";
	}
	
	//비밀번호 찾기 처리
	@RequestMapping(value = "/login/findPw")
	public @ResponseBody String findPw(Model model, CommandMap commandMap) throws Exception
	{
		//ajax json data
		String pwname = (String)commandMap.getMap().get("pwname");
		String pwid = (String)commandMap.getMap().get("pwid");
		String pwemail = (String)commandMap.getMap().get("pwemail");
		
		System.out.println(pwname);
		System.out.println(pwid);
		System.out.println(pwemail);
		
		System.out.println("비밀번호 찾기");
		//json data -> commandMap에 쿼리 컬럼명과 매핑하여 넣어줌
		commandMap.put("MEMBER_NAME_FIND", pwname);
		commandMap.put("MEMBER_ID_FIND", pwid);
		commandMap.put("MEMBER_EMAIL_FIND", pwemail);
		
		System.out.println(commandMap.getMap());
		
		//쿼리 반환 값 String 변수로 받기
		String findPw = loginService.findPw(commandMap.getMap());
		
		
		System.out.println("찾은비밀번호 " + findPw);
		
		return findPw;
	}

	
	
}
