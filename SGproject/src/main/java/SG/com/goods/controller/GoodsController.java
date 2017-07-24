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
	//게시판 리스트
	@RequestMapping(value = "/goodsList")
	public String goodsList(Model model,HttpSession session) throws Exception {
		
		//세션 처리 필요
		int MEMBER_NO = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
		
		if(MEMBER_NO ==0){
		
			List<Map<String, Object>> list = goodsService.goodsList();	
			model.addAttribute("goodsList",list);
		
		}else{
				//사이드 장바구니 처리
				List<Map<String,Object>> sessionList = new ArrayList<Map<String,Object>>();
		        sessionList = goodsService.BascketMemberSelect( Integer.parseInt(session.getAttribute("MEMBER_NO").toString()));
				session.setAttribute("basketList", sessionList);
				System.out.println("로그인 세션 생성=============="+sessionList);
			
			
			List<Map<String,Object>> list = goodsService.wishGoodsList(MEMBER_NO);
			model.addAttribute("goodsList",list);
		}
				
		model.addAttribute("currentPage",currentPage);
		
		return "goodsList_tiles";
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
	public String CommentAdd(Model model,String str,String no,String rate,HttpSession session) throws Exception{
		System.out.println("후기"+str+","+no);
		int No = Integer.parseInt(no);
		int rateValue = Integer.parseInt(rate);
		
		Map<String,Object> commentMap = new HashMap<String,Object>();
		commentMap.put("COMMENT_CONTENT", str);
		commentMap.put("COMMENT_GOODS_NO", No);
		
		//session 필요
		String id = session.getAttribute("MEMBER_ID").toString();
		commentMap.put("COMMENT_ID",id );
		commentMap.put("COMMENT_RATE",rateValue);
		
		System.out.println(commentMap);
		
		goodsService.insertComment(commentMap);
		
	
		List<Map<String, Object>> commentList = goodsService.commentList(No);
		model.addAttribute("commentList",commentList);
		
		return "Goods/Comment/goodsComment";
		
	}
	
	//후기 삭제
	@RequestMapping(value="/CommentDel",method = RequestMethod.POST )
	public String CommentDel(Model model,CommandMap map,HttpSession session,int COMMENT_NO) throws Exception{
		
		System.out.println(map.getMap());
		int no = Integer.parseInt(map.getMap().get("COMMETN_NO").toString());
		goodsService.deleteComment(no);
		
		
		List<Map<String, Object>> commentList = goodsService.commentList(COMMENT_NO);
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
	
	//카테고리처리
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

	
	//검색
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String search(Model model,CommandMap map,HttpSession session,int currentPage) throws Exception{
		map.put("MEMBER_NO", session.getAttribute("MEMBER_NO"));
		System.out.println(map.getMap());
		
		List<Map<String,Object>> list = goodsService.searchGoods(map.getMap());
		model.addAttribute("goodsList",list);
		model.addAttribute("currentPage",currentPage);
		return "Goods/goodsList-Body";
		
	}
	
	//사이드 장바구니
	//추가
			@SuppressWarnings({ "unchecked" })
			@RequestMapping(value = "/BasketAdd")
			public String BasketAdd(Model model,int goodsNo,HttpSession session,HttpServletRequest request) throws Exception {
				
				Map<String,Object> list = goodsService.selectOneGoodsforBasket(goodsNo);
				list.put("MEMBER_NO", Integer.parseInt(session.getAttribute("MEMBER_NO").toString()));
				list.put("GOODS_AMOUNT", 1);
				list.put("TOPPING_NAME", "토핑없음");

				if(request.getParameter("GOODS_AMOUNT")!=null){
					System.out.println("수량검사");
					list.put("GOODS_AMOUNT", Integer.parseInt(request.getParameter("GOODS_AMOUNT").toString()));
				}
				
				
				//디테일 페이지 수량 조절
				if(Integer.parseInt(list.get("GOODS_AMOUNT").toString()) != 1){
					System.out.println("수량조절");

					int GOODS_AMOUNT =Integer.parseInt(request.getParameter("GOODS_AMOUNT").toString());
					int GOODS_PRICE = Integer.parseInt(request.getParameter("GOODS_PRICE").toString());
					
					list.put("GOODS_AMOUNT", GOODS_AMOUNT);
					list.put("GOODS_PRICE",GOODS_PRICE );
					
					 int sum =Integer.parseInt(list.get("GOODS_KCAL").toString()) * GOODS_AMOUNT;
					 list.put("GOODS_KCAL", sum);
				}
				
				//세션없음
				if(session.getAttribute("basketList")==null){
					//세션값 생성
					System.out.println("세션 없음");

					
					
					List<Map<String,Object>> sessionList = new ArrayList<Map<String,Object>>();
					
					
					
					if(Integer.parseInt(session.getAttribute("MEMBER_NO").toString()) !=0){
						//DB인설트
				         goodsService.basketInsert(list);
				         int no = goodsService.basketNo(Integer.parseInt(session.getAttribute("MEMBER_NO").toString()));
				         list.put("BASKET_NO", no);
					}
					
					//jps에서 쓸 Model설정 & session설정
					sessionList.add(list);
					session.setAttribute("basketList", sessionList);
				
				}else{
					//세션 존재함
					System.out.println("세션 존재");

					List<Map<String,Object>> sessionList =(List<Map<String, Object>>) session.getAttribute("basketList");
					
					sessionList.add(list);

					if(Integer.parseInt(session.getAttribute("MEMBER_NO").toString()) !=0){
						//DB인설트
						System.out.println("DB진입");
				         goodsService.basketInsert(list);
				         int no = goodsService.basketNo(Integer.parseInt(session.getAttribute("MEMBER_NO").toString()));
				         list.put("BASKET_NO", no);
					}
					
					System.out.println(sessionList);
					session.setAttribute("basketList", sessionList);
					System.out.println("세션 검사====================================================="+sessionList);

				}
				

				return "Goods/Basket/goodsBasket";
			}
			
			
			//제거
			@SuppressWarnings("unchecked")
			@RequestMapping(value = "/BasketDelete",method = RequestMethod.POST)
			public String BasketDelete(Model model,int goodsNo, int BASKET_NO,HttpSession session) throws Exception {
				System.out.println("삭제처리시작"+goodsNo+"/"+BASKET_NO);
				
				List<Map<String,Object>> list = (List<Map<String, Object>>) session.getAttribute("basketList");
				System.out.println("basketList세션삭제");
				list.remove(goodsNo);
				
				if(Integer.parseInt(session.getAttribute("MEMBER_NO").toString())!=0){
					System.out.println("basketListDB삭제");

					goodsService.basketDelete(BASKET_NO);

				}
				
				session.setAttribute("basketList", list);

				return "Goods/Basket/goodsBasket";
			}
			
		
		
	
	

}
