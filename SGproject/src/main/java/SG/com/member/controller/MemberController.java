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
	public String memInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
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
	public String memUpdateForm(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) 
			throws Exception
	{ 
	   System.out.println("����");
			
	   String mem_id = session.getAttribute("MEMBER_ID").toString();
			
	   commandMap.getMap().put("MEMBER_ID", mem_id);
			
	   Map<String, Object> myInfo = memberService.myinfoDetail(commandMap.getMap());
	   System.out.println(myInfo.get("MEMBER_ID"));
	   model.addAttribute("myInfo", myInfo);
			  
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
	public String memUpdateAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
		System.out.println("����");
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
	
	//ȸ�� ���� ���� ó��
	@RequestMapping(value = "/memberDeleteAction")
	public String memDeleteAction(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model, CommandMap commandMap) throws Exception 
	{
	   String mem_num = session.getAttribute("MEMBER_NO").toString();
	      
	   commandMap.getMap().put("MEMBER_NO", mem_num);
	      
	   memberService.deleteMember(commandMap.getMap());
	      
	   session.invalidate();
	       
	   return "redirect:main";
	 }
	
	//���� ����Ʈ ����
	@RequestMapping(value = "/myPoint")
	public String mypoint(Model model) 
	{
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
	public String wishList(Model model)
	{
		return "Member/myWish";
	}
	
	//���� ���ø���Ʈ ����
	@RequestMapping(value = "/myWishList/wish_del")
	public String wishDelete(Model model)
	{
		return "redirect:/wishList";
	}
	
	//��ٱ��� ����Ʈ
    @RequestMapping(value = "/mybasket")
    public String basketList(Model model) 
	{
    	System.out.println("���� ��ٱ��� ����");
    	
		return "Member/myBasket";
	}
    
    @RequestMapping(value = "/myBasketList/Delete")
    public String basketDelete(Model model) 
	{
    	return "redirect:/mybasket";
	}
    
    
		
}
