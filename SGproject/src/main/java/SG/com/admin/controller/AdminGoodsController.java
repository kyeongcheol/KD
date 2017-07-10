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

import SG.com.admin.service.AdminGoodsService;
import SG.com.common.CommandMap;

import SG.com.common.Paging;

@Controller
public class AdminGoodsController {
	
	@Resource(name= "adminGoodsService")
	private AdminGoodsService adminGoodsService;
	
	
	//�˻� ���� ����
	private int searchNum;
	private String isSearch;

	//����¡ ���� ����
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 10;
	private String pagingHtml;
	private Paging page;

	
	//��ǰ����Ʈ(����ǰ)
	@RequestMapping(value="/adminGoodsList")
	public String adminGoodsList(Model model, CommandMap commandMap, HttpServletRequest request) throws Exception{

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) { //currentPage�� null �̰ų� ���� �̰ų� 0 �϶�.
			currentPage = 1;
		} else { //currentPage�� ��ܿ��� ���� �ִٸ� ��ܿ��� ������ ����.
			currentPage = Integer.parseInt(request.getParameter("currentPage")); 
		}
		
		List<Map<String, Object>> goodsList = adminGoodsService.adminGoodsList(commandMap.getMap());
		
		
		if (request.getParameter("isSearch") != null) {
			//isSearch = new String(s.getBytes("iso-8859-1"), "utf-8");
			isSearch=request.getParameter("isSearch");
			searchNum = Integer.parseInt(request.getParameter("searchNum"));

			if (searchNum == 0)// ��ǰ�̸� (�˻�)
				goodsList = adminGoodsService.adminGoodsSearch0(isSearch);
			else if (searchNum == 1)// ��ǰ��ȣ��ȣ (�˻�)
				goodsList = adminGoodsService.adminGoodsSearch1(isSearch);
			else if (searchNum == 2)// ī�װ�
				goodsList = adminGoodsService.adminGoodsSearch2(isSearch);
			else if (searchNum == 3)// �Ǹ� Ȱ��ȭ or ��Ȱ��ȭ ��ǰ ����
				goodsList = adminGoodsService.adminGoodsSearch3(isSearch);
			else if (searchNum == 4)// ��� 0�� ��ǰ
				goodsList = adminGoodsService.adminGoodsSearch4(isSearch);
			else if (searchNum == 5)// �Ǹŷ� ���� ��, ��ȸ�� ���� �� ����
				goodsList = adminGoodsService.adminGoodsSearch5(isSearch);
			else
				goodsList = adminGoodsService.adminGoodsList(commandMap.getMap());
		}	
		
		totalCount = goodsList.size();
		page = new Paging(currentPage, totalCount, blockCount, blockPage, "adminGoodsList");
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;

		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		goodsList = goodsList.subList(page.getStartCount(), lastCount);

		model.addAttribute("isSearch", isSearch);
		model.addAttribute("searchNum", searchNum);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("goodsList", goodsList);	
		model.addAttribute("goodsList",goodsList);
		
		return "admin_goodslist";
	}
	
	
	//��ǰ��� ������ �̵�(����ǰ)
	@RequestMapping(value="/adminGoodsForm")
	public String adminGoodsForm(Model model)throws Exception{
		
		return "admin_goodsForm";
	}
	
	//��ǰ���(����ǰ)
	@RequestMapping(value="/adminGoodsInsert")
	public String adminGoodsInsert(Model model, CommandMap commandmap, HttpServletRequest request) throws Exception{
		
		adminGoodsService.adminGoodsInsert(commandmap.getMap(), request);
		
		return "redirect:/adminGoodsList";
	}
	
	//��ǰ�󼼺���(����ǰ)
	@RequestMapping(value="/adminGoodsDetail")
	public String adminGoodsDetail(Model model,CommandMap commandmap,HttpServletRequest request) throws Exception{
		
		return "admin_goodsDetail";
	}
	
	//��ǰ����(����ǰ)
	@RequestMapping(value="/adminGoodsDelete")
	public String adminGoodsDelete(Model model,CommandMap commandmap,HttpServletRequest request) throws Exception{
		
		return "redirect:/adminGoodsList";
	}
	
	
		////////////////////////////////���� ���� ����///////////////////////////	////
	
	@RequestMapping(value="/adminToppingList")
	public String adminToppingList(Model model,CommandMap commandmap,HttpServletRequest request)throws Exception{
		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) { //currentPage�� null �̰ų� ���� �̰ų� 0 �϶�.
			currentPage = 1;
		} else { //currentPage�� ��ܿ��� ���� �ִٸ� ��ܿ��� ������ ����.
			currentPage = Integer.parseInt(request.getParameter("currentPage")); 
		}
		
		List<Map<String, Object>> toppingList = adminGoodsService.adminToppingList(commandmap.getMap());
		
		
		if (request.getParameter("isSearch") != null) {
			//isSearch = new String(s.getBytes("iso-8859-1"), "utf-8");
			isSearch=request.getParameter("isSearch");
			searchNum = Integer.parseInt(request.getParameter("searchNum"));

			if (searchNum == 0)// ��ǰ�̸� (�˻�)
				toppingList = adminGoodsService.adminGoodsSearch0(isSearch);
			else if (searchNum == 1)// ��ǰ��ȣ��ȣ (�˻�)
				toppingList = adminGoodsService.adminGoodsSearch1(isSearch);
			else if (searchNum == 2)// ī�װ�
				toppingList = adminGoodsService.adminGoodsSearch2(isSearch);
			else if (searchNum == 3)// �Ǹ� Ȱ��ȭ or ��Ȱ��ȭ ��ǰ ����
				toppingList = adminGoodsService.adminGoodsSearch3(isSearch);
			else if (searchNum == 4)// ��� 0�� ��ǰ
				toppingList = adminGoodsService.adminGoodsSearch4(isSearch);
			else if (searchNum == 5)// �Ǹŷ� ���� ��, ��ȸ�� ���� �� ����
				toppingList = adminGoodsService.adminGoodsSearch5(isSearch);
			else
				toppingList = adminGoodsService.adminGoodsList(commandmap.getMap());
		}	
		
		totalCount = toppingList.size();
		page = new Paging(currentPage, totalCount, blockCount, blockPage, "adminToppingList");
		pagingHtml = page.getPagingHtml().toString();

		int lastCount = totalCount;

		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;

		toppingList = toppingList.subList(page.getStartCount(), lastCount);

		model.addAttribute("isSearch", isSearch);
		model.addAttribute("searchNum", searchNum);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pagingHtml", pagingHtml);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("toppingList",toppingList);
		
		return "admin_toppingList";
	}
	
	@RequestMapping(value="/adminToppingForm")
	public String adminToppingForm(Model model) throws Exception{
		
		return "admin_toppingForm";
	}
	
	//���ε��
	@RequestMapping(value="/adminToppingInsert")
	public String adminToppingInsert(Model model, CommandMap commandmap, HttpServletRequest request) throws Exception{
		
		adminGoodsService.adminGoodsInsert(commandmap.getMap(), request);
		
		return "redirect:/adminToppingList";
	}
	
}
