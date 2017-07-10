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
	//게시판 리스트
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
	
	
	//상세보기
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
	
	
	//후기작성
	@RequestMapping(value="/goodsComment",method = RequestMethod.POST )
	public String CommentAdd(Model model,String str,String no) throws Exception{
		System.out.println("후기"+str+","+no);
		int No = Integer.parseInt(no);
		
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("COMMENT_CONTENT", str);
		commentMap.put("COMMENT_GOODS_NO", No);
		
		//session 필요
		commentMap.put("COMMENT_ID", "test");
		commentMap.put("COMMENT_RATE",No); //임시
		
		System.out.println(commentMap);
		
		goodsService.insertComment(commentMap);
		
	
		List<Map<String, Object>> commentList = goodsService.commentList(No);
		model.addAttribute("commentList",commentList);
		
		return "Goods/Comment/goodsComment";
		
	}
	
	
	
	//주문확인 
	@RequestMapping(value="/goodsOrder",method = RequestMethod.GET )
	public String goodsOrder(Model model,int GOODS_NO) throws Exception{
		Map<String,Object> list=goodsService.selectOneGoods(GOODS_NO);
		String member = "1"; //session 에서 아이디 or 아이디 넘버 받아올 부분
		
		System.out.println("주문"+list);
		
		return "goodsDetail";
		
	}
	
	
	
	//장바구니
	@RequestMapping(value="/addBasket",method = RequestMethod.POST)
	public String basket(Model model,HttpServletRequest request, HttpSession session, CommandMap map) throws Exception{
	      int member_no = Integer.parseInt(request.getParameter("BASKET_MEMBER_NO").toString());
	      
	      String tst  = request.getParameter("BASKET_GOODS_NO");
	      int basket_goods_no = Integer.parseInt(tst);
	      
	   /*   session = request.getSession(false);*/
	      
	      int basket_goods_amount = Integer.parseInt(request.getParameter("BASKET_GOODS_AMOUNT").toString());
	      String basket_topping_name="토핑없음";
	      
	      if(member_no != 0){
	    	  System.out.println("==============인설트==============");
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
