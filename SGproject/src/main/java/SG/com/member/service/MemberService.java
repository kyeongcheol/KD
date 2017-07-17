package SG.com.member.service;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService 
{
	
	 // 나의 회원정보 조회
	 Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception;
	
	 // 나의 회원정보 수정
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //회원탈퇴
	 void deleteMember(Map<String, Object> map) throws Exception;
	 
	 //회원별 총 누적금액
	 int mysumTradeMoney(Map<String, Object>map) throws Exception;
	
	 //나의 주문내역 리스트
	 List<Map<String, Object>> myOrederList(Map<String, Object>map) throws Exception; 
		
	 //나의 주문내역 상세보기
	 Map<String, Object> myOrderDetail(Map<String, Object>map) throws Exception;
		
	 //주문내역 상세보기 수정(주문개수수정, 입금전일때)
	 void myOrderUpdate(Map<String, Object>map) throws Exception;
		
	 //주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	 void myDeliUpdate(Map<String, Object>map) throws Exception;
			
	 //주문삭제 (입금전일때)
	 void orderDelete(Map<String, Object>map) throws Exception;
	 
	 //결제삭제(배송준비중일때)
	 void tradeDelete(Map<String, Object>map) throws Exception;
		
	 //배송삭제(배송준비중일때)
	 void deliDelete(Map<String, Object>map) throws Exception;
	 
	 //나의 장바구니 내역보기
     List<Map<String, Object>> myBasketList(Map<String, Object>map) throws Exception;
   
	 //장바구니 내역 삭제
	 void deleteMyBasket(Map<String, Object>map) throws Exception;
	 
	 //나의 위시리스트 내역보기
     List<Map<String, Object>> myWishList(Map<String, Object>map) throws Exception;
	  
	 //위시리스트 내역 삭제
	 void deleteMyWish(Map<String, Object>map) throws Exception;
	 
	 //상품이름으로 검색된 위시리스트 내역
     List<Map<String, Object>> searchWish0(Map<String, Object>map) throws Exception;

}
