package SG.com.member.service;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService 
{
	
	 //-------------------ȸ������---------------------------
	 // ���� ȸ������ ��ȸ
	 Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception;
	
	 // ���� ȸ������ ����
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //ȸ��Ż��
	 void deleteMember(Map<String, Object> map) throws Exception;
	 
	 //ȸ���� �� �����ݾ�
	 int mysumTradeMoney(Map<String, Object> map) throws Exception;
	
	 //-------------------�ֹ�����---------------------------
	 //��۹�ȣ �� �ֹ����� �Ǽ� ��ȸ
	 int orderdelicnt(Map<String, Object> map) throws Exception;
	 
	 //���� �ֹ����� ���� ��ȸ
	 int myOrderCnt(Map<String, Object>map) throws Exception;
	 
	 //���� �ֹ����� ����Ʈ
	 List<Map<String, Object>> myOrderList(Map<String, Object>map) throws Exception; 
		
	 //���� �ֹ����� �󼼺���
	 List<Map<String, Object>> myOrderDetail(Map<String, Object>map) throws Exception;
		
	 //�ֹ����� �󼼺��� ����(�ֹ���������, �Ա����϶�)
	 void myOrderUpdate(Map<String, Object>map) throws Exception;
		
	 //�ֹ����� �󼼺��� ����(�����������, �Ա���,����غ����϶�)
	 void myDeliUpdate(Map<String, Object>map) throws Exception;
	 
	 //������ȣ �޾ƿ���
     int tradeInfo(Map<String, Object>map) throws Exception;
			
	 //�ֹ����� (�Ա����϶�)
	 void orderDelete(Map<String, Object>map) throws Exception;
	 
	 //�ֹ����� (����غ��� �϶�)
	 void delinodel(Map<String, Object>map) throws Exception;
	 
	 //��������(����غ����϶�)
	 void tradeDelete(Map<String, Object>map) throws Exception;
		
	 //��ۻ���(����غ����϶�)
	 void deliDelete(Map<String, Object> map) throws Exception;
	 
	 //���, �Ǹŷ� ������Ʈ
	 void amountUpdate(Map<String, Object> map) throws Exception;
	 
	 //-------------------��ٱ���---------------------------
	 //���� ��ٱ��� ��������
     List<Map<String, Object>> pagingbasket(Map<String, Object>map) throws Exception;
     
     //���� ��ٱ��� ī��Ʈ
     int myBasketCnt(Map<String, Object>map) throws Exception;
   
	 //��ٱ��� ���� ����
	 void deleteMyBasket(Map<String, Object>map) throws Exception;
	 
	 //-------------------���ø���Ʈ---------------------------
	 //���� ���ø���Ʈ ��������
     List<Map<String, Object>> myWishList(Map<String, Object>map) throws Exception;
     
     //���� ���ø���Ʈ ����
     int myWishCnt(Map<String, Object>map) throws Exception;
	  
	 //���ø���Ʈ ���� ����
	 void deleteMyWish(Map<String, Object>map) throws Exception;
	 
	 //-------------------QNA �Խ��� ����Ʈ---------------------------
     //���� QNA �Խ��� ����Ʈ
	 List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception;
	 
	 //���� QNA �Խ��� ����
	 int myQnaCnt(Map<String, Object> map) throws Exception;
     
	 //���� QNA �Խ��� �� ���
	 void myQnaWrite(Map<String, Object> map) throws Exception;
	 
	 //���� QNA �Խ��� �󼼺���
	 Map<String, Object> myQnaView(Map<String, Object> map) throws Exception;
	 
	 //���� QNA �� ����
	 void myQnaUpdate(Map<String, Object> map) throws Exception;
	 
	 //���� QNA �� ����
	 void myQnaDelete(Map<String, Object> map) throws Exception;
	 

}
