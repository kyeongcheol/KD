package SG.com.admin.dao;

import java.util.List;//����Ʈ ���ڴ�
import java.util.Map;//�������ڴ�

import org.mybatis.spring.SqlSessionTemplate;//������ �����̳ʸ� ���ڴ�
import org.springframework.beans.factory.annotation.Autowired;//������̾�(����)
import org.springframework.stereotype.Repository;//����

import SG.com.common.AbstractDAO;

@Repository("adminQnADao")
public class AdminQnADao extends AbstractDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//Q&A�����ȸ(ȸ�� �ڽ��� Q&A)
	public List<Map<String,Object>> memberQnaList(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.memberQnaList",map);
	}
	//Q&A�󼼺���
	public Map<String, Object> qnaDetail(Map<String,Object>map) throws Exception{
		return sqlSession.selectOne("qnaboard.qnaDetail",map);
	}
	//Q&A�����ȸ(������)
	public List<Map<String,Object>>adminQnaList(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.adminQnaList",map);
	}
	//Q&A�����ȸ-���̵�(������)
	public List<Map<String,Object>>qnaIdSearch(Map<String, Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaIdSearch",map);
	}
	//Q&A�����ȸ-����(������)
	public List<Map<String,Object>>qnaTitleSearch(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaTitleSearch",map);
	}
	//Q&A�����ȸ-ī�װ�(�亯���,�亯ó��)
	public List<Map<String,Object>>qnaCtgSearch0(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaCtgSearch0",map);
	}
	//Q&A�����ȸ-ī�װ�(��ǰ����,Ȩ�������̿빮��)
	public List<Map<String,Object>>qnaCtgSearch1(Map<String,Object>map)throws Exception{
		return sqlSession.selectList("qnaboard.qnaCtgSearch1",map);
	}
	//Q&A���(ȸ��)
	public void qes(Map<String,Object>map) throws Exception{
		sqlSession.insert("qnaboard.qes",map);
	}
	//Q&A�亯���(������)
	public void ans(Map<String,Object>map)throws Exception{
		sqlSession.update("qnaboard.ans",map);
	}
	//Q&A����
	public void qnaDelete(Map<String,Object>map)throws Exception{
		sqlSession.delete("qnaboard.qnaDelete",map);
	}

}
