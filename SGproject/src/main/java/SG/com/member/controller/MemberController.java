package SG.com.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@RequestMapping(value = "/mypage")
	public String mypage(Model model) {
		return "mypage";
	}
	
	@RequestMapping(value = "/memberInfo")
	public String memInfo(Model model) {
		return "memberInfo";
	}
	
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(Model model) {
		return "memUpdateForm";
	}
	@RequestMapping(value = "/memberUpdateAction")
	public String memUpdateAction(Model model) {
		return "memUpdateAction";
	}
	@RequestMapping(value = "/memberDeleteForm")
	public String memDeleteForm(Model model) {
		return "memDeleteForm";
	}
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(Model model) {
		return "redirect:main";
	}
	
	
	
	
	
}
