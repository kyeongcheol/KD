package SG.com.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishController {

	@RequestMapping(value = "/wishList")
	public String wishList(Model model) {
		return "mywish";
	}
}
