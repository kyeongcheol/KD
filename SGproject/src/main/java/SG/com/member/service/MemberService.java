package SG.com.member.service;

import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService 
{
	
	 //-------------------회원정보---------------------------
	 // 나의 회원정보 조회
	 Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception;
	
	 // 나의 회원정보 수정
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //회원탈퇴
	 void deleteMember(Map<String, Object> map) throws Exception;
	 
	 //회원별 총 누적금액
	 int mysumTradeMoney(Map<String, Object> map) throws Exception;
	
	 //-------------------주문내역---------------------------
	 //배송번호 별 주문내역 건수 조회
	 int orderdelicnt(Map<String, Object> map) throws Exception;
	 
	 //나의 주문내역 개수 조회
	 int myOrderCnt(Map<String, Object>map) throws Exception;
	 
	 //나의 주문내역 리스트
	 List<Map<String, Object>> myOrderList(Map<String, Object>map) throws Exception; 
		
	 //나의 주문내역 상세보기
	 List<Map<String, Object>> myOrderDetail(Map<String, Object>map) throws Exception;
		
	 //주문내역 상세보기 수정(주문개수수정, 입금전일때)
	 void myOrderUpdate(Map<String, Object>map) throws Exception;
		
	 //주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	 void myDeliUpdate(Map<String, Object>map) throws Exception;
	 
	 //결제번호 받아오기
     int tradeInfo(Map<String, Object>map) throws Exception;
			
	 //주문삭제 (입금전일때)
	 void orderDelete(Map<String, Object>map) throws Exception;
	 
	 //주문삭제 (배송준비중 일때)
	 void delinodel(Map<String, Object>map) throws Exception;
	 
	 //결제삭제(배송준비중일때)
	 void tradeDelete(Map<String, Object>map) throws Exception;
		
	 //배송삭제(배송준비중일때)
	 void deliDelete(Map<String, Object> map) throws Exception;
	 
	 //재고, 판매량 업데이트
	 void amountUpdate(Map<String, Object> map) throws Exception;
	 
	 //-------------------장바구니---------------------------
	 //나의 장바구니 내역보기
     List<Map<String, Object>> pagingbasket(Map<String, Object>map) throws Exception;
     
     //나의 장바구니 카운트
     int myBasketCnt(Map<String, Object>map) throws Exception;
   
	 //장바구니 내역 삭제
	 void deleteMyBasket(Map<String, Object>map) throws Exception;
	 
	 //-------------------위시리스트---------------------------
	 //나의 위시리스트 내역보기
     List<Map<String, Object>> myWishList(Map<String, Object>map) throws Exception;
     
     //나의 위시리스트 개수
     int myWishCnt(Map<String, Object>map) throws Exception;
	  
	 //위시리스트 내역 삭제
	 void deleteMyWish(Map<String, Object>map) throws Exception;
	 
	 //-------------------QNA 게시판 리스트---------------------------
     //나의 QNA 게시판 리스트
	 List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception;
	 
	 //나의 QNA 게시판 개수
	 int myQnaCnt(Map<String, Object> map) throws Exception;
     
	 //나의 QNA 게시판 글 등록
	 void myQnaWrite(Map<String, Object> map) throws Exception;
	 
	 //나의 QNA 게시판 상세보기
	 Map<String, Object> myQnaView(Map<String, Object> map) throws Exception;
	 
	 //나의 QNA 글 수정
	 void myQnaUpdate(Map<String, Object> map) throws Exception;
	 
	 //나의 QNA 글 삭제
	 void myQnaDelete(Map<String, Object> map) throws Exception;
	 

}
