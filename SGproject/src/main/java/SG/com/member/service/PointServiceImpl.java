package SG.com.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import SG.com.member.dao.PointDao;

@Service("pointService")
public class PointServiceImpl implements PointService 
{
	
	  
	   @Resource(name="pointDAO")
	   private PointDao pointDao;
	   
	  //���Խ� ����Ʈ ����
	   @Override
	   public void joinPoint(Map<String, Object> map) throws Exception
	   {
		   pointDao.joinPoint(map);
	   }
		   
	   //���Ž� ����Ʈ ����
	   @Override
		public void orderPoint(Map<String, Object>map) throws Exception 
	   {
		  pointDao.orderPoint(map);
		}
		
		//����Ʈ ���
	   @Override
		public void usePoint(Map<String, Object>map) throws Exception
	    {
			pointDao.usePoint(map);
		}
		
	   //����Ʈ ���� ����
	   @Override
		public void savePoint(Map<String, Object>map) throws Exception
	    {
			pointDao.savePoint(map);
		}
		   
	   //ȸ���� ����Ʈ �� �հ�
	   @Override
		public Map<String, Object> sumPoint(Map<String, Object>map) throws Exception
		{
		   return pointDao.sumPoint(map);
		}
		
			
	   //ȸ���� ����Ʈ ���� ��ȸ
	   @Override
		public List<Map<String, Object>> myPointList(Map<String, Object> map) throws Exception 
	   {
		   return pointDao.myPointList(map);
	   }
	   
	    //�ֹ� �󼼺��� �� ����� ����Ʈ ���� ��ȸ
	   @Override
	    public int orderUsePoint(Map<String, Object>map) throws Exception
	   {
		  return pointDao.orderUsePoint(map);
	   }
	    
	    //�ֹ� ��ҽ� ����Ʈ�� ����
	   @Override
	   public void orderPointDel(Map<String, Object>map) throws Exception
	   {
		   pointDao.orderPointDel(map);
	   }

}
