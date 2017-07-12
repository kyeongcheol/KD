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
	
	//ȸ�����Խ� ����Ʈ ����
	public void joinPoint(Map<String, Object> map) throws Exception
	{
	  sqlSession.insert("point.joinPoint", map);
	}
	
	//���Ž� ����Ʈ ����
	public void orderPoint(Map<String, Object>map) throws Exception 
	{
	  sqlSession.insert("point,orderPoint", map);
	}
	
	//����Ʈ ���
	public void usePoint(Map<String, Object>map) throws Exception 
	{
		sqlSession.insert("point.usePoint", map);
	}
	
	//����Ʈ ���� ����
	public void savePoint(Map<String, Object>map) throws Exception 
	{
		sqlSession.insert("point.savaPoint", map);
	}
	
	//ȸ���� ����Ʈ �� �հ�
	public Map<String, Object> sumPoint(Map<String, Object> map) throws Exception 
	{
		return (Map<String, Object>)selectOne("point.sumPoint", map);
	}
	
	//ȸ���� ����Ʈ ���� ��ȸ
	public List<Map<String, Object>> myPointList(Map<String, Object>map) throws Exception 
	{
		return sqlSession.selectList("point.myPointList", map);
	}




}
