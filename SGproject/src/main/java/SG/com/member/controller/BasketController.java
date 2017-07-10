package SG.com.member.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BasketController {
	
	@RequestMapping(value = "/myBasketList")
	public String basketList(Model model) {
		return "mybasket";
	}
	
	
	
	
	
}
