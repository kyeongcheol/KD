package SG.com.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;


@Repository("memberDAO")
public class MemberDao extends AbstractDAO 
{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//ȸ������ ��ȸ
	@SuppressWarnings("unchecked")
	public Map<String, Object> myinfoDetail(Map<String, Object>map) throws Exception 
	{
		return (Map<String, Object>) selectOne("mypage.myinfoDetail", map);
	}
		
	//ȸ�� ���� ����
	public void updateMyinfo(Map<String, Object>map) 
	{
		update("mypage.updateMyinfo", map);
	}

	// �����ڰ� ȸ�� ���� OFF�� ����(ȸ������ Ż��)
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	    update("mypage.deleteMember", map);
	}
	
	//ȸ���� ���� �� �����ݾ�
	public int mysumTradeMoney(Map<String, Object>map) throws Exception 
	{
		return (int)selectOne("mypage.mysumTradeMoney", map);
	}
	
	//���� �ֹ����� ����Ʈ ����
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrederList(Map<String, Object>map) throws Exception 
	{
		return (List<Map<String, Object>>)selectList("mypage.myOrderList", map);
	}

	//���� �ֹ����� �󼼺���
	@SuppressWarnings("unchecked")
	public Map<String, Object> myOrderDetail(Map<String, Object>map) throws Exception 
	{
	    return (Map<String, Object>)selectOne("mypage.myOrderDetail", map);
	}
		
    //�ֹ����� �󼼺��� ����(�ֹ���������, �Ա����϶�)
    public void myOrderUpdate(Map<String, Object>map) throws Exception 
    {
	    sqlSession.update("order.myOrderUpdate", map);
	}
	
    //�ֹ����� �󼼺��� ����(�����������, �Ա���,����غ����϶�)
	public void myDeliUpdate(Map<String, Object>map) throws Exception  
	{
	    sqlSession.update("deli.myDeliUpdate", map);
	}
	
	//��۹�ȣ �� �� �հ�ݾ�
	public int sumTradeMoney(Map<String, Object>map) throws Exception 
	{
	   return (int)selectOne("order.sumTradeMoney", map);
	}
	
	//�ֹ����� (�Ա����϶�)
	public void orderDelete(Map<String, Object>map) throws Exception  
	{
	   sqlSession.delete("order.orderDelete", map);
	}
	
	

}