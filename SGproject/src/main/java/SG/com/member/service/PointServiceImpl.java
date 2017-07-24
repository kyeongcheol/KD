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
	   
	  //가입시 포인트 적립
	   @Override
	   public void joinPoint(Map<String, Object> map) throws Exception
	   {
		   pointDao.joinPoint(map);
	   }
		   
	   //구매시 포인트 적립
	   @Override
		public void orderPoint(Map<String, Object>map) throws Exception 
	   {
		  pointDao.orderPoint(map);
		}
		
		//포인트 사용
	   @Override
		public void usePoint(Map<String, Object>map) throws Exception
	    {
			pointDao.usePoint(map);
		}
		
	   //포인트 수동 적립
	   @Override
		public void savePoint(Map<String, Object>map) throws Exception
	    {
			pointDao.savePoint(map);
		}
		   
	   //회원별 포인트 총 합계
	   @Override
		public Map<String, Object> sumPoint(Map<String, Object>map) throws Exception
		{
		   return pointDao.sumPoint(map);
		}
		
			
	   //회원별 포인트 내역 조회
	   @Override
		public List<Map<String, Object>> myPointList(Map<String, Object> map) throws Exception 
	   {
		   return pointDao.myPointList(map);
	   }


	   

}
