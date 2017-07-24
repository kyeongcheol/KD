package SG.com.member.service;

import java.util.List;
import java.util.Map;

import SG.com.common.CommandMap;

public interface PointService {
	
	//가입시 포인트 적립
	public void joinPoint(Map<String, Object> map) throws Exception;
	   
	//구매시 포인트 적립
	public void orderPoint(Map<String, Object>map) throws Exception ;
	
    //포인트 사용
	public void usePoint(Map<String, Object>map) throws Exception;
	
	//포인트 수동 적립
	public void savePoint(Map<String, Object>map) throws Exception;
	   
	//회원별 포인트 총 합계
	public Map<String, Object> sumPoint(Map<String, Object> map) throws Exception ;
			
	//회원별 포인트 내역 조회
	public List<Map<String, Object>> myPointList(Map<String, Object>map) throws Exception ;
		
 

}
