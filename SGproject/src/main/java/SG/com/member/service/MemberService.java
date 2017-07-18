package SG.com.member.service;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService 
{
	
	 // ���� ȸ������ ��ȸ
	 Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception;
	
	 // ���� ȸ������ ����
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //ȸ��Ż��
	 void deleteMember(Map<String, Object> map) throws Exception;
	 
	 //ȸ���� �� �����ݾ�
	 int mysumTradeMoney(Map<String, Object>map) throws Exception;
	
	 //���� �ֹ����� ����Ʈ
	 List<Map<String, Object>> myOrderList(Map<String, Object>map) throws Exception; 
		
	 //���� �ֹ����� �󼼺���
	 Map<String, Object> myOrderDetail(Map<String, Object>map) throws Exception;
		
	 //�ֹ����� �󼼺��� ����(�ֹ���������, �Ա����϶�)
	 void myOrderUpdate(Map<String, Object>map) throws Exception;
		
	 //�ֹ����� �󼼺��� ����(�����������, �Ա���,����غ����϶�)
	 void myDeliUpdate(Map<String, Object>map) throws Exception;
			
	 //�ֹ����� (�Ա����϶�)
	 void orderDelete(Map<String, Object>map) throws Exception;
	 
	 //��������(����غ����϶�)
	 void tradeDelete(Map<String, Object>map) throws Exception;
		
	 //��ۻ���(����غ����϶�)
	 void deliDelete(Map<String, Object>map) throws Exception;
	 
	 //���� ��ٱ��� ��������
     List<Map<String, Object>> myBasketList(Map<String, Object>map) throws Exception;
   
	 //��ٱ��� ���� ����
	 void deleteMyBasket(Map<String, Object>map) throws Exception;
	 
	 //���� ���ø���Ʈ ��������
     List<Map<String, Object>> myWishList(Map<String, Object>map) throws Exception;
	  
	 //���ø���Ʈ ���� ����
	 void deleteMyWish(Map<String, Object>map) throws Exception;
	 
	 //��ǰ�̸����� �˻��� ���ø���Ʈ ����
     List<Map<String, Object>> searchWish0(Map<String, Object>map) throws Exception;

}
