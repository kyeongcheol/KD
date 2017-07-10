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
//import 페이징

import SG.com.admin.service.AdminFaqService;

@Controller
public class MemberFaqController {
	
	@Resource(name="adminFaqService")
	private AdminFaqService adminFaqService;
	
	
	private int searchNum;//목록을 구분하는 변수
	private String isSearch;//검색값을 받아오게끔 변수 설정
	
	@RequestMapping(value = "/memberFaqList")
	public String memberFaqList(Model model,CommandMap commandMap, HttpServletRequest request)throws Exception{
	
	List<Map<String, Object>>list = adminFaqService.faqList(commandMap.getMap()); //faqList에 담겨있는 list가 뿌려져있는 정보를 
	model.addAttribute("list",list);
	/*String search = request.getParameter("isSearch");*/
	Map<String, Object>isSearchMap = new HashMap<String, Object>();
	
	if(request.getParameter("isSearch") != null){//검색값이 있을떄
		isSearch=request.getParameter("isSearch");
		searchNum=Integer.parseInt(request.getParameter("searchNum"));
		isSearchMap.put("isSearch", isSearch);
		
		if(searchNum == 0){//글제목
			list = adminFaqService.faqSearch0(isSearchMap, isSearch);
		}
		if(searchNum == 1){//글내용
			list = adminFaqService.faqSearch1(isSearchMap, isSearch);
		}
		if(searchNum == 2){//카테고리 검색
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
    



