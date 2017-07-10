package SG.com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberDiyController {
	
	@RequestMapping(value = "/memberDiyList", method = RequestMethod.GET)
	public String memberNoticeList(Model model) {
		return "memberDiyList";
	}

}
