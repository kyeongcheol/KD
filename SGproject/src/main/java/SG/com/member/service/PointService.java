package SG.com.member.service;

import java.util.List;
import java.util.Map;

import SG.com.common.CommandMap;

public interface PointService {
	
	//���Խ� ����Ʈ ����
	public void joinPoint(Map<String, Object> map) throws Exception;
	   
	//���Ž� ����Ʈ ����
	public void orderPoint(Map<String, Object>map) throws Exception ;
	
    //����Ʈ ���
	public void usePoint(Map<String, Object>map) throws Exception;
	
	//����Ʈ ���� ����
	public void savePoint(Map<String, Object>map) throws Exception;
	   
	//ȸ���� ����Ʈ �� �հ�
	public Map<String, Object> sumPoint(Map<String, Object> map) throws Exception ;
			
	//ȸ���� ����Ʈ ���� ��ȸ
	public List<Map<String, Object>> myPointList(Map<String, Object>map) throws Exception ;
		
 

}
