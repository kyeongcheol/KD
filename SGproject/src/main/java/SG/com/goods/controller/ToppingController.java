package SG.com.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import SG.com.goods.service.ToppingServiceImpl;

@Controller
public class ToppingController {
	
	@Resource
	ToppingServiceImpl toppingService;
	
	//DIYȭ��
	@RequestMapping(value = "/goodsDIY")
	public String goodsDIY(Model model) {
		
		List<Map<String,Object>> list = toppingService.toppingList();
		
		System.out.println(list);
		model.addAttribute("topping",list);
		
		return "goodsDIY_tiles";
	}
	
	
	
	
	
	//�߰�
	@SuppressWarnings({ "null", "unchecked" })
	@RequestMapping(value = "/ToppingAdd")
	public String ToppingAdd(Model model,int topping,HttpSession session) {
		
		Map<String,Object> list = toppingService.toppingOne(topping);
		
		
		if(session.getAttribute("toppingList")==null || session.getAttribute("toppingList").equals("")){
			//���ǰ�
			
			
			List<Map<String,Object>> sessionList = new ArrayList<Map<String,Object>>();
			
			sessionList.add(list);
			
			session.setAttribute("toppingList", sessionList);
			
			//jps���� �� Model����
			model.addAttribute("toppingList",sessionList);
			System.out.println(sessionList);
		
		}else{
			
			List<Map<String,Object>> sessionList =(List<Map<String, Object>>) session.getAttribute("toppingList");
			
			sessionList.add(list);
			session.setAttribute("toppingList", sessionList);
			
			model.addAttribute("toppingList",sessionList);
			System.out.println(sessionList);

		}
		
		return "Goods/Diy/goodsTopping";
	}
	
	
	//����
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ToppingDelete")
	public String ToppingDelete(Model model,int no,HttpSession session) {
		
		
		List<Map<String,Object>> list = (List<Map<String, Object>>) session.getAttribute("toppingList");
		list.remove(no);
		session.setAttribute("toppingList", list);
		System.out.println("���ǻ���");
		model.addAttribute("toppingList",list);
		
		return "Goods/Diy/goodsTopping";
	}
	
	
	
	//��ٱ��� �߰�
	@RequestMapping(value = "/BasketDiy")
	public String BasketDiy(Model model,HttpSession session,HttpServletRequest request) {
		/*basketList ���̵� ��ٱ��� �����̸�*/
		/*toppingList diy ���� �̸�*/
		/*list ��ٱ��Ͽ� �� DIY*/
		List<Map<String,Object>> toppingList = (List<Map<String, Object>>) session.getAttribute("toppingList");
		List<Map<String,Object>> basketList = (List<Map<String, Object>>) session.getAttribute("basketList");
		Map<String,Object> list = new HashMap<String,Object>();
		
		System.out.println("���� �׽�Ʈ");
		
		String GOODS_TOPPING="";

		for(int i =0;i<toppingList.size();i++){
		GOODS_TOPPING = GOODS_TOPPING +","+ toppingList.get(i).get("TOPPING_NAME").toString();
		}
		
		request.getParameter("form_price");
		request.getParameter("form_kcal");
		
		System.out.println(basketList);

		list.put("GOODS_NAME", "DIY������");
		list.put("GOODS_DETAIL", GOODS_TOPPING);
		list.put("GOODS_KCAL", 		request.getParameter("form_kcal").toString());
		list.put("GOODS_PRICE", request.getParameter("form_price").toString());
		list.put("GOODS_THUMBNAIL", "SG_diy.jpg");
		list.put("GOODS_AMOUNT", 1);
		
		basketList.add(list);
		
		System.out.println(basketList);
		session.setAttribute("basketList", basketList);
		session.removeAttribute("toppingList");
		
		model.addAttribute("basketList",basketList);
		return "redirect:goodsDIY";
		
	}
		
	

}
