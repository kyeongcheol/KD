package SG.com.admin.dao;

import java.util.List;//리스트 쓰겠다
import java.util.Map;//맵을쓰겠다

import org.mybatis.spring.SqlSessionTemplate;//스프링 컨테이너를 쓰겠다
import org.springframework.beans.factory.annotation.Autowired;//오토와이어(참조)
import org.springframework.stereotype.Repository;//참조

import SG.com.common.AbstractDAO;

@Repository("adminQnADao")
public class AdminQnADao extends AbstractDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//Q&A목록조회(회원 자신의 Q&A)
	public List<Map<String,Object>> memberQnaList(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.memberQnaList",map);
	}
	//Q&A상세보기
	public Map<String, Object> qnaDetail(Map<String,Object>map) throws Exception{
		return sqlSession.selectOne("qnaboard.qnaDetail",map);
	}
	//Q&A목록조회(관리자)
	public List<Map<String,Object>>adminQnaList(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.adminQnaList",map);
	}
	//Q&A목록조회-아이디(관리자)
	public List<Map<String,Object>>qnaIdSearch(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaIdSearch",map);
	}
	//Q&A목록조회-제목(관리자)
	public List<Map<String,Object>>qnaTitleSearch(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaTitleSearch",map);
	}
	//Q&A목록조회-카테고리(답변대기,답변처리)
	public List<Map<String,Object>>qnaCtgSearch0(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaCtgSearch0",map);
	}
	//Q&A목록조회-카테고리(상품문의,홈페이지이용문의)
	public List<Map<String,Object>>qnaCtgSearch1(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaCtgSearch1",map);
	}
	//Q&A등록(회원)
	public void qes(Map<String,Object>map) throws Exception{
		sqlSession.insert("qnaboard.qes",map);
	}
	//Q&A답변등록(관리자)
	public void ans(Map<String,Object>map)throws Exception{
		sqlSession.update("qnaboard.ans",map);
	}
	//Q&A삭제
	public void qnaDelete(Map<String,Object>map)throws Exception{
		sqlSession.delete("qnaboard.qnaDelete",map);
	}

}
