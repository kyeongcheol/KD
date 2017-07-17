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
	
	//-------------------회원정보---------------------------
	//회원정보 조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> myinfoDetail(Map<String, Object>map) throws Exception 
	{
		return (Map<String, Object>) selectOne("mypage.myinfoDetail", map);
	}
		
	//회원 정보 수정
	public void updateMyinfo(Map<String, Object>map) 
	{
		update("mypage.updateMyinfo", map);
	}

	// 관리자가 회원 정보 OFF로 수정(회원강제 탈퇴)
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	    update("mypage.deleteMember", map);
	}
	
	//회원별 구매 총 누적금액
	public int mysumTradeMoney(Map<String, Object>map) throws Exception 
	{
		return (int)selectOne("mypage.mysumTradeMoney", map);
	}
	
	//-------------------주문내역---------------------------
	
	//나의 주문내역 리스트 보기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrederList(Map<String, Object>map) throws Exception 
	{
		return (List<Map<String, Object>>)selectList("mypage.myOrderList", map);
	}

	//나의 주문내역 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> myOrderDetail(Map<String, Object>map) throws Exception 
	{
	    return (Map<String, Object>)selectOne("mypage.myOrderDetail", map);
	}
		
    //주문내역 상세보기 수정(주문 개수 수정, 입금전일때)
    public void myOrderUpdate(Map<String, Object>map) throws Exception 
    {
	    sqlSession.update("mypage.myOrderUpdate", map);
	}
	
    //주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	public void myDeliUpdate(Map<String, Object>map) throws Exception  
	{
	    sqlSession.update("mypage.myDeliUpdate", map);
	}
	
	//주문삭제 (입금전일때)
	public void orderDelete(Map<String, Object>map) throws Exception  
	{
	   sqlSession.delete("mypage.orderDelete", map);
	}
	
	//-------------------주문취소(배송준비 중일 때)---------------------------
	
	//결제삭제(배송준비중일때)
	public void tradeDelete(Map<String, Object>map) throws Exception  
	{
	   sqlSession.delete("mypage.tradeDelete", map);
	}
	
	//배송삭제(배송준비중일때)
	public void deliDelete(Map<String, Object>map) throws Exception 
	{
		sqlSession.delete("mypage.deliDelete", map);
	}
	
	//-------------------장바구니---------------------------
	
	//나의 장바구니 내역보기
    public List<Map<String, Object>> myBasketList(Map<String, Object>map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myBasketList", map);
	}
	
    //장바구니 내역 삭제
    public void deleteMyBasket(Map<String, Object>map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyBasket", map);
    }
    
    //-------------------위시리스트---------------------------
    
    //나의 위시리스트 내역보기
    public List<Map<String, Object>> myWishList(Map<String, Object>map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myWishList", map);
	}
	
    //위시리스트 내역 삭제
    public void deleteMyWish(Map<String, Object>map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyWish", map);
    }
    
    //상품 이름으로 검색된 위시리스트 내역보기
    public List<Map<String, Object>> searchWish0(Map<String, Object>map) throws Exception 
    {
	   return sqlSession.selectList("mypage.searchWish0", map);
	}
    
}