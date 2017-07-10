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
	
	//��ǰ ��ȸ
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> adminGoodsList(Map<String,Object>map)throws Exception{
		return (List<Map<String,Object>>) selectList("adgoods.adgoodsList", map);
	}
	
	//�̹����� ������ ��ǰ���
	public void adminGoodsInsert(Map<String,Object>map)throws Exception{
		insert("adgoods.insertAdminGoods", map);
		
	}
	//��ǰ ����� �̹��� ���
	public void goodsThumbnailInsert(Map<String,Object>map)throws Exception{
		update("adgoods.updateGoodsThumbnail",map);
	}
	
	//��ǰ �̹��� ���
	public void goodsImageInsert(Map<String,Object>map)throws Exception{
		insert("adgoods.insertGoodsImage",map);
	}
	
	//���� ��ȸ
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> adminToppingList(Map<String,Object>map)throws Exception{
		return (List<Map<String,Object>>) selectList("adgoods.adToppingList", map);
	}
	
	
	
	// ��ǰ �˻�(��ǰ �̸�)
	public List<Map<String, Object>> adminGoodsSearch0(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch0",isSearch);
	}

	// ��ǰ �˻�(��ǰ ��ȣ)
	public List<Map<String, Object>> adminGoodsSearch1(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch1",isSearch);
	}

	// ��ǰ �˻�(ī�װ� �˻�)
	public List<Map<String, Object>> adminGoodsSearch2(String isSearch) throws Exception {
		return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch2",isSearch);
	}

		// ��ǰ �˻�(�Ǹ�On,Off)
		public List<Map<String, Object>> adminGoodsSearch3(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch3",isSearch);
		}

		// ��ǰ �˻�(��� 0�� ��ǰ)
		public List<Map<String, Object>> adminGoodsSearch4(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch4",isSearch);
		}

		// ��ǰ �˻�(�Ǹŷ� ���� ��, ��ȸ�� ���� ��)
		public List<Map<String, Object>> adminGoodsSearch5(String isSearch) throws Exception {
			return (List<Map<String,Object>>)selectList("adgoods.adgoodsListSearch5",isSearch);
		}
}
