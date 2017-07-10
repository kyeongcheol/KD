package SG.com.admin.controller;


import java.util.HashMap;//해쉬맵쓸것
import java.util.List;//리스트쓸것
import java.util.Map;//맵쓸것

import javax.annotation.Resource;//어노테이션 Resource쓸것
import javax.servlet.http.HttpServletRequest;//request쓸것

import org.springframework.stereotype.Controller;//이것이 컨트롤러다
import org.springframework.web.bind.annotation.RequestMapping;
//이것이 
import org.springframework.ui.Model;


import SG.com.common.CommandMap;
/*import spring.kh.siroragi.Paging;*/
//페이징

import SG.com.admin.service.AdminFaqService;

@Controller//컨트롤러를 설정하면 스프링 컨테이너에서 객체로 만들어져서 사용가능
public class AdminFaqController {
	
	@Resource(name="adminFaqService")
	private AdminFaqService adminFaqService;
	
	//검색 넘버와,검색 값(String)을 받아오기위한 변수
	private int searchNum;
	String isSearch;
	
	//관리자 페이지로 이동 *수정사항 흐름도에 넣어야함*
	@RequestMapping(value = "/admin" )
	public String list(){
		return "adminForm";//adminMain.jsp로 이동하게 설정
	}
	//FAQ관리자 페이지로 이동(목록이동)
	@RequestMapping(value="/adminFaq")
	public String adminFaq(Model model,CommandMap commandMap, HttpServletRequest request)throws Exception{
		
		List<Map<String,Object>> list = adminFaqService.faqList(commandMap.getMap());
		String search = request.getParameter("isSearch");
		Map<String, Object> isSearchMap = new HashMap<String, Object>();
		
		if(request.getParameter("isSearch") !=null){
			isSearch = new String(search.getBytes("iso-885901"), "utf-8");
			//받아오는값 utf-8로 바꾸는 작업
			searchNum = Integer.parseInt(request.getParameter("searchNum"));
			//파라미터를 받아올떄 String 값으로 날라오는걸 int로 바꾸는작업
			isSearchMap.put("isSearch",isSearch);
			//HashMap에 "isSearch"라는 이름으로 utf-8로 인코딩된 isSearch값을 넣어줌
			
			if(searchNum == 0){//글제목=0
				list = adminFaqService.faqSearch0(isSearchMap ,isSearch);
				//isSearch를 넣어야하는 이유 메소드를 map만 넣을수있는걸 만들면되는데
				//메서드를 만들떄 map,isSearch를 받아야 쓸수있게끔 설정해놓음
				}
			if(searchNum == 1){//글내용=1
				list = adminFaqService.faqSearch1(isSearchMap, isSearch);
			}
			if(searchNum == 2){//카테고리=2
				list = adminFaqService.faqSearch2(isSearchMap, isSearch);
			}
			System.out.println(list);
			model.addAttribute("list",list);
			
			return "admin_faq";
			
			
		}else{//검색 값이 없을때
			
			model.addAttribute("list",list);
			return "admin_faq";
		}
	}
	
	//FAQ등록폼
	@RequestMapping(value="/adminFaqForm")
	public String adminFaqForm(){
		return "/Admin/adminFaqForm";
	}//jsp에서 등록폼에서 submit해서 db로 insert된것을 아래 FAQ등록에서
	//맵에서 꺼내와서 리다이렉트로 tiles걸지않고 url에다가 봐로 쏴줌
	
	//FAQ등록
	@RequestMapping(value = "/adminFaqWrite")
	public String adminFaqWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		adminFaqService.faqWrite(commandMap.getMap(), request);
		//맵에서 꺼내쓸때 항상 유의할점은 jsp에서 db에서 설정된 type들과 매핑이되는지
		//를 자세히 봐야한다

		return "redirect:/adminFaq";
	}
	//FAQ 수정폼 이동
	@RequestMapping(value = "/adminNoticeModifyForm")
	public String adminFaqModifyForm(Model model,CommandMap commandMap) throws Exception {

		Map<String, Object> map = adminFaqService.faqDetail(commandMap.getMap());
		//상세보기에 대한 것을 커맨드 맵에서 꺼내옴
		model.addAttribute("map", map.get("map"));
		//상세보기에 들어있는 정보를 꺼내서 map에 다시저장

		return "adminNoticeModifyForm";
	}
	

	// FAQ 수정
	@RequestMapping(value = "/adminFaqModify")
	public String adminFaqModify(Model model, CommandMap commandMap) throws Exception {

		adminFaqService.faqModify(commandMap.getMap());

		model.addAttribute("FAQ_NO", commandMap.get("FAQ_NO"));

		return "redirect:/adminFaq";
	}
	
	// FAQ 삭제하기
	@RequestMapping(value = "/adminFaqDelete")
	public String adminFaqDelete(CommandMap commandMap) throws Exception {


		adminFaqService.faqDelete(commandMap.getMap());

		return "redirect:/adminFaq";
	}

	
	
}

