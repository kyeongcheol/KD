package SG.com.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminQnAService {
	
	     //Q&A�����ȸ(ȸ�� �ڽ��� Q&A)
		 List<Map<String,Object>> memberQnaList(Map<String, Object>map)throws Exception;
		
		 //Q&A�󼼺���
		 Map<String, Object> qnaDetail(Map<String,Object>map) throws Exception;
		 
		//Q&A�����ȸ(������)
	    List<Map<String,Object>>adminQnaList(Map<String, Object>map)throws Exception;
			
		//Q&A�����ȸ-���̵�(������)
		List<Map<String,Object>>qnaIdSearch(Map<String, Object>map)throws Exception;
		
		//Q&A�����ȸ-����(������)
		 List<Map<String,Object>>qnaTitleSearch(Map<String,Object>map)throws Exception;
			
		//Q&A�����ȸ-ī�װ�(�亯���,�亯ó��)
		 List<Map<String,Object>>qnaCtgSearch0(Map<String,Object>map)throws Exception;
			
		//Q&A�����ȸ-ī�װ�(��ǰ����,Ȩ�������̿빮��)
		 List<Map<String,Object>>qnaCtgSearch1(Map<String,Object>map)throws Exception;
			
		//Q&A���(ȸ��)
		 void qes(Map<String,Object>map) throws Exception;
	
		//Q&A�亯���(������)
		 void ans(Map<String,Object>map)throws Exception;
			
		//Q&A����
		 void qnaDelete(Map<String,Object>map)throws Exception;
			
		}



