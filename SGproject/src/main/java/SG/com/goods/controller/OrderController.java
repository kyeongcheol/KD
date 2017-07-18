/*package SG.com.goods.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import SG.com.common.CommandMap;
import SG.com.goods.service.GoodsService;
import SG.com.goods.service.OrderService;
import SG.com.member.service.MemberService;

@Controller
public class OrderController {

	@Resource
	OrderService orderService;
	@Resource
	MemberService memberService;
	@Resource
	GoodsService goodsService;
	
	
	
	//주문 확인 페이지 이동
	   @RequestMapping(value="/goodsOrder", method=RequestMethod.GET)
	   public String goodsOrder(Model model,HttpServletRequest request, HttpSession session, CommandMap map) throws Exception{
	      
	      String tst=request.getParameter("GOODS_NO");
	      int goods_no = Integer.parseInt(tst);
	      session = request.getSession(false);
	      
	      if(session==null){
	         return "redirect:loginForm";
	      }
	      
	      String member_id=session.getAttribute("MEMBER_ID").toString();
	      map.put("MEMBER_ID", member_id);
	      Map<String,Object> orderGoods = goodsService.selectOneGoods(goods_no);
	      Map<String,Object> orderDeli= memberService.myinfoDetail(map.getMap());
	      model.addAttribute("orderGoods", orderGoods);
	      model.addAttribute("orderDeli", orderDeli);
	      
	      return "goodsOrder_tiles";
	   }
	   
	   
	   
	   //주문 완료 페이지 이동 
	   @RequestMapping(value="/goodsOrderSuccess", method=RequestMethod.POST)
	   public String orderSuccess(CommandMap map) throws Exception{
	      
		  System.out.println(map.getMap()); 
		  
	  
	      //주문테이블insert
	      orderService.orderInsert(map.getMap());
	      //배송테이블 insert
	      orderService.insertDeli(map.getMap());
	      
	      //마지막 주문 번호, 배송번호 가져오기
	      Map<String, Object> selectLastOrder = orderService.selectLastOrder();
	      int order_no=Integer.parseInt(selectLastOrder.get("ORDER_NO").toString());
	      map.put("ORDER_NO", order_no);
	      Map<String, Object> selectLastDeli = orderService.selectLastDeli();
	      int deli_no=Integer.parseInt(selectLastDeli.get("DELI_NO").toString());
	      map.put("DELI_NO", deli_no);
	      
	      //주문테이블 update
	      orderService.updateDeli(map.getMap());// update SG_ORDER set DELI_NO=#{DELI_NO} where orderno=#{orderno}
	      
	  
	      
	      return "goodsOrderSuccess_tiles";
	   }
}
*/