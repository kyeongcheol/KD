package SG.com.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;


@Repository("memberDAO")
public class MemberDao extends AbstractDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//ȸ������ ��ȸ
	public Map<String, Object> myinfoDetail(Map<String, Object>map) throws Exception 
	{
		return (Map<String, Object>) selectOne("mypage.myinfoDetail", map);
	}
		
	//ȸ�� ���� ����
	public void updateMyinfo(Map<String, Object>map) 
	{
		sqlSession.update("mypage.updateMyinfo", map);
	}

	// �����ڰ� ȸ�� ���� OFF�� ����(ȸ������ Ż��)
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	   sqlSession.update("mypage.deleteMember", map);
	}
	
	//ȸ���� ���� �� �����ݾ�
	public Map<String, Object> sumTradeMoney(Map<String, Object>map) throws Exception 
	{
		return (Map<String, Object>)selectOne("mypage.sumTradeMoney", map);
	}
	
	

}