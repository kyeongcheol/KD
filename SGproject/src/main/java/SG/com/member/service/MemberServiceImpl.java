package SG.com.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import SG.com.member.dao.LoginDao;
import SG.com.member.dao.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService 
{
	
	@Resource(name="memberDAO")
	private MemberDao memberDao;
	
	// ���� ȸ������ ��ȸ
	@Override
	public Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception
	{
	   return memberDao.myinfoDetail(map);
	}
	
	// ���� ȸ������ ����
	@Override
	public void updateMyinfo(Map<String, Object> map) throws Exception
	{
       memberDao.updateMyinfo(map);
	}
		
	//ȸ��Ż��
	@Override
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	   memberDao.deleteMember(map);
	}
	  
	//ȸ���� ���� �� �����ݾ�
	@Override
	public int mysumTradeMoney(Map<String, Object>map) throws Exception
	{
	   return (int)memberDao.mysumTradeMoney(map);
	}
	
	//��۹�ȣ �� �ֹ����� �Ǽ� ��ȸ
	@Override
	public int orderdelicnt(Map<String, Object> map) throws Exception 
	{
		return (int)memberDao.orderdelicnt(map);
	}
    
	//���� �ֹ����� ���� ��ȸ
	@Override
	public int myOrderCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrderCnt(map);
	}
	
	//���� �ֹ����� ����Ʈ
	@Override
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrderList(map);
	}

	//���� �ֹ����� �󼼺���
	@Override
	public List<Map<String, Object>> myOrderDetail(Map<String, Object> map) throws Exception 
	{
		return (List<Map<String, Object>>) memberDao.myOrderDetail(map);
	}
	
	//�ֹ����� �󼼺��� ����(�ֹ���������, �Ա����϶�)
	@Override
	public void myOrderUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myOrderUpdate(map);
		
	}
	
	//�ֹ����� �󼼺��� ����(�����������, �Ա���,����غ����϶�)
	@Override
	public void myDeliUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myDeliUpdate(map);
	}
    
	//������ȣ �޾ƿ���
	@Override
	public int tradeInfo(Map<String, Object>map) throws Exception
	{
	   return memberDao.tradeInfo(map);
	}
	
	//�ֹ����� (�Ա����϶�)
	@Override
	public void orderDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.orderDelete(map);
	}
	
	//�ֹ����� (����غ����϶�)
	@Override
	public void delinodel(Map<String, Object> map) throws Exception 
	{
		memberDao.delinodel(map);
	}

	//��������(����غ����϶�)
	@Override
	public void tradeDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.tradeDelete(map);	
	}
	
	//��ۻ���(����غ����϶�)
	@Override
	public void deliDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.deliDelete(map);	
	}
	
	//���+1, �Ǹŷ�-1 
	@Override
	public void amountUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.amountUpdate(map);
	}

	//���� ��ٱ��� ��������
	@Override
	public List<Map<String, Object>> pagingbasket(Map<String, Object> map) throws Exception 
	{
		return memberDao.pagingbasket(map);
	}
	
    //��ٱ��� ���� �Ǽ�
	@Override
	public int myBasketCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myBasketCnt(map);
	}

	//���� ��ٱ��� ���� ����
	@Override
	public void deleteMyBasket(Map<String, Object> map) throws Exception 
	{
		memberDao.deleteMyBasket(map);	
	}

	//���� ���ø���Ʈ ��������
	@Override
	public List<Map<String, Object>> myWishList(Map<String, Object> map) throws Exception 
	{
	    return memberDao.myWishList(map);
	}
	
	//���� ���ø���Ʈ ����
	@Override
	public int myWishCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myWishCnt(map);
	}

	//���ø���Ʈ ���� ����
	@Override
	public void deleteMyWish(Map<String, Object> map) throws Exception 
	{
		memberDao.deleteMyWish(map);
	}

	//���� QNA �Խ��� ����Ʈ
	@Override
	public List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaList(map);
	}

	//���� QNA �Խ��� ����
	@Override
	public int myQnaCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaCnt(map);
	}

	//���� QNA �Խ��� �� ���
	@Override
	public void myQnaWrite(Map<String, Object> map) throws Exception 
	{
		memberDao.myQnaWrite(map);		
	}
	
    //���� QNA �Խ��� �󼼺���
	@Override
	public Map<String, Object> myQnaView(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaView(map);
	}

	//���� QNA �Խ��� �� ����
	@Override
	public void myQnaUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myQnaUpdate(map);
	}

	//���� QNA �Խ��� �� ����
	@Override
	public void myQnaDelete(Map<String, Object> map) throws Exception 
	{
	    memberDao.myQnaDelete(map);
	}

}
