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
	
	//배송번호 별 주문내역 건수 조회
	public int orderdelicnt(Map<String, Object>map) throws Exception 
	{
	   return (int)selectOne("mypage.orderdelicnt", map);
    }
	
	//나의 주문내역 개수 조회
	public int myOrderCnt(Map<String, Object>map) throws Exception 
    {
	   return (int)selectOne("mypage.myOrderCnt", map);
	}
	
	//나의 주문내역 리스트 보기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) throws Exception 
	{
		return (List<Map<String, Object>>)selectList("mypage.myOrderList", map);
	}

	//나의 주문내역 상세보기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrderDetail(Map<String, Object> map) throws Exception 
	{
	    return (List<Map<String, Object>>)selectList("mypage.myOrderDetail", map);
	}
		
    //주문내역 상세보기 수정(주문 개수 수정, 입금전일때)
    public void myOrderUpdate(Map<String, Object> map) throws Exception 
    {
	    sqlSession.update("mypage.myOrderUpdate", map);
	}
	
    //주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	public void myDeliUpdate(Map<String, Object> map) throws Exception  
	{
	    sqlSession.update("mypage.myDeliUpdate", map);
	}
	
	//주문삭제 (입금전일때)
	public void orderDelete(Map<String, Object> map) throws Exception  
	{
	   sqlSession.delete("mypage.orderDelete", map);
	}
	
	//-------------------주문취소(배송준비 중일 때)---------------------------
	
	//주문삭제 (입금전일때)
	public void delinodel(Map<String, Object> map) throws Exception  
	{
	    sqlSession.delete("mypage.delinodel", map);
	}
		
	//결제 정보 가져오기
    public int tradeInfo(Map<String, Object> map) throws Exception
    {
       return (int)selectOne("mypage.tradeInfo", map);
    }
    
	//결제삭제(배송준비중일때)
	public void tradeDelete(Map<String, Object> map) throws Exception  
	{
	   sqlSession.delete("mypage.tradeDelete", map);
	}
	
	//배송삭제(배송준비중일때)
	public void deliDelete(Map<String, Object> map) throws Exception 
	{
		sqlSession.delete("mypage.deliDelete", map);
	}
	
	//주문 취소 시 재고+1 , 판매량-1
	public void amountUpdate(Map<String, Object> map) throws Exception
	{
		sqlSession.update("mypage.amountUpdate", map);
	}
	
	//-------------------장바구니---------------------------
	
	//나의 장바구니 내역보기(페이징)
    public List<Map<String, Object>> pagingbasket(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.pagingbasket", map);
	}
    
    //나의 장바구니 총 개수
    public int myBasketCnt(Map<String, Object> map) throws Exception 
    {
	   return (int)selectOne("mypage.MyBasketCnt", map);
	}
	
    //장바구니 내역 삭제
    public void deleteMyBasket(Map<String, Object> map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyBasket", map);
    }
    
    //-------------------위시리스트---------------------------
    
    //나의 위시리스트 내역보기
    public List<Map<String, Object>> myWishList(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myWishList", map);
	}
    
    //나의 위시리스트 총 개수
    public int myWishCnt(Map<String, Object> map) throws Exception 
    {
	   return (int)selectOne("mypage.myWishCnt", map);
	}
	
    //위시리스트 내역 삭제
    public void deleteMyWish(Map<String, Object> map) throws Exception 
    {
	    sqlSession.delete("mypage.deleteMyWish", map);
    }
    
    //-------------------QNA 게시판---------------------------
    
    //QNA 게시판 리스트
    public List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception 
    {
	   return sqlSession.selectList("mypage.myQnaList", map);
	}
    
    //QNA 게시판 개수
    public int myQnaCnt(Map<String, Object> map) throws Exception
    {
    	return (int)selectOne("mypage.myQnaCnt", map);
    }
    
    //QNA 게시판 글 등록
    public void myQnaWrite(Map<String, Object> map) throws Exception
    {
    	sqlSession.insert("mypage.myQnaWrite", map);
    }
    
    //QNA 게시판 글 상세보기
    @SuppressWarnings("unchecked")
	public Map<String, Object> myQnaView(Map<String, Object> map) throws Exception
    {
    	return (Map<String, Object>)selectOne("mypage.myQnaView", map);
    }
    
    //QNA 게시판 글 수정
    public void myQnaUpdate(Map<String, Object> map) throws Exception
    {
    	sqlSession.update("mypage.myQnaUpdate", map);
    }
    
    //QNA 게시판 글 삭제
    public void myQnaDelete(Map<String, Object> map) throws Exception
    {
    	sqlSession.delete("mypage.myQnaDelete", map);
    }
   
    
}