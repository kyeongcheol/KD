package SG.com.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import SG.com.common.CommandMap;
import SG.com.common.Paging;
import SG.com.member.service.LoginService;
import SG.com.member.service.MemberService;
import SG.com.member.service.PointService;

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
	
	private int gcurrentPage = 1;
	
	// 페이징
    private int currentPage = 1;
    private int totalCount;
    private int blockCount = 5;
    private int blockPage = 5;
    private String pagingHtml;
    private Paging paging;
    
    private String isSearch;
    private int searchNum;
    
    private int lastCount;
	
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
	public String memInfo(HttpSession session, Model model, CommandMap commandMap) throws Exception 
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
	public String memUpdateForm(HttpSession session, Model model, CommandMap commandMap) 
			throws Exception
	{ 
	   System.out.println("진입");
			
	   String mem_id = session.getAttribute("MEMBER_ID").toString(); //MEMBER_ID 값 설정
			
	   commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID 커맨드 map에 put
	
	   //회원 ID에 해당하는 회원정보 myinfo 맵으로 받아오기
	   Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
	   //아이디 출력
	   System.out.println(myInfo.get("MEMBER_ID"));
	   model.addAttribute("myInfo", myInfo); //myinfo 영역에 저장
			  
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
	public String memUpdateAction(HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		System.out.println("진입");
		//split 된 이메일을 다시 합침
		String MEMBER_EMAIL = commandMap.getMap().get(("MEMBER_EMAIL1"))
				+"@"+ commandMap.getMap().get(("MEMBER_EMAIL2"));
		
		commandMap.getMap().put("MEMBER_EMAIL", MEMBER_EMAIL); //MEMBER_EMAIL map에 삽입
		
		System.out.println(commandMap.getMap()); //map 확인
		
		//updatemember map 선언
		Map<String, Object> updatemember = new HashMap<String, Object>();
	    updatemember = commandMap.getMap(); //update 할 정보들 updatemember에 넣음
	    memberService.updateMyinfo(updatemember); //update 쿼리 실행
	    
	    //memberMap map 선언
	    Map<String, Object> memberMap = new HashMap<String, Object>();
	    memberMap = memberService.myinfoDetail(commandMap.getMap()); //바뀐 회원정보 불러옴
	    model.addAttribute("memberInfo", memberMap); //model에 저장
	    
	    return "Member/mem_update_Form";
	}

	
	//회원 정보 삭제 처리
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
	   System.out.println("진입");
	   String mem_id = commandMap.getMap().get("MEMBER_ID").toString(); //ajax jason data가 map에 저장
	   
	   commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID commandMap에 저장
	   commandMap.getMap().put("MEMBER_NO", session.getAttribute("MEMBER_NO")); //MEMBER_NO commandMap에 저장
	   System.out.println(commandMap.getMap());
	
	   memberService.deleteMember(commandMap.getMap()); //update onoff = 1(회원탈퇴) 쿼리 실행
	   
	   Map<String, Object> member = new HashMap<String, Object>(); //member map 선언
	   member = memberService.myinfoDetail(commandMap.getMap()); //회원 정보를 map에 담음
	   
	   int onoff = Integer.parseInt(member.get("MEMBER_ONOFF").toString());
	   
	   if(onoff == 1)
	   {
		  model.addAttribute("onoff", onoff);
	      session.invalidate(); //세션 종료
	   }
	       
	   return "Member/member_Info";
	 }
	
	//나의 포인트 내역
	@RequestMapping(value = "/myPoint")
	public String mypoint(HttpSession session, Model model, CommandMap commandMap, HttpServletRequest request) throws Exception 
	{
	      System.out.println("진입");
	      System.out.println("나의 포인트 내역");
	      String mem_no = session.getAttribute("MEMBER_NO").toString();
	      
	      commandMap.getMap().put("MEMBER_NO", mem_no); //회원 번호 commandMap에 넣기
	      
	      if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
					|| request.getParameter("currentPage").equals("0")) 
	      {
			 currentPage = 1;
		  } 
	      else 
	      {
			 currentPage = Integer.parseInt(request.getParameter("currentPage"));
		  }
	      
	      Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
	      List<Map<String, Object>> pointList = pointService.myPointList(commandMap.getMap());
	      System.out.println(pointList);
	      
	      totalCount = pointList.size();
	      System.out.println(pointList.size());

		  paging = new Paging(currentPage, totalCount, blockCount, blockPage, "myPoint");
		  pagingHtml = paging.getPagingHtml().toString();

		  lastCount = totalCount;
		  
		  if (paging.getEndCount() < totalCount)
				{lastCount = paging.getEndCount() + 1; }
		  
		  pointList = pointList.subList(paging.getStartCount(), lastCount);
		  
		  model.addAttribute("totalCount", totalCount);
		  model.addAttribute("pagingHtml", pagingHtml);
		  model.addAttribute("currentPage", currentPage);
	      model.addAttribute("sumPoint", sumPoint.get("SUM"));
	      model.addAttribute("pointList", pointList);
	      
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
	public String wishList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request) 
			throws Exception
	{
		
		String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
		System.out.println(member_id);
		commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
		
		List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //WISH 테이블에 있는 내용을 꺼내옴
		model.addAttribute("gcurrentPage", gcurrentPage); //상품 리스트 페이지
		
		model.addAttribute("wishlist", wishlist);
		
        return "Member/myWish";
	}
	
	//나의 위시리스트 삭제
	@RequestMapping(value = "/myWishList/wish_del")
	public String wishDelete(Model model, CommandMap commandMap, HttpSession session) throws Exception
	{
		System.out.println("위시리스트 삭제");
		int wish_no = Integer.parseInt(commandMap.getMap().get("WISH_NO").toString()); 
		int wish_mem_no = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
		commandMap.getMap().put("WISH_NO", wish_no);
		commandMap.getMap().put("WISH_MEMBER_NO", wish_mem_no);
		
		System.out.println(commandMap.getMap());
		memberService.deleteMyWish(commandMap.getMap()); //위시리스트 삭제
		
		String member_id = session.getAttribute("MEMBER_ID").toString();
		commandMap.getMap().put("MEMBER_ID", member_id);
		List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //위시리스트 꺼내옴
		model.addAttribute("wishlist", wishlist);
		
		return "Member/myWish";
	}
	
	//장바구니 리스트
    @RequestMapping(value = "/mybasket")
    public String basketList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request)
    throws Exception
	{
    	System.out.println("나의 장바구니 내역");
    	String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
    	System.out.println(member_id);
		commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
		
		List<Map<String,Object>> basketlist = memberService.myBasketList(commandMap.getMap()); //WISH 테이블에 있는 내용을 꺼내옴
		model.addAttribute("gcurrentPage", gcurrentPage); //상품 리스트 페이지
		model.addAttribute("basketlist", basketlist);
		
		return "Member/myBasket";
	}
    
    //나의 장바구니 리스트 삭제
    
    @RequestMapping(value = "/myBasketList/Delete")
    public String basketDelete(Model model, CommandMap commandMap, HttpSession session, 
    		@RequestParam(value="BASKET_NO", required=true) List<Integer> basketno) throws Exception
	{
    	System.out.println("장바구니 삭제");
    	System.out.println(basketno.size());
    	
    	for(int i=0; i<basketno.size(); i++)
    	{
    		System.out.println(basketno.get(i));
    		commandMap.getMap().put("BASKET_NO", basketno.get(i));
    		System.out.println(commandMap.getMap());
    		memberService.deleteMyBasket(commandMap.getMap());
    	}
    	
    	String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
    	System.out.println(member_id);
		commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
		
		List<Map<String,Object>> basketlist = memberService.myBasketList(commandMap.getMap()); //WISH 테이블에 있는 내용을 꺼내옴
		model.addAttribute("basketlist", basketlist);
		
    	return "Member/myBasket";
	}
    
    
		
}
