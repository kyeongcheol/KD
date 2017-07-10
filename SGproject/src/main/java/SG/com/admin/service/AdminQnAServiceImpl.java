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
	
    //Q&A목록조회(회원 자신의 Q&A)
	 public List<Map<String,Object>> memberQnaList(Map<String, Object>map)throws Exception{
		 return adminQnADao.memberQnaList(map);
	 }
	
	 //Q&A상세보기
	 public Map<String, Object> qnaDetail(Map<String,Object>map) throws Exception{
		 return adminQnADao.qnaDetail(map);
	 }
	 
	//Q&A목록조회(관리자)
   public List<Map<String,Object>>adminQnaList(Map<String, Object>map)throws Exception{
	   return adminQnADao.adminQnaList(map);
   }
		
	//Q&A목록조회-아이디(관리자)
	public List<Map<String,Object>>qnaIdSearch(Map<String, Object>map)throws Exception{
      return adminQnADao.qnaIdSearch(map);
	}
	
	//Q&A목록조회-제목(관리자)
	public List<Map<String,Object>>qnaTitleSearch(Map<String,Object>map)throws Exception{
		return adminQnADao.qnaTitleSearch(map);
	 }
		
	//Q&A목록조회-카테고리(답변대기,답변처리)
	public List<Map<String,Object>>qnaCtgSearch0(Map<String,Object>map)throws Exception{
		return adminQnADao.qnaCtgSearch0(map);
	}
		
	//Q&A목록조회-카테고리(상품문의,홈페이지이용문의)
	 public List<Map<String,Object>>qnaCtgSearch1(Map<String,Object>map)throws Exception{
		 return adminQnADao.qnaCtgSearch1(map);
	 }
		
	//Q&A등록(회원)
	public void qes(Map<String,Object>map) throws Exception{
		adminQnADao.qes(map);
	}

	//Q&A답변등록(관리자)
	public void ans(Map<String,Object>map)throws Exception{
		adminQnADao.ans(map);
	}
		
	//Q&A삭제
	 public void qnaDelete(Map<String,Object>map)throws Exception{
		 adminQnADao.qnaDelete(map);
	 }
		
	}


