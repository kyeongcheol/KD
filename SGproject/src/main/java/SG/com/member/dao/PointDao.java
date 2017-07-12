package SG.com.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository("pointDAO")
public class PointDao extends AbstractDAO 
{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//회원가입시 포인트 적립
	public void joinPoint(Map<String, Object> map) throws Exception
	{
	  sqlSession.insert("point.joinPoint", map);
	}
	
	//구매시 포인트 적립
	public void orderPoint(Map<String, Object>map) throws Exception 
	{
	  sqlSession.insert("point,orderPoint", map);
	}
	
	//포인트 사용
	public void usePoint(Map<String, Object>map) throws Exception 
	{
		sqlSession.insert("point.usePoint", map);
	}
	
	//포인트 수동 적립
	public void savePoint(Map<String, Object>map) throws Exception 
	{
		sqlSession.insert("point.savaPoint", map);
	}
	
	//회원별 포인트 총 합계
	public Map<String, Object> sumPoint(Map<String, Object> map) throws Exception 
	{
		return (Map<String, Object>)selectOne("point.sumPoint", map);
	}
	
	//회원별 포인트 내역 조회
	public List<Map<String, Object>> myPointList(Map<String, Object>map) throws Exception 
	{
		return sqlSession.selectList("point.myPointList", map);
	}




}
