package SG.com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.stereotype.Service;

import SG.com.admin.dao.AdminGoodsDao;
import SG.com.common.GoodsImageUtils;

import org.springframework.beans.factory.annotation.Autowired;//오토와이어(참조)

@Service("adminGoodsService") //해당 클래스를 adminGoodsService로 사용
public class AdminGoodsServiceImpl implements AdminGoodsService{
	
	@Resource(name="adminGoodsDao")
	private AdminGoodsDao adminGoodsDao;
	
	@Resource(name="goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	//상품목록
	@Override	
	public List<Map<String,Object>> adminGoodsList(Map<String,Object>map) throws Exception{
		return adminGoodsDao.adminGoodsList(map);
	}
	
	//상품등록
	@Override
	public void adminGoodsInsert(Map<String,Object>map,HttpServletRequest request) throws Exception{
			
		System.out.println(map +"SG_GOODS : DB작업전");
		
		adminGoodsDao.adminGoodsInsert(map); //이미지파일을 제외한 데이터 입력
		
		System.out.println(map +"SG_GOODS : DB작업후");
		
		map = goodsImageUtils.goodsThumbnail(map,request); //goodsThumbnail메서드를 통해  파일작성 및 파일이름을 가져온 후
		
		adminGoodsDao.goodsThumbnailInsert(map); //Thumbnail 파일명이 저장된 map을 매개인자로 주어 데이터 입력.
		
		System.out.println(map+"썸네일 이미지 등록완료");//콘솔창에 찍어봅시다..
		
		List<Map<String,Object>> goodsImageList = goodsImageUtils.parseInsertFileInfo(map, request);//parseInsertFileInfo메서드를 통해 파일 작성 및 파일이름을 가져온 후 리스트<맵>형태로 리턴
		
		if(goodsImageList.size() > 0 ){
			for(int i = 0; i< goodsImageList.size(); i++){//반복문 실행
				adminGoodsDao.goodsImageInsert(goodsImageList.get(i));//리스트안에 담겨진 map들을 전부 입력
				System.out.println(i+"번째 이미지 입력완료");//콘솔에 찍어보자..
			}
		}
		
	}
	
	// 상품 검색(상품명)
	@Override
	public List<Map<String, Object>> adminGoodsSearch0(String isSearch) throws Exception {

		return adminGoodsDao.adminGoodsSearch0(isSearch);
	}

	// 상품 검색(상품 번호)
	@Override
	public List<Map<String, Object>> adminGoodsSearch1(String isSearch) throws Exception {

		return adminGoodsDao.adminGoodsSearch1(isSearch);
	}

		// 상품 검색(카테고리 검색)
		@Override
		public List<Map<String, Object>> adminGoodsSearch2(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch2(isSearch);
			return goodsList;
		}

		// 상품 검색(판매 On,Off)
		@Override
		public List<Map<String, Object>> adminGoodsSearch3(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch3(isSearch);
			return goodsList;
		}

		// 상품 검색(재고가 0인 상품)
		@Override
		public List<Map<String, Object>> adminGoodsSearch4(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch4(isSearch);
			return goodsList;
		}

		// 상품 정렬(판매량순)
		@Override
		public List<Map<String, Object>> adminGoodsSearch5(String isSearch) throws Exception {
			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch5(isSearch);
			return goodsList;
		}
		
		////////////////////////////////////////토핑////////////////////////////////////////
		
		//토핑 조회 
		@Override
		public List<Map<String,Object>> adminToppingList(Map<String,Object>map) throws Exception{
			List<Map<String,Object>> toppingList = adminGoodsDao.adminToppingList(map);
			return toppingList;
		}
	
}
