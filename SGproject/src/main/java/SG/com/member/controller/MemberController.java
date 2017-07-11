package SG.com.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//마이페이지 컨트롤러 
@Controller
public class MemberController 
{
	//마이페이지 
	@RequestMapping(value = "/mypage")
	public String mypage(Model model) 
	{
		return "mypage";
	}
	
	//회원 정보 조회
	@RequestMapping(value = "/memberInfo")
	public String memInfo(Model model) 
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
