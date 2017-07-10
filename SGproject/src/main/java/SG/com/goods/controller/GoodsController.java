package SG.com.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import SG.com.common.CommandMap;
import SG.com.goods.service.GoodsServiceImpl;

@Controller
public class GoodsController {
	@Resource
	GoodsServiceImpl goodsService;
	
	int currentPage=1;
	//�Խ��� ����Ʈ
	@RequestMapping(value = "/goodsList")
	public String goodsList(Model model) throws Exception {
		List<Map<String, Object>> list = goodsService.goodsList();
		model.addAttribute("goodsList",list);
		model.addAttribute("currentPage",currentPage);
		
		return "goodsList_tiles";
	}
	
	@RequestMapping(value = "/goodsDIY")
	public String goodsDIY(Model model) {
		return "goodsDIY_tiles";
	}
	
	
	//�󼼺���
	@RequestMapping(value="/goodsDetail",method = RequestMethod.GET )
	public String goodsDetail(Model model,int goodsNo,int currentPage) throws Exception{
		Map<String,Object> list = goodsService.selectOneGoods(goodsNo);
		int point =Integer.parseInt(list.get("GOODS_PRICE").toString());
		String image = goodsService.imageList(goodsNo);
		List<Map<String, Object>> commentList = goodsService.commentList(goodsNo);
		
		System.out.println(commentList);
		model.addAttribute("image",image);
		model.addAttribute("goodsDetail",list);
		model.addAttribute("point", point/100);
		model.addAttribute("commentList",commentList);
		System.out.println(commentList);
		return "goodsDetail_tiles";
	}
	
	
	//�ı��ۼ�
	@RequestMapping(value="/goodsComment",method = RequestMethod.POST )
	public String CommentAdd(Model model,String str,String no) throws Exception{
		System.out.println("�ı�"+str+","+no);
		int No = Integer.parseInt(no);
		
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("COMMENT_CONTENT", str);
		commentMap.put("COMMENT_GOODS_NO", No);
		
		//session �ʿ�
		commentMap.put("COMMENT_ID", "test");
		commentMap.put("COMMENT_RATE",No); //�ӽ�
		
		System.out.println(commentMap);
		
		goodsService.insertComment(commentMap);
		
	
		List<Map<String, Object>> commentList = goodsService.commentList(No);
		model.addAttribute("commentList",commentList);
		
		return "Goods/Comment/goodsComment";
		
	}
	
	
	
	//�ֹ�Ȯ�� 
	@RequestMapping(value="/goodsOrder",method = RequestMethod.GET )
	public String goodsOrder(Model model,int GOODS_NO) throws Exception{
		Map<String,Object> list=goodsService.selectOneGoods(GOODS_NO);
		String member = "1"; //session ���� ���̵� or ���̵� �ѹ� �޾ƿ� �κ�
		
		System.out.println("�ֹ�"+list);
		
		return "goodsDetail";
		
	}
	
	
	
	//��ٱ���
	@RequestMapping(value="/addBasket",method = RequestMethod.POST)
	public String basket(Model model,HttpServletRequest request, HttpSession session, CommandMap map) throws Exception{
	      int member_no = Integer.parseInt(request.getParameter("BASKET_MEMBER_NO").toString());
	      
	      String tst  = request.getParameter("BASKET_GOODS_NO");
	      int basket_goods_no = Integer.parseInt(tst);
	      
	   /*   session = request.getSession(false);*/
	      
	      int basket_goods_amount = Integer.parseInt(request.getParameter("BASKET_GOODS_AMOUNT").toString());
	      String basket_topping_name="���ξ���";
	      
	      if(member_no != 0){
	    	  System.out.println("==============�μ�Ʈ==============");
	         map.put("BASKET_GOODS_NO", basket_goods_no);
	         map.put("BASKET_MEMBER_NO", member_no);
	         map.put("BASKET_GOODS_NAME", goodsService.selectOneGoods(basket_goods_no).get("GOODS_NAME"));
	         map.put("BASKET_GOODS_AMOUNT", basket_goods_amount);
	         map.put("BASKET_TOPPING_NAME", basket_topping_name);
		      System.out.println(map.getMap());

         goodsService.basketInsert(map.getMap());
         
	      }else{
	         return "redirect:loginForm";
	      	}
	      
	      return "Goods/goodsList";
	   }
	   
	
	
	
	
	
	

}
