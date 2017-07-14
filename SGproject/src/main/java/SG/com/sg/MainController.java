package SG.com.sg;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SG.com.goods.service.GoodsServiceImpl;

@Controller
public class MainController {
	
	@Resource
	GoodsServiceImpl goodsService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model ,HttpSession session) throws Exception {
		List<Map<String,Object>> list = goodsService.goodsList();
		
		if(session.getAttribute("MEMBER_NO")==null){
		session.setAttribute("MEMBER_ID", "visitor");
		session.setAttribute("MEMBER_NO", 0);
		}
		
		model.addAttribute("goodsList",list);
		return "main_tiles";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminmain(Model model){
		return "admin_main";
	}
}
