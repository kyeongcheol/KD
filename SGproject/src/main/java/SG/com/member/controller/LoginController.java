package SG.com.member.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import SG.com.common.CommandMap;
import SG.com.member.service.LoginService;

@Controller
public class LoginController 
{
	@Resource(name="loginService")
	private LoginService loginService;
	
	//�α��� ��
	@RequestMapping(value = "/loginForm")
	public String login(Model model) 
	{
		return "loginForm";
	}
	
	//�α��� ���� ���� üũ(�α��� ó��)
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(Model model, CommandMap commandMap, HttpSession session, HttpServletRequest request)
	throws Exception
	{
		//�ش� ���̵�� �˻��Ͽ� ȸ�� ���� ��������
		Map<String, Object> loginChk = loginService.selectId(commandMap.getMap());

		//���� �Ǿ� ���� ������
		if(loginChk == null)
		{
			//�Է��� ���̵� ���ٴ� �޽��� ���
			model.addAttribute("message", "�ش� ���̵� �����ϴ�.");
			return "loginForm";
			
		}
		//�ش� ȸ�������� ������
		else
		{
			 System.out.println("��й�ȣ1 : " + loginChk.get("MEMBER_PASSWORD") 
			    + "\n ��й�ȣ2 : " + commandMap.get("MEMBER_PASSWORD"));
			 
			//��й�ȣ�� ��ġ�ϸ�
		    if(loginChk.get("MEMBER_PASSWORD").toString().
		    		equals(commandMap.get("MEMBER_PASSWORD").toString()))
		    {
		    	//loginChk Map�� "MEMBER" ������ ����
		    	model.addAttribute("MEMBER", loginChk);
		    	//���� ���� ����(���̵�, ȸ�� �̸�, ȸ����ȣ)
		    	session.setAttribute("MEMBER_ID", commandMap.get("MEMBER_ID"));
		    	session.setAttribute("MEMBER_NO", loginChk.get("MEMBER_NO"));
		    	session.setAttribute("MEMBER_NAME", loginChk.get("MEMBER_NAME"));
		    	
		    	return "redirect:/main";
		    }		    		    
		    	model.addAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		    	return "loginForm";		    
		}
	}
	
	//�α׾ƿ�
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest request, HttpSession session, CommandMap commandMap) 
	{
		//getSession(false) : ���� ������ �����ϸ� ���� ���� ����, ������ null�� ����
		session = request.getSession(false);
		
		//���� ������ �����ϸ�
		if(session != null)
		{
			//���� �Ҹ�
			session.invalidate();
		}
		
		return "redirect:/main";
	}
	
	//���̵� ã�� �� 
	@RequestMapping(value = "/login/findIdForm")
	public String findIdForm(Model model) 
	{
		return "Member/find_Id";
	}
	
	//���̵� ã�� ó��
	@RequestMapping(value = "/login/findId")
	public @ResponseBody String findId(Model model, CommandMap commandMap)
	throws Exception
	{
		//ajax jason data
		String idname = (String)commandMap.getMap().get("idname"); 
		String idemail = (String)commandMap.getMap().get("idemail"); 
		
		System.out.println("���̵� ã��");
		//json data -> commandMap�� ���� �÷���� �����Ͽ� �־���
		commandMap.put("MEMBER_NAME_FIND", idname);
		commandMap.put("MEMBER_EMAIL_FIND", idemail);
		
		//���� ��ȯ �� String ������ ����
		String findId = loginService.findId(commandMap.getMap());
        System.out.println("ã�� ���̵� : " + findId);
        
		return findId;
	}
	
	//��й�ȣ ã�� ��
	@RequestMapping(value = "/login/findPwForm")
	public String findPwForm(Model model)
	{
		return "Member/find_Pw";
	}
	
	//��й�ȣ ã�� ó��
	@RequestMapping(value = "/login/findPw")
	public @ResponseBody String findPw(Model model, CommandMap commandMap) throws Exception
	{
		//ajax json data
		String pwname = (String)commandMap.getMap().get("pwname");
		String pwid = (String)commandMap.getMap().get("pwid");
		String pwemail = (String)commandMap.getMap().get("pwemail");
		
		System.out.println(pwname);
		System.out.println(pwid);
		System.out.println(pwemail);
		
		System.out.println("��й�ȣ ã��");
		//json data -> commandMap�� ���� �÷���� �����Ͽ� �־���
		commandMap.put("MEMBER_NAME_FIND", pwname);
		commandMap.put("MEMBER_ID_FIND", pwid);
		commandMap.put("MEMBER_EMAIL_FIND", pwemail);
		
		System.out.println(commandMap.getMap());
		
		//���� ��ȯ �� String ������ �ޱ�
		String findPw = loginService.findPw(commandMap.getMap());
		
		
		System.out.println("ã����й�ȣ " + findPw);
		
		return findPw;
	}

	
	
}
