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
	
	//회원정보 조회
	public Map<String, Object> myinfoDetail(Map<String, Object>map) throws Exception 
	{
		return (Map<String, Object>) selectOne("mypage.myinfoDetail", map);
	}
		
	//회원 정보 수정
	public void updateMyinfo(Map<String, Object>map) 
	{
		sqlSession.update("mypage.updateMyinfo", map);
	}

	// 관리자가 회원 정보 OFF로 수정(회원강제 탈퇴)
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	   sqlSession.update("mypage.deleteMember", map);
	}

}