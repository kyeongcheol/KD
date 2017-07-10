package SG.com.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminFaqService {//�������̽� �޴���
	
	//FAQ�����ȸ
	List<Map<String,Object>> faqList(Map<String,Object>map) throws Exception;
	
	//FAQ�󼼺���
	Map<String, Object> faqDetail(Map<String,Object>map) throws Exception;
	
	//FAQ�˻� ����=0
	List<Map<String,Object>> faqSearch0(Map<String,Object>map,String isSearch) throws Exception;
	//isSearch ������ �޾ƿ÷��� �ϱ⋚���� isSearch�� ����
	
	//FAQ�˻� ����=1
	List<Map<String,Object>> faqSearch1(Map<String,Object>map,String isSearch) throws Exception;
	
	//FAQ�˻� ī�װ�=2
	List<Map<String,Object>> faqSearch2(Map<String,Object>map,String isSearch) throws Exception;
	
	//FAQ���
	void faqWrite(Map<String,Object>map, HttpServletRequest request)throws Exception;
	
	//FAQ����
	void faqModify(Map<String,Object>map)throws Exception;
	
	//FAQ����
	void faqDelete(Map<String,Object>map)throws Exception;
	
	//FAQ��ȸ�� ������Ʈ
	void faqUpdateHitCnt(Map<String,Object>map) throws Exception;
}
	
	