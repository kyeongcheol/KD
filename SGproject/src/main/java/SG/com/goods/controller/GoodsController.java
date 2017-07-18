package SG.com.goods.controller;

import java.util.ArrayList;
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
import SG.com.goods.service.WishServiceImpl;

@Controller
public class GoodsController {
	@Resource
	GoodsServiceImpl goodsService;
	
	@Resource
	WishServiceImpl wishService;
	
	
	int currentPage=1;
	//�Խ��� ����Ʈ
	@RequestMapping(value = "/goodsList")
	public String goodsList(Model model,HttpSession session) throws Exception {
		
		//���� ó�� �ʿ�
		int MEMBER_NO = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
		
		if(MEMBER_NO ==0){
		
			List<Map<String, Object>> list = goodsService.goodsList();	
			model.addAttribute("goodsList",list);
		
		}else{
			
			List<Map<String,Object>> list = goodsService.wishGoodsList(MEMBER_NO);
			model.addAttribute("goodsList",list);
		}
				
		model.addAttribute("currentPage",currentPage);
		
		return "goodsList_tiles";
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
	public String CommentAdd(Model model,String str,String no,String rate,HttpSession session) throws Exception{
		System.out.println("�ı�"+str+","+no);
		int No = Integer.parseInt(no);
		int rateValue = Integer.parseInt(rate);
		
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("COMMENT_CONTENT", str);
		commentMap.put("COMMENT_GOODS_NO", No);
		
		//session �ʿ�
		String id = session.getAttribute("MEMBER_ID").toString();
		commentMap.put("COMMENT_ID",id );
		commentMap.put("COMMENT_RATE",rateValue);
		
		System.out.println(commentMap);
		
		goodsService.insertComment(commentMap);
		
	
		List<Map<String, Object>> commentList = goodsService.commentList(No);
		model.addAttribute("commentList",commentList);
		
		return "Goods/Comment/goodsComment";
		
	}
	
	//�ı� ����
	@RequestMapping(value="/CommentDel",method = RequestMethod.POST )
	public String CommentDel(Model model,CommandMap map,HttpSession session,int COMMENT_NO) throws Exception{
		
		System.out.println(map.getMap());
		int no = Integer.parseInt(map.getMap().get("COMMETN_NO").toString());
		goodsService.deleteComment(no);
		
		
		List<Map<String, Object>> commentList = goodsService.commentList(COMMENT_NO);
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
	   
	
	//ī�װ�ó��
	@RequestMapping(value="/category",method = RequestMethod.POST)
	public String category(Model model,CommandMap map,int currentPage,HttpSession session) throws Exception{
		
		int MEMBER_NO=Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
		map.put("MEMBER_NO", MEMBER_NO);
		System.out.println(map.getMap());
		System.out.println(currentPage);
		
		List<Map<String, Object>> list=goodsService.goodscategory(map.getMap());
		model.addAttribute("goodsList",list);
		model.addAttribute("currentPage",currentPage);
		System.out.println(list);
		return "Goods/goodsList-Body";
	
	}

	
	//�˻�
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String search(Model model,CommandMap map,HttpSession session,int currentPage) throws Exception{
		map.put("MEMBER_NO", session.getAttribute("MEMBER_NO"));
		System.out.println(map.getMap());
		
		List<Map<String,Object>> list = goodsService.searchGoods(map.getMap());
		model.addAttribute("goodsList",list);
		model.addAttribute("currentPage",currentPage);
		return "Goods/goodsList-Body";
		
	}
	
	//���̵� ��ٱ���
	//�߰�
		@SuppressWarnings({ "unchecked" })
		@RequestMapping(value = "/BasketAdd")
		public String BasketAdd(Model model,int goodsNo,HttpSession session,HttpServletRequest request) throws Exception {
			
			Map<String,Object> list = goodsService.selectOneGoodsforBasket(goodsNo);
			
			list.put("GOODS_AMOUNT", "1");
			
			if(request.getParameter("GOODS_AMOUNT")!=null){
				list.put("GOODS_AMOUNT", Integer.parseInt(request.getParameter("GOODS_AMOUNT").toString()));
			}
			//������ ������ ���� ����
			if(!list.get("GOODS_AMOUNT").equals("1")){
				int GOODS_AMOUNT =Integer.parseInt(request.getParameter("GOODS_AMOUNT").toString());
				int GOODS_PRICE = Integer.parseInt(request.getParameter("GOODS_PRICE").toString());
				
				list.put("GOODS_AMOUNT", GOODS_AMOUNT);
				list.put("GOODS_PRICE",GOODS_PRICE );
				
				 int sum =Integer.parseInt(list.get("GOODS_KCAL").toString()) * GOODS_AMOUNT;
			
				 list.put("GOODS_KCAL", sum);
			}
			
			//���Ǿ���
			if(session.getAttribute("basketList")==null){
				//���ǰ�
				
				
				List<Map<String,Object>> sessionList = new ArrayList<Map<String,Object>>();
				
				sessionList.add(list);
				
				session.setAttribute("basketList", sessionList);
				
				if(!session.getAttribute("MEMBER_NO").equals("0")){
					//DB�μ�Ʈ
					goodsService.basketInsert(list);
				}
				
				//jps���� �� Model����
				model.addAttribute("basketList",sessionList);
				System.out.println(sessionList);
			
			}else{
				
				List<Map<String,Object>> sessionList =(List<Map<String, Object>>) session.getAttribute("basketList");
				
				sessionList.add(list);
				session.setAttribute("basketList", sessionList);
				if(!session.getAttribute("MEMBER_NO").equals("0")){
					//DB�μ�Ʈ
					goodsService.basketInsert(list);

				}
				model.addAttribute("basketList",sessionList);
				System.out.println(sessionList);

			}
			
			System.out.println(list);
			return "Goods/Basket/goodsBasket";
		}
		
		
		//����
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/BasketDelete",method = RequestMethod.POST)
		public String BasketDelete(Model model,int goodsNo,HttpSession session) {
			List<Map<String,Object>> list = (List<Map<String, Object>>) session.getAttribute("basketList");
			list.remove(goodsNo);
			session.setAttribute("basketList", list);
			System.out.println("basketList���ǻ���");
			
			if(!session.getAttribute("MEMBER_NO").equals("0")){
				//DB����Ʈ
			}
			model.addAttribute("basketList",list);

			return "Goods/Basket/goodsBasket";
		}
		
	
	
	

}
