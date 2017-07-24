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
	
	//-------------------ȸ������---------------------------
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
	
	//-------------------�ֹ�����---------------------------
	
	//��۹�ȣ �� �ֹ����� �Ǽ� ��ȸ
	public int orderdelicnt(Map<String, Object>map) throws Exception 
	{
	   return (int)selectOne("mypage.orderdelicnt", map);
    }
	
	//���� �ֹ����� ���� ��ȸ
	public int myOrderCnt(Map<String, Object>map) throws Exception 
    {
	   return (int)selectOne("mypage.myOrderCnt", map);
	}
	
	//���� �ֹ����� ����Ʈ ����
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) throws Exception 
	{
		return (List<Map<String, Object>>)selectList("mypage.myOrderList", map);
	}

	//���� �ֹ����� �󼼺���
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrderDetail(Map<String, Object> map) throws Exception 
	{
	    return (List<Map<String, Object>>)selectList("mypage.myOrderDetail", map);
	}
		
    //�ֹ����� �󼼺��� ����(�ֹ� ���� ����, �Ա����϶�)
    public void myOrderUpdate(Map<String, Object> map) throws Exception 
    {
	    sqlSession.update("mypage.myOrderUpdate", map);
	}
	
    //�ֹ����� �󼼺��� ����(�����������, �Ա���,����غ����϶�)
	public void myDeliUpdate(Map<String, Object> map) throws Exception  
	{
	    sqlSession.update("mypage.myDeliUpdate", map);
	}
	
	//�ֹ����� (�Ա����϶�)
	public void orderDelete(Map<String, Object> map) throws Exception  
	{
	   sqlSession.delete("mypage.orderDelete", map);
	}
	
	//-------------------�ֹ����(����غ� ���� ��)---------------------------
	
	//�ֹ����� (�Ա����϶�)
	public void delinodel(Map<String, Object> map) throws Exception  
	{
	    sqlSession.delete("mypage.delinodel", map);
	}
		
	//���� ���� ��������
    public int tradeInfo(Map<String, Object> map) throws Exception
    {
       return (int)selectOne("mypage.tradeInfo", map);
    }
    
	//��������(����غ����϶�)
	public void tradeDelete(Map<String, Object> map) throws Exception  
	{
	   sqlSession.delete("mypage.tradeDelete", map);
	}
	
	//��ۻ���(����غ����϶�)
	public void deliDelete(Map<String, Object> map) throws Exception 
	{
		sqlSession.delete("mypage.deliDelete", map);
	}
	
	//�ֹ� ��� �� ���+1 , �Ǹŷ�-1
	public void amountUpdate(Map<String, Object> map) throws Exception
	{
		sqlSession.update("mypage.amountUpdate", map);
	}
	
	//-------------------��ٱ���---------------------------
	
	//���� ��ٱ��� ��������(����¡)
    public List<Map<String, Object>> pagingbasket(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.pagingbasket", map);
	}
    
    //���� ��ٱ��� �� ����
    public int myBasketCnt(Map<String, Object> map) throws Exception 
    {
	   return (int)selectOne("mypage.MyBasketCnt", map);
	}
	
    //��ٱ��� ���� ����
    public void deleteMyBasket(Map<String, Object> map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyBasket", map);
    }
    
    //-------------------���ø���Ʈ---------------------------
    
    //���� ���ø���Ʈ ��������
    public List<Map<String, Object>> myWishList(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myWishList", map);
	}
    
    //���� ���ø���Ʈ �� ����
    public int myWishCnt(Map<String, Object> map) throws Exception 
    {
	   return (int)selectOne("mypage.myWishCnt", map);
	}
	
    //���ø���Ʈ ���� ����
    public void deleteMyWish(Map<String, Object> map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyWish", map);
    }
    
    //-------------------QNA �Խ���---------------------------
    
    //QNA �Խ��� ����Ʈ
    public List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myQnaList", map);
	}
    
    //QNA �Խ��� ����
    public int myQnaCnt(Map<String, Object> map) throws Exception
    {
    	return (int)selectOne("mypage.myQnaCnt", map);
    }
    
    //QNA �Խ��� �� ���
    public void myQnaWrite(Map<String, Object> map) throws Exception
    {
    	sqlSession.insert("mypage.myQnaWrite", map);
    }
    
    //QNA �Խ��� �� �󼼺���
    @SuppressWarnings("unchecked")
	public Map<String, Object> myQnaView(Map<String, Object> map) throws Exception
    {
    	return (Map<String, Object>)selectOne("mypage.myQnaView", map);
    }
    
    //QNA �Խ��� �� ����
    public void myQnaUpdate(Map<String, Object> map) throws Exception
    {
    	sqlSession.update("mypage.myQnaUpdate", map);
    }
    
    //QNA �Խ��� �� ����
    public void myQnaDelete(Map<String, Object> map) throws Exception
    {
    	sqlSession.delete("mypage.myQnaDelete", map);
    }
   
    
}