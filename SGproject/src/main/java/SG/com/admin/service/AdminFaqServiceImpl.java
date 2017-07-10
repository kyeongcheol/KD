package SG.com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
/*import FaqImageUtils;*/
//이미지 import 시킬 Util아직 안만듬
import SG.com.admin.dao.AdminFaqDao;
import SG.com.admin.service.AdminFaqService;


//아래에 AdminFaqServiceImp이 컴퍼런트 스켄이 되서 사용가능하면
//@Service를 adminFaqService라는 이름으로 사용하겠다라는 것이다
@Service("adminFaqService")
public class AdminFaqServiceImpl implements AdminFaqService {//인터페이스 메뉴판
	
/*	@Resource(name="이미지유틸")
	private faqImageUtils faqImageUtils;*/
	
	@Resource(name="AdminFaqDao")
	private AdminFaqDao adminFaqDao;
	
	//FAQ목록조회
	@Override	
	public List<Map<String,Object>> faqList(Map<String,Object>map) throws Exception{
		return adminFaqDao.faqList(map);
	}
	
	//FAQ상세보기
	@Override
	public Map<String, Object> faqDetail(Map<String,Object>map) throws Exception{
		adminFaqDao.faqUpdateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = adminFaqDao.faqDetail(map);
		resultMap.put("map", tempMap);
		//상세보기(맵에다가 맵을 담는작업을함)
		//상세보기가 여러게가 있으니깐 이런작업을한듯?(물어보기)
		return resultMap;
		
	}
	
	 //FAQ검색 제목=0
	@Override
	public List<Map<String,Object>> faqSearch0(Map<String,Object>map, String isSearch) throws Exception{
		return adminFaqDao.faqSearch0(map, isSearch);
	}
	//isSearch 값으로 받아올려고 하기떄문에 isSearch를 설정
	
	//FAQ검색 내용=1
	@Override
	public List<Map<String,Object>> faqSearch1(Map<String,Object>map,String isSearch) throws Exception{
		return adminFaqDao.faqSearch1(map, isSearch);
	}
	
	//FAQ검색 카테고리=2
	@Override
	public List<Map<String,Object>> faqSearch2(Map<String,Object>map,String isSearch) throws Exception{
		return adminFaqDao.faqSearch2(map, isSearch);
	}
	
	//FAQ등록
	@Override
	public void faqWrite(Map<String,Object>map, HttpServletRequest request)throws Exception{
		adminFaqDao.faqWrite(map);
	}
	
	//FAQ수정
	@Override
	public void faqModify(Map<String,Object>map)throws Exception{
		adminFaqDao.faqModify(map);
	}
	
	//FAQ삭제
	@Override
	public void faqDelete(Map<String,Object>map)throws Exception{
		adminFaqDao.faqDelete(map);
	}

	@Override
	public void faqUpdateHitCnt(Map<String, Object> map) throws Exception {
		adminFaqDao.faqUpdateHitCnt(map);
		
	}

	
	
	
	

}
	
	