package SG.com.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository("AdminFaqDao")//�����̳ʿ� �ִ� Repository
public class AdminFaqDao extends AbstractDAO {
	
	//��ü ����Ʈ
	@SuppressWarnings("unchecked")//��� ����
	public List<Map<String,Object>> faqList(Map<String,Object>map)throws Exception{
		return (List<Map<String,Object>>) selectList("faqboard.faqList", map);
	}
	//FAQ�󼼺���
	@SuppressWarnings("unchecked")
	public Map<String,Object> faqDetail(Map<String,Object>map)throws Exception{
		return(Map<String,Object>) selectOne("faqboard.faqDetail",map);
		
	}
	
	//FAQ�˻�(����)=0
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> faqSearch0(Map<String,Object>map, String isSearch){
		return (List<Map<String,Object>>) faqSearch0("faqboard.faqSearch0",map, isSearch);
		
	}
	//FAQ�˻�(����)=1
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> faqSearch1(Map<String,Object>map, String isSearch){
		return (List<Map<String,Object>>) faqSearch1("faqboard.faqSearch1",map, isSearch);
		
	}
	//FAQ�˻�(ī�װ�)=2
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> faqSearch2(Map<String,Object>map, String isSearch){
		return (List<Map<String,Object>>) faqSearch2("faqboard.faqSearch2",map, isSearch);
		
	}
	
	//FAQ���
	public void faqWrite(Map<String, Object>map) throws Exception{
		insert("faqboard.faqWrite",map);
	}
	
	//FAQ����	
	public void faqModify(Map<String, Object>map)throws Exception{
		update("faqboard.faqModify",map);
	}
	
	//FAQ��ȸ��
	public void faqUpdateHitCnt(Map<String,Object>map) throws Exception{
		update("faqboard.faqUpdateHitCnt",map);
	}
	//FAQ����
	public void faqDelete(Map<String, Object>map) throws Exception{
		delete("faqboard.faqDelete",map);
	}
			
	

}
