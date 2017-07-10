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
	
	   //회원가입
		public void insertMember(Map<String, Object> map) throws Exception{
			sqlSession.insert("join.insertMember", map);
		}
		
		//이메일 중복확인
		public int checkEmail(Map<String, Object> map) throws Exception{
			return (int)selectOne("join.checkEmail", map);
		}
		
		//아이디 중복 확인
		public int checkId(Map<String, Object> map) throws Exception{
			return (int)selectOne("join.checkId", map);
		}
		

}

