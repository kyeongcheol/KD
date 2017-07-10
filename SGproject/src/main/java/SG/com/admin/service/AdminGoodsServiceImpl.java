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

import org.springframework.beans.factory.annotation.Autowired;//������̾�(����)

@Service("adminGoodsService") //�ش� Ŭ������ adminGoodsService�� ���
public class AdminGoodsServiceImpl implements AdminGoodsService{
	
	@Resource(name="adminGoodsDao")
	private AdminGoodsDao adminGoodsDao;
	
	@Resource(name="goodsImageUtils")
	private GoodsImageUtils goodsImageUtils;
	
	//��ǰ���
	@Override	
	public List<Map<String,Object>> adminGoodsList(Map<String,Object>map) throws Exception{
		return adminGoodsDao.adminGoodsList(map);
	}
	
	//��ǰ���
	@Override
	public void adminGoodsInsert(Map<String,Object>map,HttpServletRequest request) throws Exception{
			
		System.out.println(map +"SG_GOODS : DB�۾���");
		
		adminGoodsDao.adminGoodsInsert(map); //�̹��������� ������ ������ �Է�
		
		System.out.println(map +"SG_GOODS : DB�۾���");
		
		map = goodsImageUtils.goodsThumbnail(map,request); //goodsThumbnail�޼��带 ����  �����ۼ� �� �����̸��� ������ ��
		
		adminGoodsDao.goodsThumbnailInsert(map); //Thumbnail ���ϸ��� ����� map�� �Ű����ڷ� �־� ������ �Է�.
		
		System.out.println(map+"����� �̹��� ��ϿϷ�");//�ܼ�â�� ���ô�..
		
		List<Map<String,Object>> goodsImageList = goodsImageUtils.parseInsertFileInfo(map, request);//parseInsertFileInfo�޼��带 ���� ���� �ۼ� �� �����̸��� ������ �� ����Ʈ<��>���·� ����
		
		if(goodsImageList.size() > 0 ){
			for(int i = 0; i< goodsImageList.size(); i++){//�ݺ��� ����
				adminGoodsDao.goodsImageInsert(goodsImageList.get(i));//����Ʈ�ȿ� ����� map���� ���� �Է�
				System.out.println(i+"��° �̹��� �Է¿Ϸ�");//�ֿܼ� ����..
			}
		}
		
	}
	
	// ��ǰ �˻�(��ǰ��)
	@Override
	public List<Map<String, Object>> adminGoodsSearch0(String isSearch) throws Exception {

		return adminGoodsDao.adminGoodsSearch0(isSearch);
	}

	// ��ǰ �˻�(��ǰ ��ȣ)
	@Override
	public List<Map<String, Object>> adminGoodsSearch1(String isSearch) throws Exception {

		return adminGoodsDao.adminGoodsSearch1(isSearch);
	}

		// ��ǰ �˻�(ī�װ� �˻�)
		@Override
		public List<Map<String, Object>> adminGoodsSearch2(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch2(isSearch);
			return goodsList;
		}

		// ��ǰ �˻�(�Ǹ� On,Off)
		@Override
		public List<Map<String, Object>> adminGoodsSearch3(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch3(isSearch);
			return goodsList;
		}

		// ��ǰ �˻�(��� 0�� ��ǰ)
		@Override
		public List<Map<String, Object>> adminGoodsSearch4(String isSearch) throws Exception {

			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch4(isSearch);
			return goodsList;
		}

		// ��ǰ ����(�Ǹŷ���)
		@Override
		public List<Map<String, Object>> adminGoodsSearch5(String isSearch) throws Exception {
			List<Map<String, Object>> goodsList = adminGoodsDao.adminGoodsSearch5(isSearch);
			return goodsList;
		}
		
		////////////////////////////////////////����////////////////////////////////////////
		
		//���� ��ȸ 
		@Override
		public List<Map<String,Object>> adminToppingList(Map<String,Object>map) throws Exception{
			List<Map<String,Object>> toppingList = adminGoodsDao.adminToppingList(map);
			return toppingList;
		}
	
}
