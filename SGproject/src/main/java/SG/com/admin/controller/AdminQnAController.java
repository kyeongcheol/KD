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
//Resource�� �ȸ���
import SG.com.common.CommandMap;
//����¡ import

@Controller
public class AdminQnAController {
	
	@Resource(name= "adminQnAService")
	private AdminQnAService adminQnAService;
	
	//�˻�
	private int searchNum;
	private int categoryNum;
	private String isSearch;
	
@RequestMapping(value="/adminQnA")
public String adminQnA( CommandMap commandMap, Model model , HttpServletRequest request)throws Exception{	
		List<Map<String, Object>>list = adminQnAService.adminQnaList(commandMap.getMap());
		//���������� ����Ǵ� ����Ʈ�� �̾Ƴ� �����͸� list ������ �����ؼ� "list"��� �̸����� ��������
		//�۾�
		isSearch = request.getParameter("isSearch");//�Ѿ���� Ű���带 ������ ����
		categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
		searchNum = Integer.parseInt(request.getParameter("searchNum"));
		//isSearch�� "isSearch"��� ������ �޾ƿ´�.�˻���
		if(isSearch !=null){//�˻�����  ������
			if (searchNum == 0) {//���̵�� ��ȸ
				commandMap.put("���̵�", isSearch);
				list = adminQnAService.qnaIdSearch(commandMap.getMap());
				}
				//���̵� ���� ī�װ� ����				
				/*===========================================================*/
				//�������� ī�װ� �ٸ��� �з�//
				if (categoryNum == 0) { 
					commandMap.put("ī�װ�", 0 );
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 1) { // ��ǰ�� �ش��ϴ� Q&A ��� �ҷ�����

					commandMap.put("QNA_CATEGORY", "��ǰ����");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 2) { // ��ۿ� �ش��ϴ� Q&A ��� �ҷ�����
					commandMap.put("QNA_CATEGORY", "��۹���");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 3) { // �Աݿ� �ش��ϴ� Q&A ��� �ҷ�����
					commandMap.put("QNA_CATEGORY", "�Աݹ���");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 4) { // ��ȯ&��ǰ�� �ش��ϴ� Q&A ��� �ҷ�����
					commandMap.put("QNA_CATEGORY", "��ȯ&��ǰ����");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 5) { // ��Ÿ�� �ش��ϴ� Q&A ��� �ҷ�����
					commandMap.put("QNA_CATEGORY", "��Ÿ����");
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				else if (categoryNum == 6) { // ��Ÿ�� �ش��ϴ� Q&A ��� �ҷ�����
					list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
				}
				

			
			if (searchNum == 1) {//���� ���� �˻�
				commandMap.put("����", isSearch);
				list = adminQnAService.qnaCtgSearch1(commandMap.getMap());
			
			}
		}
		    model.addAttribute("isSearch", isSearch);
		    model.addAttribute("searchNum",searchNum);
		    model.addAttribute("categoryNum",categoryNum );
		    model.addAttribute("list", list);
		    
		    return "admin_QnA";
	}
	//Q&A�����ȸ(Q&A�� ������ü���� ����� ������������������ ��������)
	//*url mapping=/adminQnA
	//*�޼ҵ� adminQnA()
	//*tiles adminQnA->adminQnA.jsp
		    
 @RequestMapping(value = "/adminAnswerForm")
public String adminAnswerForm(CommandMap commandMap, Model model, HttpServletRequest request  )throws Exception{
		    	if(request.getParameter("QNA_NO")!=null){
		    		//�۹�ȣ�� ������
		    		Map<String, Object> qna=adminQnAService.qnaDetail(commandMap.getMap());
		    		//�������� qna�� ����
		    		model.addAttribute("qna", qna);
		    		//qna�� ����Ǽ� view �������� �ѷ���
		    	}
		    	return "adminAnswerForm";
		    	
		    	
		    	
		    }
		    
			
			
	//Q&A �亯�� �̵�
	//*url mapping=/adminAnswerForm
	//*�޼ҵ� adminAnswerForm()
	//*tiles adminAnswerForm->adminAnswerForm.jsp

		    //�ϴܺ���
@RequestMapping(value="/adminAnswer")
public String adminAnswer(CommandMap commandMap, Model model, HttpServletRequest request)throws Exception{
		  adminQnAService.ans(commandMap.getMap());
		   return "adminQnA";
		    
		    
		    	
		    }
    //Q&A �亯
	//*url mapping=/adminAnswer
	//*�޼ҵ� adminAnswer()
	//*tiles adminQnA->adminQnA.jsp
		    
@RequestMapping(value="/adminAnswerDelete")
public String adminAnswerDelete(CommandMap commandMap, Model model, HttpServletRequest request)throws Exception{
	adminQnAService.qnaDelete(commandMap.getMap());
	return "adminQnA";
		    	
		    }
	
	
	//Q&A ����
	//*url mapping=/adminAnswerDelete
	//*�޼ҵ� adminAnswerDelete()
	//*tiles adminQnA ->adminQnA.jsp
	}

