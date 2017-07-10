package SG.com.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminFaqService {//인터페이스 메뉴판
	
	//FAQ목록조회
	List<Map<String,Object>> faqList(Map<String,Object>map) throws Exception;
	
	//FAQ상세보기
	Map<String, Object> faqDetail(Map<String,Object>map) throws Exception;
	
	//FAQ검색 제목=0
	List<Map<String,Object>> faqSearch0(Map<String,Object>map,String isSearch) throws Exception;
	//isSearch 값으로 받아올려고 하기떄문에 isSearch를 설정
	
	//FAQ검색 내용=1
	List<Map<String,Object>> faqSearch1(Map<String,Object>map,String isSearch) throws Exception;
	
	//FAQ검색 카테고리=2
	List<Map<String,Object>> faqSearch2(Map<String,Object>map,String isSearch) throws Exception;
	
	//FAQ등록
	void faqWrite(Map<String,Object>map, HttpServletRequest request)throws Exception;
	
	//FAQ수정
	void faqModify(Map<String,Object>map)throws Exception;
	
	//FAQ삭제
	void faqDelete(Map<String,Object>map)throws Exception;
	
	//FAQ조회수 업데이트
	void faqUpdateHitCnt(Map<String,Object>map) throws Exception;
}
	
	