package SG.com.admin.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/*import javax.servlet.http.HttpSession;*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*import org.springframework.web.bind.annotation.RequestMethod;*/
/*import org.springframework.web.bind.annotation.RequestParam;*/
/*import org.springframework.web.bind.annotation.ResponseBody;*/
import org.springframework.ui.Model;
/*import org.springframework.validation.BindingResult;*/

import SG.com.admin.service.AdminQnAService;
//Resource가 안먹힘
import SG.com.common.CommandMap;
//페이징 import

@Controller
public class AdminQnAController {
	
	@Resource(name= "adminQnAService")
	private AdminQnAService adminQnAService;
	
	//검색
	private int searchNum;
	private int categoryNum;
	private String isSearch;
	
@RequestMapping(value="/adminQnA")
public String adminQnA( CommandMap commandMap, Model model , HttpServletRequest request)throws Exception{	
		List<Map<String, Object>>list = adminQnAService.adminQnaList(commandMap.getMap());
		//쿼리문으로 실행되는 리시트로 뽑아낸 데이터를 list 변수로 선언해서 "list"라는 이름으로 쓰기위한
		//작업
		isSearch = request.getParameter("isSearch");//넘어오는 키워드를 꺼내서 저장
		categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
		searchNum = Integer.parseInt(request.getParameter("searchNum"));
		//isSearch는 "isSearch"라는 값으로 받아온다.검색값
		if(isSearch !=null){//검색값이  있으면
			if (searchNum == 0) {//아이디로 조회
				commandMap.put("아이디", isSearch);
				list = adminQnAService.qnaIdSearch(commandMap.getMap());
				}
				//아이디에 따른 카테고리 시작				
				/*===========================================================*/
				//쿼리문당 카테고리 다르게 분류//
				if (categoryNum == 0) { 
					commandMap.put("카테고리", 0 );
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 1) { // 상품에 해당하는 Q&A 목록 불러오기

					commandMap.put("QNA_CATEGORY", "상품문의");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 2) { // 배송에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "배송문의");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 3) { // 입금에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "입금문의");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 4) { // 교환&반품에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "교환&반품문의");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 5) { // 기타에 해당하는 Q&A 목록 불러오기
					commandMap.put("QNA_CATEGORY", "기타문의");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 6) { // 기타에 해당하는 Q&A 목록 불러오기
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				

			
			if (searchNum == 1) {//제목에 따른 검색
				commandMap.put("제목", isSearch);
				list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
			
			}
		}
		    model.addAttribute("isSearch", isSearch);
		    model.addAttribute("searchNum",searchNum);
		    model.addAttribute("categoryNum",categoryNum );
		    model.addAttribute("list", list);
		    
		    return "admin_QnA";
	}
	//Q&A목록조회(Q&A에 대한전체적인 목록은 관리자페이지에서도 보여아함)
	//*url mapping=/adminQnA
	//*메소드 adminQnA()
	//*tiles adminQnA->adminQnA.jsp
		    
 @RequestMapping(value = "/adminAnswerForm")
public String adminAnswerForm(CommandMap commandMap, Model model, HttpServletRequest request  )throws Exception{
		    	if(request.getParameter("QNA_NO")!=null){
		    		//글번호가 있을때
		    		Map<String, Object> qna=adminQnAService.qnaDetail(commandMap.getMap());
		    		//쿼리문을 qna에 저장
		    		model.addAttribute("qna", qna);
		    		//qna로 저장되서 view 페이지에 뿌려줌
		    	}
		    	return "adminAnswerForm";
		    	
		    	
		    	
		    }
		    
			
			
	//Q&A 답변폼 이동
	//*url mapping=/adminAnswerForm
	//*메소드 adminAnswerForm()
	//*tiles adminAnswerForm->adminAnswerForm.jsp

		    //일단보류
@RequestMapping(value="/adminAnswer")
public String adminAnswer(CommandMap commandMap, Model model, HttpServletRequest request)throws Exception{
		  adminQnAService.ans(commandMap.getMap());
		   return "adminQnA";
		    
		    
		    	
		    }
    //Q&A 답변
	//*url mapping=/adminAnswer
	//*메소드 adminAnswer()
	//*tiles adminQnA->adminQnA.jsp
		    
@RequestMapping(value="/adminAnswerDelete")
public String adminAnswerDelete(CommandMap commandMap, Model model, HttpServletRequest request)throws Exception{
	adminQnAService.qnaDelete(commandMap.getMap());
	return "adminQnA";
		    	
		    }
	
	
	//Q&A 삭제
	//*url mapping=/adminAnswerDelete
	//*메소드 adminAnswerDelete()
	//*tiles adminQnA ->adminQnA.jsp
	}

