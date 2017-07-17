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

//���������� ��Ʈ�ѷ� 
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
   
   // ����¡
    private int currentPage = 1;
    private int totalCount;
    private int blockCount = 7;
    private int blockPage = 5;
    private String pagingHtml;
    private Paging paging;
    
    private String isSearch;
    private int searchNum;
   
   //���������� 
   @RequestMapping(value = "/mypage")
   public String mypage(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {
      String mem_num = session.getAttribute("MEMBER_NO").toString(); //���ǿ��� ȸ����ȣ ��������
      String mem_id = session.getAttribute("MEMBER_ID").toString(); //���ǿ��� ȸ�����̵� ��������
      
      commandMap.getMap().put("MEMBER_NO", mem_num); //ȸ����ȣ commandMap�� �ֱ�
      commandMap.getMap().put("MEMBER_ID", mem_id); //ȸ�� ���̵� commandMap�� �ֱ�
      
      Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
      Map<String, Object> mem_grade = loginService.selectId(commandMap.getMap()); 
      
      model.addAttribute("sumPoint", sumPoint.get("SUM"));
      model.addAttribute("memberGrade", mem_grade.get("MEMBER_GRADE"));
      
      return "mypage";
   }
   
   //ȸ�� ���� ��ȸ
   @RequestMapping(value = "/memberInfo")
   public String memInfo(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {

      System.out.println("ȸ�� ����");
        String mem_id = session.getAttribute("MEMBER_ID").toString();
      
      commandMap.getMap().put("MEMBER_ID", mem_id);
      
      Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
      System.out.println(myInfo.get("MEMBER_ID"));
      model.addAttribute("myInfo", myInfo);
      
      return "Member/member_Info";
   }
   
   //ȸ�� ���� ���� ��
   @RequestMapping(value = "/memberUpdateForm")
   public String memUpdateForm(HttpSession session, Model model, CommandMap commandMap) 
         throws Exception
   { 
      System.out.println("����");
         
      String mem_id = session.getAttribute("MEMBER_ID").toString(); //MEMBER_ID �� ����
         
      commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID Ŀ�ǵ� map�� put
   
      //ȸ�� ID�� �ش��ϴ� ȸ������ myinfo ������ �޾ƿ���
      Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
      //���̵� ���
      System.out.println(myInfo.get("MEMBER_ID"));
      model.addAttribute("myInfo", myInfo); //myinfo ������ ����
           
      String email = (String)myInfo.get("MEMBER_EMAIL");
         
      System.out.println(email);
         
      //�Է��� email �ּҸ� @�� �������� �ɰ�
      String [] split_email = email.split("@");
         
      //���� ���� ����(�̸���) : ex) ykc90831@gmail.com
      session.setAttribute("email1", split_email[0].toString()); // ykc90831
      session.setAttribute("email2", split_email[1].toString()); // gmail.com

      return "Member/mem_update_Form";
   }
   
   //ȸ�� ���� ���� ó��
   @RequestMapping(value = "/memberUpdateAction")
   public String memUpdateAction(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {
      System.out.println("����");
      //split �� �̸����� �ٽ� ��ħ
      String MEMBER_EMAIL = commandMap.getMap().get(("MEMBER_EMAIL1"))
            +"@"+ commandMap.getMap().get(("MEMBER_EMAIL2"));
      
      commandMap.getMap().put("MEMBER_EMAIL", MEMBER_EMAIL); //MEMBER_EMAIL map�� ����
      
      System.out.println(commandMap.getMap()); //map Ȯ��
      
      //updatemember map ����
      Map<String, Object> updatemember = new HashMap<String, Object>();
       updatemember = commandMap.getMap(); //update �� ������ updatemember�� ����
       memberService.updateMyinfo(updatemember); //update ���� ����
       
       //memberMap map ����
       Map<String, Object> memberMap = new HashMap<String, Object>();
       memberMap = memberService.myinfoDetail(commandMap.getMap()); //�ٲ� ȸ������ �ҷ���
       model.addAttribute("memberInfo", memberMap); //model�� ����
       
       return "Member/mem_update_Form";
   }

   
   //ȸ�� ���� ���� ó��
   @RequestMapping(value = "/memberDeleteAction")
   public String memDeleteAction(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {
      System.out.println("����");
      String mem_id = commandMap.getMap().get("MEMBER_ID").toString(); //ajax jason data�� map�� ����
      
      commandMap.getMap().put("MEMBER_ID", mem_id); //MEMBER_ID commandMap�� ����
      commandMap.getMap().put("MEMBER_NO", session.getAttribute("MEMBER_NO")); //MEMBER_NO commandMap�� ����
      System.out.println(commandMap.getMap());
   
      memberService.deleteMember(commandMap.getMap()); //update onoff = 1(ȸ��Ż��) ���� ����
      
      Map<String, Object> member = new HashMap<String, Object>(); //member map ����
      member = memberService.myinfoDetail(commandMap.getMap()); //ȸ�� ������ map�� ����
      
      int onoff = Integer.parseInt(member.get("MEMBER_ONOFF").toString());
      
      if(onoff == 1)
      {
        model.addAttribute("onoff", onoff);
         session.invalidate(); //���� ����
      }
          
      return "Member/member_Info";
    }
   
   //���� ����Ʈ ����
   @RequestMapping(value = "/myPoint")
   public String mypoint(HttpSession session, Model model, CommandMap commandMap) throws Exception 
   {
         System.out.println("����");
         String mem_no = session.getAttribute("MEMBER_NO").toString();
         
         commandMap.getMap().put("MEMBER_NO", mem_no); //ȸ�� ��ȣ commandMap�� �ֱ�
         
         Map<String, Object> sumPoint = pointService.sumPoint(commandMap.getMap());
         List<Map<String, Object>> pointList = pointService.myPointList(commandMap.getMap());
         System.out.println(pointList);
         
         model.addAttribute("sumPoint", sumPoint.get("SUM"));
         model.addAttribute("pointList", pointList);
         
         
         System.out.println("���� ����Ʈ ����");
         
         return "Member/myPoint";
      }
   
   //���� �ֹ� ����
   @RequestMapping(value="/orderInfo")
   public String orderInfo(Model model)
   {
      System.out.println("���� �ֹ� ����");
      return "Member/myOrder";
   }
   
   //���� �ֹ� �󼼺���
   @RequestMapping(value="/orderInfoView")
   public String orderInfoView(Model model)
   {
      return "orderInfoView";
   }
   
   //�ֹ���ǰ ���
   @RequestMapping(value="/orderInfo/order_del")
   public String orderDel(Model model)
   {
      return "Member/myOrder";
   }
   
   //�ֹ���ǰ ��� ó��
   @RequestMapping(value="/ordercancel")
   public String orderCancel(Model model)
   {
      return "redirect:/orderInfo";
   }
   
   //���� ���ø���Ʈ
   @RequestMapping(value = "/wishList")
   public String wishList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request) 
         throws Exception
   {
      
      String member_id = session.getAttribute("MEMBER_ID").toString(); //���ǿ��� MEMBER_ID ������ String ������ ����
      System.out.println(member_id);
      commandMap.getMap().put("MEMBER_ID", member_id); //���� MEMBER_ID�� commandMap�� ����
      
      //�信�� ������ currentPage�� ���� ���
      if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
            || request.getParameter("currentPage").equals("0")) 
      {
         currentPage = 1;
      } 
      else 
      {
         currentPage = Integer.parseInt(request.getParameter("currentPage"));
      }
      
      List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //WISH ���̺� �ִ� ������ ������
      model.addAttribute("gcurrentPage", gcurrentPage); //��ǰ ����Ʈ ������
      isSearch = request.getParameter("isSearch");
      
        if (isSearch != null) //�˻��� �� ���
        {
         searchNum = Integer.parseInt(request.getParameter("searchNum"));

         System.out.println("getMap : " + commandMap.getMap());

         if (searchNum == 0) //��ǰ �̸�
         { 
            wishlist = memberService.searchWish0(commandMap.getMap());
         } 
        
        totalCount = wishlist.size();
      paging = new Paging(currentPage, totalCount, blockCount, blockPage, "wishList", searchNum, isSearch);
      pagingHtml = paging.getPagingHtml().toString();
      int lastCount = totalCount;
      
      if (paging.getEndCount() < totalCount)
      {   lastCount = paging.getEndCount() + 1; }

      wishlist = wishlist.subList(paging.getStartCount(), lastCount);
      
      model.addAttribute("isSearch", isSearch);
      model.addAttribute("searchNum", searchNum);
      model.addAttribute("totalCount", totalCount);
      model.addAttribute("pagingHtml", pagingHtml);
      model.addAttribute("currentPage", currentPage);
      model.addAttribute("wishlist", wishlist);
      
        }
        else 
        {

         totalCount = wishlist.size();

         paging = new Paging(currentPage, totalCount, blockCount, blockPage, "wishList", searchNum, isSearch);
         pagingHtml = paging.getPagingHtml().toString();

         int lastCount = totalCount;

         if (paging.getEndCount() < totalCount)
            {lastCount = paging.getEndCount() + 1;}

         wishlist = wishlist.subList(paging.getStartCount(), lastCount);

         model.addAttribute("totalCount", totalCount);
         model.addAttribute("pagingHtml", pagingHtml);
         model.addAttribute("currentPage", currentPage);

         model.addAttribute("wishlist", wishlist); //WISH LIST�� "wishlist" ������ ����
         
         
      }
        return "Member/myWish";
   }
   
   //���� ���ø���Ʈ ����
   @RequestMapping(value = "/myWishList/wish_del")
   public String wishDelete(Model model, CommandMap commandMap, HttpSession session) throws Exception
   {
      System.out.println("���ø���Ʈ ����");
      int wish_no = Integer.parseInt(commandMap.getMap().get("WISH_NO").toString()); 
      int wish_mem_no = Integer.parseInt(session.getAttribute("MEMBER_NO").toString());
      commandMap.getMap().put("WISH_NO", wish_no);
      commandMap.getMap().put("WISH_MEMBER_NO", wish_mem_no);
      
      System.out.println(commandMap.getMap());
      memberService.deleteMyWish(commandMap.getMap()); //���ø���Ʈ ����
      
      String member_id = session.getAttribute("MEMBER_ID").toString();
      commandMap.getMap().put("MEMBER_ID", member_id);
      List<Map<String,Object>> wishlist = memberService.myWishList(commandMap.getMap()); //���ø���Ʈ ������
      model.addAttribute("wishlist", wishlist);
      
      return "Member/myWish";
   }
   
   //��ٱ��� ����Ʈ
    @RequestMapping(value = "/mybasket")
    public String basketList(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request)
    throws Exception
   {
       System.out.println("���� ��ٱ��� ����");
       String member_id = session.getAttribute("MEMBER_ID").toString(); //���ǿ��� MEMBER_ID ������ String ������ ����
       System.out.println(member_id);
      commandMap.getMap().put("MEMBER_ID", member_id); //���� MEMBER_ID�� commandMap�� ����
      
      List<Map<String,Object>> basketlist = memberService.myBasketList(commandMap.getMap()); //WISH ���̺� �ִ� ������ ������
      model.addAttribute("gcurrentPage", gcurrentPage); //��ǰ ����Ʈ ������
      model.addAttribute("basketlist", basketlist);
      
      return "Member/myBasket";
   }
    
    //���� ��ٱ��� ����Ʈ ����
    
    @RequestMapping(value = "/myBasketList/Delete")
    public String basketDelete(Model model, CommandMap commandMap, HttpSession session, 
          @RequestParam(value="BASKET_NO", required=true) List<Integer> basketno) throws Exception
   {
       System.out.println("��ٱ��� ����");
       System.out.println(basketno.size());
       
       for(int i=0; i<basketno.size(); i++)
       {
          /*commandMap.getMap().put("BASKET_NO", basketno.get(i));*/
          System.out.println(basketno.get(i));
       }
       System.out.println(commandMap.getMap());
       return "Member/myBasket";
   }
    
    
      
}