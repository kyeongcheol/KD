package SG.com.admin.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import SG.com.common.AbstractDAO;

@Repository("adminGoodsDao")
public class AdminGoodsDao extends AbstractDAO{
	
	//상품 조회
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> adminGoodsList(Map<String,Object>map)throws Exception{
		return (List<Map<String,Object>>) selectList("adgoods.adgoodsList", map);
	}
	
	//이미지를 제외한 상품등록
	public void adminGoodsInsert(Map<String,Object>map)throws Exception{
		insert("adgoods.insertAdminGoods", map);
		
	}
	//상품 썸네일 이미지 등록
	public void goodsThumbnailInsert(Map<String,Object>map)throws Exception{
		update("adgoods.updateGoodsThumbnail",map);
	}
	
	//상품 이미지 등록
	public void goodsImageInsert(Map<String,Object>map)throws Exception{
		insert("adgoods.insertGoodsImage",map);
	}
	
	//토핑 조회
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> adminToppingList(Map<String,Object>map)throws Exception{
		return (List<Map<String,Object>>) selectList("adgoods.adToppingList", map);
	}
	
	
	
	// 상품 검색(상품 이름)
	public List<Map<String, Object>> adminGoodsSearch0(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch0",isSearch);
	}

	// 상품 검색(상품 번호)
	public List<Map<String, Object>> adminGoodsSearch1(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch1",isSearch);
	}

	// 상품 검색(카테고리 검색)
	public List<Map<String, Object>> adminGoodsSearch2(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch2",isSearch);
	}

		// 상품 검색(판매On,Off)
		public List<Map<String, Object>> adminGoodsSearch3(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch3",isSearch);
		}

		// 상품 검색(재고가 0인 상품)
		public List<Map<String, Object>> adminGoodsSearch4(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch4",isSearch);
		}

		// 상품 검색(판매량 많은 순, 조회수 많은 순)
		public List<Map<String, Object>> adminGoodsSearch5(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch5",isSearch);
		}
}
