package SG.com.admin.controller;


import java.util.HashMap;//�ؽ��ʾ���
import java.util.List;//����Ʈ����
import java.util.Map;//�ʾ���

import javax.annotation.Resource;//������̼� Resource����
import javax.servlet.http.HttpServletRequest;//request����

import org.springframework.stereotype.Controller;//�̰��� ��Ʈ�ѷ���
import org.springframework.web.bind.annotation.RequestMapping;
//�̰��� 
import org.springframework.ui.Model;


import SG.com.common.CommandMap;
/*import spring.kh.siroragi.Paging;*/
//����¡

import SG.com.admin.service.AdminFaqService;

@Controller//��Ʈ�ѷ��� �����ϸ� ������ �����̳ʿ��� ��ü�� ��������� ��밡��
public class AdminFaqController {
	
	@Resource(name="adminFaqService")
	private AdminFaqService adminFaqService;
	
	//�˻� �ѹ���,�˻� ��(String)�� �޾ƿ������� ����
	private int searchNum;
	String isSearch;
	
	//������ �������� �̵� *�������� �帧���� �־����*
	@RequestMapping(value = "/admin" )
	public String list(){
		return "adminForm";//adminMain.jsp�� �̵��ϰ� ����
	}
	//FAQ������ �������� �̵�(����̵�)
	@RequestMapping(value="/adminFaq")
	public String adminFaq(Model model,CommandMap commandMap, HttpServletRequest request)throws Exception{
		
		List<Map<String,Object>> list = adminFaqService.faqList(commandMap.getMap());
		String search = request.getParameter("isSearch");
		Map<String, Object> isSearchMap = new HashMap<String, Object>();
		
		if(request.getParameter("isSearch") !=null){
			isSearch = new String(search.getBytes("iso-885901"), "utf-8");
			//�޾ƿ��°� utf-8�� �ٲٴ� �۾�
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			//�Ķ���͸� �޾ƿË� String ������ ������°� int�� �ٲٴ��۾�
			isSearchMap.put("isSearch",isSearch);
			//HashMap�� "isSearch"��� �̸����� utf-8�� ���ڵ��� isSearch���� �־���
			
			if(searchNum == 0){//������=0
				list = adminFaqService.faqSearch0(isSearchMap ,isSearch);
				//isSearch�� �־���ϴ� ���� �޼ҵ带 map�� �������ִ°� �����Ǵµ�
				//�޼��带 ���鋚 map,isSearch�� �޾ƾ� �����ְԲ� �����س���
				}
			if(searchNum == 1){//�۳���=1
				list = adminFaqService.faqSearch1(isSearchMap, isSearch);
			}
			if(searchNum == 2){//ī�װ�=2
				list = adminFaqService.faqSearch2(isSearchMap, isSearch);
			}
			System.out.println(list);
			model.addAttribute("list",list);
			
			return "admin_faq";
			
			
		}else{//�˻� ���� ������
			
			model.addAttribute("list",list);
			return "admin_faq";
		}
	}
	
	//FAQ�����
	@RequestMapping(value="/adminFaqForm")
	public String adminFaqForm(){
		return "/Admin/adminFaqForm";
	}//jsp���� ��������� submit�ؼ� db�� insert�Ȱ��� �Ʒ� FAQ��Ͽ���
	//�ʿ��� �����ͼ� �����̷�Ʈ�� tiles�����ʰ� url���ٰ� ���� ����
	
	//FAQ���
	@RequestMapping(value = "/adminFaqWrite")
	public String adminFaqWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		adminFaqService.faqWrite(commandMap.getMap(), request);
		//�ʿ��� �������� �׻� ���������� jsp���� db���� ������ type��� �����̵Ǵ���
		//�� �ڼ��� �����Ѵ�

		return "redirect:/adminFaq";
	}
	//FAQ ������ �̵�
	@RequestMapping(value = "/adminNoticeModifyForm")
	public String adminFaqModifyForm(Model model,CommandMap commandMap) throws Exception {

		Map<String, Object> map = adminFaqService.faqDetail(commandMap.getMap());
		//�󼼺��⿡ ���� ���� Ŀ�ǵ� �ʿ��� ������
		model.addAttribute("map", map.get("map"));
		//�󼼺��⿡ ����ִ� ������ ������ map�� �ٽ�����

		return "adminNoticeModifyForm";
	}
	

	// FAQ ����
	@RequestMapping(value = "/adminFaqModify")
	public String adminFaqModify(Model model, CommandMap commandMap) throws Exception {

		adminFaqService.faqModify(commandMap.getMap());

		model.addAttribute("FAQ_NO", commandMap.get("FAQ_NO"));

		return "redirect:/adminFaq";
	}
	
	// FAQ �����ϱ�
	@RequestMapping(value = "/adminFaqDelete")
	public String adminFaqDelete(CommandMap commandMap) throws Exception {


		adminFaqService.faqDelete(commandMap.getMap());

		return "redirect:/adminFaq";
	}

	
	
}

