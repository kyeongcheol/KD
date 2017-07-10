package SG.com.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import SG.com.admin.dao.AdminQnADao;



@Service("adminQnAService")
public class AdminQnAServiceImpl implements AdminQnAService {
	
	@Resource(name="adminQnADao")
	private AdminQnADao adminQnADao;
	
    //Q&A�����ȸ(ȸ�� �ڽ��� Q&A)
	 public List<Map<String,Object>> memberQnaList(Map<String, Object>map)throws Exception{
		 return adminQnADao.memberQnaList(map);
	 }
	
	 //Q&A�󼼺���
	 public Map<String, Object> qnaDetail(Map<String,Object>map) throws Exception{
		 return adminQnADao.qnaDetail(map);
	 }
	 
	//Q&A�����ȸ(������)
   public List<Map<String,Object>>adminQnaList(Map<String, Object>map)throws Exception{
	   return adminQnADao.adminQnaList(map);
   }
		
	//Q&A�����ȸ-���̵�(������)
	public List<Map<String,Object>>qnaIdSearch(Map<String, Object>map)throws Exception{
      return adminQnADao.qnaIdSearch(map);
	}
	
	//Q&A�����ȸ-����(������)
	public List<Map<String,Object>>qnaTitleSearch(Map<String,Object>map)throws Exception{
		return adminQnADao.qnaTitleSearch(map);
	 }
		
	//Q&A�����ȸ-ī�װ�(�亯���,�亯ó��)
	public List<Map<String,Object>>qnaCtgSearch0(Map<String,Object>map)throws Exception{
		return adminQnADao.qnaCtgSearch0(map);
	}
		
	//Q&A�����ȸ-ī�װ�(��ǰ����,Ȩ�������̿빮��)
	 public List<Map<String,Object>>qnaCtgSearch1(Map<String,Object>map)throws Exception{
		 return adminQnADao.qnaCtgSearch1(map);
	 }
		
	//Q&A���(ȸ��)
	public void qes(Map<String,Object>map) throws Exception{
		adminQnADao.qes(map);
	}

	//Q&A�亯���(������)
	public void ans(Map<String,Object>map)throws Exception{
		adminQnADao.ans(map);
	}
		
	//Q&A����
	 public void qnaDelete(Map<String,Object>map)throws Exception{
		 adminQnADao.qnaDelete(map);
	 }
		
	}


