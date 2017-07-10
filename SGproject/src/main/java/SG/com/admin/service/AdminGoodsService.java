package SG.com.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminGoodsService {
	
	
		//��ǰ���(����ǰ)
		List<Map<String,Object>> adminGoodsList(Map<String,Object>map) throws Exception;

		// ��ǰ �˻�(��ǰ �̸�)
		public List<Map<String, Object>> adminGoodsSearch0(String isSearch) throws Exception;

		// ��ǰ �˻�(��ǰ ��ȣ)
		public List<Map<String, Object>> adminGoodsSearch1(String isSearch) throws Exception;

		// ��ǰ �˻�(ī�װ� �˻�)
		public List<Map<String, Object>> adminGoodsSearch2(String isSearch) throws Exception;

		// ��ǰ �˻�(�Ǹ� ON or OFF)
		public List<Map<String, Object>> adminGoodsSearch3(String isSearch) throws Exception;

		// ��ǰ �˻�(��� 0�� ��ǰ)
		public List<Map<String, Object>> adminGoodsSearch4(String isSearch) throws Exception;
				
		// ��ǰ ����(�Ǹŷ���)
		public List<Map<String, Object>> adminGoodsSearch5(String isSearch) throws Exception;
		
		//��ǰ ���
		public void adminGoodsInsert(Map<String,Object>map, HttpServletRequest request) throws Exception;
		/*// ��ǰ ���� ������ �̵�
		public List<Map<String, Object>> goodsModifyForm(Map<String, Object> map) throws Exception;
		
		public List<Map<String, Object>> goodsModifyFormImage(Map<String, Object> map) throws Exception;
		// ��ǰ ����
		public void goodsModify(Map<String, Object> map, HttpServletRequest request) throws Exception;

		// ��ǰ ����
		public void goodsDelete(Map<String, Object> map) throws Exception;
*/	
		
		//���� ��ȸ
		public List<Map<String,Object>> adminToppingList(Map<String,Object>map)throws Exception;
		
		//���� ���
		//public void adminToppingInsert(Map<String,Object>map,HttpServletRequest request) throws Exception;
	
}
