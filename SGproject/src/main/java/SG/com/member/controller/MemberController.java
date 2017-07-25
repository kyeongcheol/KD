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
import org.springframework.web.bind.annotation.ResponseBody;

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
   
    // 포인트 내역 페이징
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
      
      /*String page = pagingHtml(commandMap, 1);
      model.addAttribute("page", page);*/
      model.addAttribute("sumPoint", sumPoint.get("SUM"));
      model.addAttribute("memberGrade", mem_grade.get("MEMBER_GRADE"));
      
      return "mypage";
   }
   
   //회원 정보 조회
   @RequestMapping(value = "/memberInfo")
   public String memInfo(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {

      System.out.println("===========나의 정보 조회 컨트롤러 진입==========");
        String mem_id = session.getAttribute("MEMBER_ID").toString();
      
      commandMap.getMap().put("MEMBER_ID", mem_id);
      
      Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
      System.out.println("===========나의 아이디=========== : " + myInfo.get("MEMBER_ID"));
      model.addAttribute("myInfo", myInfo);
      
      return "Member/member_Info";
   }
   
   //회원 정보 수정 폼
   @RequestMapping(value = "/memberUpdateForm")
   public String memUpdateForm(HttpSession session, Model model, CommandMap commandMap) 
         throws Exception
   { 
      System.out.println("===========회원 정보 수정 폼 컨트롤러 진입===========");
         
      String mem_id = session.getAttribute("MEMBER_ID").toString(); //MEMBER_ID 값 설정
         
      commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID 커맨드 map에 put
   
      //회원 ID에 해당하는 회원정보 myinfo 맵으로 받아오기
      Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
      //아이디 출력
      System.out.println("===========나의 아이디=========== : " + myInfo.get("MEMBER_ID"));
      model.addAttribute("myInfo", myInfo); //myinfo 영역에 저장
           
      String email = (String)myInfo.get("MEMBER_EMAIL");
         
      System.out.println("===========나의 이메일=========== : " + email); 
         
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
      System.out.println("===========회원 정보 수정 처리(Ajax) 컨트롤러 진입===========");
      //split 된 이메일을 다시 합침
      String MEMBER_EMAIL = commandMap.getMap().get(("MEMBER_EMAIL1"))
            +"@"+ commandMap.getMap().get(("MEMBER_EMAIL2"));
      
      commandMap.getMap().put("MEMBER_EMAIL", MEMBER_EMAIL); //MEMBER_EMAIL map에 삽입
      
      //map 확인
      System.out.println("===========회원 정보 수정 처리(Ajax)에 필요할 commandMap 출력==========="); 
      System.out.println(commandMap.getMap());
      
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
      System.out.println("===========회원 정보 삭제 처리(Ajax) 컨트롤러 진입===========");
      String mem_id = commandMap.getMap().get("MEMBER_ID").toString(); //ajax jason data가 map에 저장
      
      commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID commandMap에 저장
      commandMap.getMap().put("MEMBER_NO", session.getAttribute("MEMBER_NO")); //MEMBER_NO commandMap에 저장
      System.out.println(commandMap.getMap());
   
      //map 확인
      System.out.println("===========회원 정보 삭제 처리(Ajax)에 필요할 commandMap 출력===========");
      memberService.deleteMember(commandMap.getMap()); //update onoff = 1(회원탈퇴) 쿼리 실행
      
      Map<String, Object> member = new HashMap<String, Object>(); //member map 선언
      member = memberService.myinfoDetail(commandMap.getMap()); //회원 정보를 map에 담음
      
      int onoff = Integer.parseInt(member.get("MEMBER_ONOFF").toString()); //회원 탈퇴 여부 : on/off
      
      //회원이 탈퇴 되면
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
         
         System.out.println("===========나의 포인트 내역 조회 컨트롤러 진입===========");
         String mem_no = session.getAttribute("MEMBER_NO").toString();
         
         commandMap.getMap().put("MEMBER_NO", mem_no); //회원 번호 commandMap에 넣기
         
         //페이징 로직 시작
         if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
               || request.getParameter("currentPage").equals("0")) 
         {
            currentPage = 1; //현재 페이지 1로 설정
         } 
         else 
         {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
         }
         
         Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap()); //회원의 총 포인트 합계
         List<Map<String, Object>> pointList = pointService.myPointList(commandMap.getMap()); //나의 포인트 내역
         
         System.out.println("===========나의 포인트 내역 조회(DB)===========");
         System.out.println(pointList);
         
         totalCount = pointList.size();
         System.out.println(pointList.size());

        paging = new Paging(currentPage, totalCount, blockCount, blockPage, "myPoint");
        pagingHtml = paging.getPagingHtml().toString();

        lastCount = totalCount;
        
        if (paging.getEndCount() < totalCount)
            {lastCount = paging.getEndCount() + 1; }
        
        pointList = pointList.subList(paging.getStartCount(), lastCount);
        //페이징 로직 끝
        
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pagingHtml", pagingHtml);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sumPoint", sumPoint.get("SUM"));
        model.addAttribute("pointList", pointList);
         
         return "Member/myPoint";
      }
   
      //나의 주문 내역
      @RequestMapping(value="/orderInfo")
      public String orderInfo(HttpSession session, Model model, CommandMap commandMap) throws Exception
      {
    	   System.out.println("===========나의 주문 내역 조회 컨트롤러 진입===========");
           String mem_id = session.getAttribute("MEMBER_ID").toString();
           session.setAttribute("totalcount", 3); //주문내역 세션 설정
           String pagingHtml; //페이징 method를 담을 String 변수
           
           commandMap.getMap().put("MEMBER_ID", mem_id); //회원 번호 commandMap에 넣기
           
           //페이징 로직 시작
           if(commandMap.get("PAGE").toString().equals(""))
           {
               pagingHtml = pagingHtml(commandMap, 1, session);
           }
           else
           {
              int page = Integer.parseInt(commandMap.get("PAGE").toString());
              pagingHtml = pagingHtml(commandMap, page, session);
           }
           
           List<Map<String, Object>> myOrderList = memberService.myOrderList(commandMap.getMap()); //나의 주문내역

           System.out.println("===========나의 주문 내역 조회(DB)===========");
           System.out.println(myOrderList);
            
           model.addAttribute("gcurrentPage", gcurrentPage); //상품 currentpage
           model.addAttribute("myOrderList", myOrderList); //주문내역
           model.addAttribute("pagingHtml", pagingHtml); //주문 내역 페이징
            
           return "Member/myOrder";
      }
      
      //나의 주문 상세보기
      @RequestMapping(value="/orderInfoView")
      public String orderInfoView(Model model, CommandMap commandMap, HttpSession session)
      throws Exception
      {
    	 System.out.println("===========나의 주문 상세보기 컨트롤러 진입==========="); 
    	 commandMap.getMap().put("ORDER_DELI_NO", commandMap.getMap().get("DELI_NO")); 
    	 commandMap.getMap().put("MEMBER_ID", session.getAttribute("MEMBER_ID"));
    	 
    	 System.out.println("===========나의 주문 상세보기에 필요한 commandMap==========="); 
    	 System.out.println(commandMap.getMap());
    	 List<Map<String, Object>> myOrderDetail = memberService.myOrderDetail(commandMap.getMap());
    	 
    	 int orderUsePoint=0;
         for(int i=0;i<myOrderDetail.size();i++)
         {
              
             orderUsePoint =+ pointService.orderUsePoint( myOrderDetail.get(i));
         }
         
    	 model.addAttribute("myOrderDetail", myOrderDetail);
    	 System.out.println("orderUsePoint");
         model.addAttribute("orderUsePoint", orderUsePoint);

         return "Member/myOrder_View";
      }
      
    //나의 주문 수정(배송정보)
    @RequestMapping(value="/orderUpdateAction")
    public String orderUpdateAction(Model model, CommandMap commandMap, HttpSession session) throws Exception
    {
         System.out.println("배송정보 수정 집입");
       
         commandMap.getMap().put("ORDER_DELI_NO", commandMap.getMap().get("ORDER_DELI_NO")); 
         
         System.out.println(commandMap.getMap());
  
         memberService.myDeliUpdate(commandMap.getMap()); //update 쿼리 실행
         //memberMap map 선언
         
         return "Member/myOrder_View" ;
         
      }
      
      //주문상품 취소
      @RequestMapping(value="/orderInfo/order_del")
       public String orderDel(Model model, CommandMap commandMap, HttpSession session) throws Exception
      {
          System.out.println("===========주문 취소(Ajax) 컨트롤러 진입===========");
          String mem_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 ID값 받아오기
          
          //ajax json data : 배송번호, 주문 상태, 주문번호
          String deli_no = (String) commandMap.getMap().get("DELI_NO"); //주문 상품의 배송번호
          int order_state = Integer.parseInt(commandMap.getMap().get("ORDER_STATE").toString()); //주문 상품의 주문상태 
          String order_no =(String)commandMap.getMap().get("ORDER_NO"); //주문 상품의 주문번호
          String pagingHtml; //삭제된 후 주문 내역 페이징에 필요할 String 변수
          
          //받아온 데이터들 commandMap에 삽입
          commandMap.getMap().put("MEMBER_ID", mem_id);
          commandMap.getMap().put("DELI_NO", deli_no);
          commandMap.getMap().put("ORDER_DELI_NO", deli_no);
          
          commandMap.getMap().put("ORDER_STATE", order_state);
          commandMap.getMap().put("ORDER_NO", order_no);
          
         
          System.out.println("===========주문 취소(Ajax)에 필요한 commandMap 출력==========="); 
          System.out.println(commandMap.getMap());
         
          int cnt = memberService.orderdelicnt(commandMap.getMap()); //배송번호가 같은 주문 정보를 가져옴
          System.out.println(cnt);
        
          if(order_state == 0) //주문상태가 입금 전일때
          {
            if(cnt > 1) //배송번호가 하나 이상일 경우
            {
            	System.out.println("===========입금 전 부분 취소함 : 배송번호 1개 이상일 경우===========");
            	System.out.println(commandMap.getMap());
            	memberService.orderDelete(commandMap.getMap()); //주문 테이블 삭제
            	memberService.amountUpdate(commandMap.getMap());
            }
            else //배송번호가 하나일 경우
            {
            	System.out.println("===========입금 전 부분 취소함 : 배송번호 1개 일 경우===========");
            	System.out.println(commandMap.getMap());
            	memberService.orderDelete(commandMap.getMap()); //주문 테이블 삭제
            	memberService.deliDelete(commandMap.getMap()); //배송 테이블 삭제
            	memberService.amountUpdate(commandMap.getMap());
            }
          }  
         
          else if(order_state == 1) //주문상태가 배송준비중일 때
          {
             System.out.println("===========배송준비중 주문 취소함=========== ");
             
             int trade_no = memberService.tradeInfo(commandMap.getMap()); //결제 번호 받아오기
             commandMap.getMap().put("TRADE_NO", trade_no);
              
             System.out.println(commandMap.getMap());
             memberService.delinodel(commandMap.getMap()); //주문 테이블 삭제
             memberService.tradeDelete(commandMap.getMap()); //결제 테이블 삭제
             memberService.deliDelete(commandMap.getMap()); //배송 테이블 삭제
             memberService.amountUpdate(commandMap.getMap());
          }
         
          //페이징
          if(commandMap.get("PAGE").toString().equals(""))
          {
             pagingHtml = pagingHtml(commandMap, 1, session);
          }
          else
          {
             int page = Integer.parseInt(commandMap.get("PAGE").toString());
             pagingHtml = pagingHtml(commandMap, page, session);
          }
         
          List<Map<String, Object>> myOrderList = memberService.myOrderList(commandMap.getMap());
          model.addAttribute("myOrderList", myOrderList);
          model.addAttribute("pagingHtml", pagingHtml);
          return "Member/myOrder";
      }
      
   //나의 위시리스트
   @RequestMapping(value = "/wishList")
   public String wishList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request) 
         throws Exception
   {
	  System.out.println("===========나의 위시리스트 컨트롤러 진입===========");
      String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
      System.out.println(member_id);
      commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
      session.setAttribute("totalcount", 2);
      String pagingHtml;
      
      //페이징
      if(commandMap.get("PAGE").toString().equals(""))
      {
          pagingHtml = pagingHtml(commandMap, 1, session);
      }
      else
      {
         int page = Integer.parseInt(commandMap.get("PAGE").toString());
         pagingHtml = pagingHtml(commandMap, page, session);
      }
      
      List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //WISH 테이블에 있는 내용을 꺼내옴
      model.addAttribute("gcurrentPage", gcurrentPage); //상품 리스트 페이지
      model.addAttribute("pagingHtml", pagingHtml);
      model.addAttribute("wishlist", wishlist);
      
      return "Member/myWish";
   }
   
   //나의 위시리스트 삭제
   @RequestMapping(value = "/myWishList/wish_del")
   public String wishDelete(Model model, CommandMap commandMap, HttpSession session) throws Exception
   {
	  System.out.println("===========나의 위시리스트 삭제(Ajax) 컨트롤러 진입===========");
      int wish_no = Integer.parseInt(commandMap.getMap().get("WISH_NO").toString()); 
      int wish_mem_no = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
      String pagingHtml;
     
      commandMap.getMap().put("WISH_NO", wish_no);
      commandMap.getMap().put("WISH_MEMBER_NO", wish_mem_no);
      
      System.out.println("===========나의 위시리스트 삭제(Ajax)에 필요한 commandMap 출력===========");
      System.out.println(commandMap.getMap());
      memberService.deleteMyWish(commandMap.getMap()); //위시리스트 삭제
      
      String member_id = session.getAttribute("MEMBER_ID").toString();
      commandMap.getMap().put("MEMBER_ID", member_id);
      
      //페이징
      if(commandMap.get("PAGE").toString().equals(""))
      {
          pagingHtml = pagingHtml(commandMap, 1, session);
      }
      else
      {
         int page = Integer.parseInt(commandMap.get("PAGE").toString());
         pagingHtml = pagingHtml(commandMap, page, session);
      }
      
      List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //위시리스트 꺼내옴
      model.addAttribute("wishlist", wishlist);
      model.addAttribute("pagingHtml", pagingHtml);
      
      return "Member/myWish";
   }
   
    //장바구니 리스트
    @RequestMapping(value = "/mybasket")
    public String basketList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request)
    throws Exception
    {
       System.out.println("===========나의 장바구니 내역 컨트롤러 진입===========");	
       String pagingHtml;
       
       String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
       
       session.setAttribute("totalcount", 1); //장바구니 세션 설정
       commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
            
      
      System.out.println(commandMap.getMap());
      
      //페이징
      if(commandMap.get("PAGE").toString().equals(""))
      {
          pagingHtml = pagingHtml(commandMap, 1, session);
      }
      else
      {
         int page = Integer.parseInt(commandMap.get("PAGE").toString());
         pagingHtml = pagingHtml(commandMap, page, session);
      }
      
      List<Map<String,Object>> basketlist = memberService.pagingbasket(commandMap.getMap()); 
      model.addAttribute("pagingHtml", pagingHtml);
      //페이징
      
      model.addAttribute("gcurrentPage", gcurrentPage); //상품 리스트 페이지
      model.addAttribute("basketlist", basketlist);
      
      return "Member/myBasket";
   }
    
    //나의 장바구니 리스트 삭제
    @RequestMapping(value = "/myBasketList/Delete")
    public String basketDelete(Model model, CommandMap commandMap, HttpSession session, 
          @RequestParam(value="BASKET_NO", required=true) List<Integer> basketno) throws Exception
   {
       System.out.println("===========나의 장바구니 취소(Ajax) 컨트롤러 진입===========");	
       System.out.println(basketno.size());
       String pagingHtml;
      
       for(int i=0; i<basketno.size(); i++)
       {
          System.out.println(basketno.get(i));
          commandMap.getMap().put("BASKET_NO", basketno.get(i));
          System.out.println(commandMap.getMap());
          memberService.deleteMyBasket(commandMap.getMap());
       }
       
       //장바구니 삭제 후 페이징 시작
       if(commandMap.get("PAGE").toString().equals(""))
       {
           pagingHtml = pagingHtml(commandMap, 1, session);
       }
       else
       {
          int page = Integer.parseInt(commandMap.get("PAGE").toString());
          pagingHtml = pagingHtml(commandMap, page, session);
       }
       
       String member_id = session.getAttribute("MEMBER_ID").toString(); //세션에서 MEMBER_ID 가져와 String 변수로 받음
       System.out.println(member_id);
       commandMap.getMap().put("MEMBER_ID", member_id); //세션 MEMBER_ID를 commandMap에 넣음
      
       List<Map<String,Object>> basketlist = memberService.pagingbasket(commandMap.getMap()); 
       model.addAttribute("pagingHtml", pagingHtml);
       model.addAttribute("basketlist", basketlist);
      
       return "Member/myBasket";
   }
   
    
   //QNA 게시판 리스트
   @RequestMapping(value ="/myQnaList") 
   public String myQnaList(Model model, CommandMap commandMap, HttpSession session) throws Exception
   {
	   System.out.println("===========나의 QNA LIST 컨트롤러 진입===========");
	   int member_no = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
	   commandMap.getMap().put("MEMBER_NO", member_no);
	   session.setAttribute("totalcount", 4);
	   String pagingHtml;
	   System.out.println(commandMap.getMap());
	   
	   //QNA LIST 페이징
       if(commandMap.get("PAGE").toString().equals(""))
       {
           pagingHtml = pagingHtml(commandMap, 1, session);
       }
       else
       {
          int page = Integer.parseInt(commandMap.get("PAGE").toString());
          pagingHtml = pagingHtml(commandMap, page, session);
       }
       
	   List<Map<String, Object>> qnalist = memberService.myQnaList(commandMap.getMap());
	   model.addAttribute("qnalist", qnalist);
	   model.addAttribute("pagingHtml", pagingHtml);
	   return "Member/myQnaList";
   }
   
   //QNA 게시판 등록 폼
   @RequestMapping(value ="/myQnaWriteForm")
   public String myQnaWriteForm(Model model, CommandMap commandMap)
   {
	   return "Member/myQnaWrite";
   }
   
   //QNA 게시판 등록 처리
   @RequestMapping(value ="/myQnaWrite/writeAction")
   public String myQnaWriteAction(Model model, CommandMap commandMap)
   {
	   return "Member/myQnaWrite";
   }
   
   //QNA 게시판 상세보기
   @RequestMapping(value ="/myQnaView")
   public String myQnaView(Model model, CommandMap commandMap)
   {
	   return "Member/myQnaView";
   }
   
   //QNA 게시판 수정 처리
   @RequestMapping(value = "/myQnaView/updateAction")
   public String myQnaUpdateAction(Model model, CommandMap commandMap)
   {
	   return "Member/myQnaView";
   }
   
   //QNA 게시판 삭제 처리
   @RequestMapping(value = "/myQnaView/deleteAction")
   public String myQnaDeleteAction(Model model, CommandMap commandMap)
   {
	   return "Member/myQnaView";
   }
   
    //페이징 로직
    private String pagingHtml(CommandMap commandMap, int pageNo, HttpSession session) throws Exception
    {      
      int blockCount = 5; //한 페이지에 보여줄 게시물 수
      int totalCount = 0; //총 게시물 수
      
      commandMap.getMap().put("MEMBER_ID", session.getAttribute("MEMBER_ID"));
      int session_no = Integer.parseInt(session.getAttribute("totalcount").toString());
      
      if(session_no == 1)
      {
    	
    	  System.out.println("==========장바구니 세션 번호========== : " + session_no);
    	  totalCount =  memberService.myBasketCnt(commandMap.getMap()); 
    	  System.out.println("==========장바구니 총 게시물 수========== : " + totalCount);
      }
      
      else if(session_no == 2)
      {
    	  System.out.println("==========위시리스트 세션 번호========== : " + session_no);
    	  totalCount = memberService.myWishCnt(commandMap.getMap());
    	  System.out.println("==========위시리스트 총 게시물 수========== : " + totalCount);
      }
      
      else if(session_no == 3)
      {
    	  System.out.println("==========주문내역 세션 번호========== : " + session_no);
    	  totalCount = memberService.myOrderCnt(commandMap.getMap());
    	  System.out.println("==========주문내역 총 게시물 수========== : " + totalCount);
      }
      
      else
      {
    	  System.out.println("==========qna 세션 번호========== : " + session_no);
    	  totalCount = memberService.myQnaCnt(commandMap.getMap());
    	  System.out.println("==========qna 총 게시물 수========== : " + totalCount);
      }
        
      int totalPage = (int) Math.ceil((double) totalCount / blockCount);      
      //System.out.println("totalCount:"+totalCount  +"   blockCount: "+  blockCount );      
      //System.out.println("totalPage:"+totalPage   +"  |||  "+  (int) Math.ceil((double) totalCount / blockCount)        );
      
      String PAGING = String.valueOf(blockCount);   
      String PAGINGNO = String.valueOf(pageNo);   
      
      commandMap.put("PAGING",PAGING); //페이지의 리스트 수
      commandMap.put("PAGINGNO",PAGINGNO); // currentpage    
      
      
      StringBuffer pagingHtml = new StringBuffer();
      
      for(int i=1; i<=totalPage; i++ )
      {         
         
         if(i==pageNo)
         {
            
            pagingHtml.append("<strong>");
            pagingHtml.append(i);                  
            pagingHtml.append("</strong>  ");
         
         }
         else
         {
            
            pagingHtml.append(" <a class='page' href='javascript:ajaxPaging("+i+");'' >" );         
            pagingHtml.append(i);            
            pagingHtml.append("</a> ");
            
         }
         
      }
      
      return pagingHtml.toString();
   }
      
}