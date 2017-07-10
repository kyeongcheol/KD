package SG.com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
/*import FaqImageUtils;*/
//�̹��� import ��ų Util���� �ȸ���
import SG.com.admin.dao.AdminFaqDao;
import SG.com.admin.service.AdminFaqService;


//�Ʒ��� AdminFaqServiceImp�� ���۷�Ʈ ������ �Ǽ� ��밡���ϸ�
//@Service�� adminFaqService��� �̸����� ����ϰڴٶ�� ���̴�
@Service("adminFaqService")
public class AdminFaqServiceImpl implements AdminFaqService {//�������̽� �޴���
	
/*	@Resource(name="�̹�����ƿ")
	private faqImageUtils faqImageUtils;*/
	
	@Resource(name="AdminFaqDao")
	private AdminFaqDao adminFaqDao;
	
	//FAQ�����ȸ
	@Override	
	public List<Map<String,Object>> faqList(Map<String,Object>map) throws Exception{
		return adminFaqDao.faqList(map);
	}
	
	//FAQ�󼼺���
	@Override
	public Map<String, Object> faqDetail(Map<String,Object>map) throws Exception{
		adminFaqDao.faqUpdateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = adminFaqDao.faqDetail(map);
		resultMap.put("map", tempMap);
		//�󼼺���(�ʿ��ٰ� ���� ����۾�����)
		//�󼼺��Ⱑ �����԰� �����ϱ� �̷��۾����ѵ�?(�����)
		return resultMap;
		
	}
	
	 //FAQ�˻� ����=0
	@Override
	public List<Map<String,Object>> faqSearch0(Map<String,Object>map, String isSearch) throws Exception{
		return adminFaqDao.faqSearch0(map, isSearch);
	}
	//isSearch ������ �޾ƿ÷��� �ϱ⋚���� isSearch�� ����
	
	//FAQ�˻� ����=1
	@Override
	public List<Map<String,Object>> faqSearch1(Map<String,Object>map,String isSearch) throws Exception{
		return adminFaqDao.faqSearch1(map, isSearch);
	}
	
	//FAQ�˻� ī�װ�=2
	@Override
	public List<Map<String,Object>> faqSearch2(Map<String,Object>map,String isSearch) throws Exception{
		return adminFaqDao.faqSearch2(map, isSearch);
	}
	
	//FAQ���
	@Override
	public void faqWrite(Map<String,Object>map, HttpServletRequest request)throws Exception{
		adminFaqDao.faqWrite(map);
	}
	
	//FAQ����
	@Override
	public void faqModify(Map<String,Object>map)throws Exception{
		adminFaqDao.faqModify(map);
	}
	
	//FAQ����
	@Override
	public void faqDelete(Map<String,Object>map)throws Exception{
		adminFaqDao.faqDelete(map);
	}

	@Override
	public void faqUpdateHitCnt(Map<String, Object> map) throws Exception {
		adminFaqDao.faqUpdateHitCnt(map);
		
	}

	
	
	
	

}
	
	