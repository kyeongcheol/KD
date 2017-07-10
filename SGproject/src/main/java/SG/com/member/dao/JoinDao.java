package SG.com.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;


@Repository("joinDAO")
public class JoinDao extends AbstractDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	   //ȸ������
		public void insertMember(Map<String, Object> map) throws Exception{
			sqlSession.insert("join.insertMember", map);
		}
		
		//�̸��� �ߺ�Ȯ��
		public int checkEmail(Map<String, Object> map) throws Exception{
			return (int)selectOne("join.checkEmail", map);
		}
		
		//���̵� �ߺ� Ȯ��
		public int checkId(Map<String, Object> map) throws Exception{
			return (int)selectOne("join.checkId", map);
		}
		

}

