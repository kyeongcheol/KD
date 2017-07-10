package SG.com.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import SG.com.common.CommandMap;
//import ����¡

import SG.com.admin.service.AdminFaqService;

@Controller
public class MemberFaqController {
	
	@Resource(name="adminFaqService")
	private AdminFaqService adminFaqService;
	
	
	private int searchNum;//����� �����ϴ� ����
	private String isSearch;//�˻����� �޾ƿ��Բ� ���� ����
	
	@RequestMapping(value = "/memberFaqList")
	public String memberFaqList(Model model,CommandMap commandMap, HttpServletRequest request)throws Exception{
	
	List<Map<String, Object>>list = adminFaqService.faqList(commandMap.getMap()); //faqList�� ����ִ� list�� �ѷ����ִ� ������ 
	model.addAttribute("list",list);
	/*String search = request.getParameter("isSearch");*/
	Map<String, Object>isSearchMap = new HashMap<String, Object>();
	
	if(request.getParameter("isSearch") != null){//�˻����� ������
		isSearch=request.getParameter("isSearch");
		searchNum=Integer.parseInt(request.getParameter("searchNum"));
		isSearchMap.put("isSearch", isSearch);
		
		if(searchNum == 0){//������
			list = adminFaqService.faqSearch0(isSearchMap, isSearch);
		}
		if(searchNum == 1){//�۳���
			list = adminFaqService.faqSearch1(isSearchMap, isSearch);
		}
		if(searchNum == 2){//ī�װ� �˻�
			list = adminFaqService.faqSearch2(isSearchMap, isSearch);
		}
		model.addAttribute("list",list);
		

	
	
	return "memberFaqList_tiles";
	}else{
		
	 return "memberFaqList_tiles";
	}
		
	}

    @RequestMapping(value="/memberFaqDetail")
    public String faqDetail(Model model, CommandMap commandMap)throws Exception{
    	Map<String, Object>map = adminFaqService.faqDetail(commandMap.getMap());
    	model.addAttribute("map",map.get("map"));
    	
 
    	
    	return "memberFAqDetail";
    }

}
    



