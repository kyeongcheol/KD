package SG.com.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import SG.com.common.CommandMap;
import SG.com.member.service.PointService;
import SG.com.member.service.MemberService;
import SG.com.member.service.LoginService;

//마이페이지 컨트롤러 
@Controller
public class MemberController 
{
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="pointService")
	private PointService pointService;
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	//마이페이지 
	@RequestMapping(value = "/mypage")
	public String mypage(HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		String mem_num = session.getAttribute("MEMBER_NO").toString(); //세션에서 회원번호 가져오기
		String mem_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 회원아이디 가져오기
		
		commandMap.getMap().put("MEMBER_NO", mem_num); //회원번호 commandMap에 넣기
		commandMap.getMap().put("MEMBER_ID", mem_id); //회원 아이디 commandMap에 넣기
		
		Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
		Map<String, Object> mem_grade = loginService.selectId(commandMap.getMap()); 
		
		model.addAttribute("sumPoint", sumPoint.get("SUM"));
		model.addAttribute("memberGrade", mem_grade.get("MEMBER_GRADE"));
		
		return "mypage";
	}
	
	//회원 정보 조회
	@RequestMapping(value = "/memberInfo")
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{

		System.out.println("회원 내역");
        String mem_id = session.getAttribute("MEMBER_ID").toString();
		
		commandMap.getMap().put("MEMBER_ID", mem_id);
		
		Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
		System.out.println(myInfo.get("MEMBER_ID"));
		model.addAttribute("myInfo", myInfo);
		
		return "Member/member_Info";
	}
	
	//회원 정보 수정 폼
	@RequestMapping(value = "/memberUpdateForm")
	public String memUpdateForm(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) 
			throws Exception
	{ 
	   System.out.println("진입");
			
	   String mem_id = session.getAttribute("MEMBER_ID").toString();
			
	   commandMap.getMap().put("MEMBER_ID", mem_id);
			
	   Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
	   System.out.println(myInfo.get("MEMBER_ID"));
	   model.addAttribute("myInfo", myInfo);
			  
	   String email = (String)myInfo.get("MEMBER_EMAIL");
			
	   System.out.println(email);
			
	   //입력한 email 주소를 @를 기준으로 쪼갬
	   String [] split_email = email.split("@");
			
	   //세션 영역 저장(이메일) : ex) ykc90831@gmail.com
	   session.setAttribute("email1", split_email[0].toString()); // ykc90831
	   session.setAttribute("email2", split_email[1].toString()); // gmail.com

	   return "Member/mem_update_Form";
	}
	
	//회원 정보 수정 처리
	@RequestMapping(value = "/memberUpdateAction")
	public String memUpdateAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		System.out.println("진입");
		String MEMBER_EMAIL = commandMap.getMap().get(("MEMBER_EMAIL1"))
				+"@"+ commandMap.getMap().get(("MEMBER_EMAIL2"));
		
		commandMap.getMap().put("MEMBER_EMAIL", MEMBER_EMAIL);
		
		System.out.println(MEMBER_EMAIL);
		System.out.println(commandMap.getMap());
		
	    
	    memberService.updateMyinfo(commandMap.getMap());
	    
	    Map<String, Object> memberMap = new HashMap<String, Object>();
	    
	    memberMap = memberService.myinfoDetail(commandMap.getMap());
	    model.addAttribute("memberInfo", memberMap);
	    
	    return "Member/mem_update_Form";
	}
	
	//회원 정보 삭제 처리
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
	   String mem_num = session.getAttribute("MEMBER_NO").toString();
	      
	   commandMap.getMap().put("MEMBER_NO", mem_num);
	      
	   memberService.deleteMember(commandMap.getMap());
	      
	   session.invalidate();
	       
	   return "redirect:main";
	 }
	
	//나의 포인트 내역
	@RequestMapping(value = "/myPoint")
	public String mypoint(Model model) 
	{
		System.out.println("나의 포인트 내역");
		return "Member/myPoint";
	}
	
	//나의 주문 내역
	@RequestMapping(value="/orderInfo")
	public String orderInfo(Model model)
	{
		System.out.println("나의 주문 내역");
		return "Member/myOrder";
	}
	
	//나의 주문 상세보기
	@RequestMapping(value="/orderInfoView")
	public String orderInfoView(Model model)
	{
		return "orderInfoView";
	}
	
	//주문상품 취소
	@RequestMapping(value="/orderInfo/order_del")
	public String orderDel(Model model)
	{
		return "Member/myOrder";
	}
	
	//주문상품 취소 처리
	@RequestMapping(value="/ordercancel")
	public String orderCancel(Model model)
	{
		return "redirect:/orderInfo";
	}
	
	//나의 위시리스트
	@RequestMapping(value = "/wishList")
	public String wishList(Model model)
	{
		return "Member/myWish";
	}
	
	//나의 위시리스트 삭제
	@RequestMapping(value = "/myWishList/wish_del")
	public String wishDelete(Model model)
	{
		return "redirect:/wishList";
	}
	
	//장바구니 리스트
    @RequestMapping(value = "/mybasket")
    public String basketList(Model model) 
	{
    	System.out.println("나의 장바구니 내역");
    	
		return "Member/myBasket";
	}
    
    @RequestMapping(value = "/myBasketList/Delete")
    public String basketDelete(Model model) 
	{
    	return "redirect:/mybasket";
	}
    
    
		
}
