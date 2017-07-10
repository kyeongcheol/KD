package SG.com.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository("loginDAO")
public class LoginDao  extends AbstractDAO  {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	//���̵� ã��
	public String findId(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("login.findId", map);
	}
	
	//��� ã��
	public String findPw(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("login.findPw", map);
	}
	
	//�α��� ���� �ҷ�����
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectId(Map<String, Object> map) throws Exception
	{
	  return (Map<String, Object>)selectOne("login.loginSuccess", map);
	}


}
