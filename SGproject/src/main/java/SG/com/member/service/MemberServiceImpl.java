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
	
	// 나의 회원정보 조회
	@Override
	public Map<String, Object> myinfoDetail(Map<String, Object> map) throws Exception
	{
	   return memberDao.myinfoDetail(map);
	}
	
	// 나의 회원정보 수정
	@Override
	public void updateMyinfo(Map<String, Object> map) throws Exception
	{
       memberDao.updateMyinfo(map);
	}
		
	//회원탈퇴
	@Override
	public void deleteMember(Map<String, Object> map) throws Exception
	{
	   memberDao.deleteMember(map);
	}
	  
	//회원의 결제 총 누적금액
	@Override
	public int mysumTradeMoney(Map<String, Object>map) throws Exception
	{
	   return (int)memberDao.mysumTradeMoney(map);
	}
	
	//배송번호 별 주문내역 건수 조회
	@Override
	public int orderdelicnt(Map<String, Object> map) throws Exception 
	{
		return (int)memberDao.orderdelicnt(map);
	}
    
	//나의 주문내역 개수 조회
	@Override
	public int myOrderCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrderCnt(map);
	}
	
	//나의 주문내역 리스트
	@Override
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrderList(map);
	}

	//나의 주문내역 상세보기
	@Override
	public List<Map<String, Object>> myOrderDetail(Map<String, Object> map) throws Exception 
	{
		return (List<Map<String, Object>>) memberDao.myOrderDetail(map);
	}
	
	//주문내역 상세보기 수정(주문개수수정, 입금전일때)
	@Override
	public void myOrderUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myOrderUpdate(map);
		
	}
	
	//주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	@Override
	public void myDeliUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myDeliUpdate(map);

	}
    
	//결제번호 받아오기
	@Override
	public int tradeInfo(Map<String, Object>map) throws Exception
	{
	   return memberDao.tradeInfo(map);
	}
	
	//주문삭제 (입금전일때)
	@Override
	public void orderDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.orderDelete(map);
	}
	
	//주문삭제 (배송준비중일때)
	@Override
	public void delinodel(Map<String, Object> map) throws Exception 
	{
		memberDao.delinodel(map);
	}

	//결제삭제(배송준비중일때)
	@Override
	public void tradeDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.tradeDelete(map);	
	}
	
	//배송삭제(배송준비중일때)
	@Override
	public void deliDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.deliDelete(map);	
	}
	
	//재고+1, 판매량-1 
	@Override
	public void amountUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.amountUpdate(map);
	}

	//나의 장바구니 내역보기
	@Override
	public List<Map<String, Object>> pagingbasket(Map<String, Object> map) throws Exception 
	{
		return memberDao.pagingbasket(map);
	}
	
    //장바구니 내역 건수
	@Override
	public int myBasketCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myBasketCnt(map);
	}

	//나의 장바구니 내역 삭제
	@Override
	public void deleteMyBasket(Map<String, Object> map) throws Exception 
	{
		memberDao.deleteMyBasket(map);	
	}

	//나의 위시리스트 내역보기
	@Override
	public List<Map<String, Object>> myWishList(Map<String, Object> map) throws Exception 
	{
	    return memberDao.myWishList(map);
	}
	
	//나의 위시리스트 개수
	@Override
	public int myWishCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myWishCnt(map);
	}

	//위시리스트 내역 삭제
	@Override
	public void deleteMyWish(Map<String, Object> map) throws Exception 
	{
		memberDao.deleteMyWish(map);
	}

	//나의 QNA 게시판 리스트
	@Override
	public List<Map<String, Object>> myQnaList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaList(map);
	}

	//나의 QNA 게시판 개수
	@Override
	public int myQnaCnt(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaCnt(map);
	}

	//나의 QNA 게시판 글 등록
	@Override
	public void myQnaWrite(Map<String, Object> map) throws Exception 
	{
		memberDao.myQnaWrite(map);		
	}
	
    //나의 QNA 게시판 상세보기
	@Override
	public Map<String, Object> myQnaView(Map<String, Object> map) throws Exception 
	{
		return memberDao.myQnaView(map);
	}

	//나의 QNA 게시판 글 수정
	@Override
	public void myQnaUpdate(Map<String, Object> map) throws Exception 
	{
		memberDao.myQnaUpdate(map);
	}

	//나의 QNA 게시판 글 삭제
	@Override
	public void myQnaDelete(Map<String, Object> map) throws Exception 
	{
	    memberDao.myQnaDelete(map);
	}

}
