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
	
	//나의 주문내역 리스트
	@Override
	public List<Map<String, Object>> myOrederList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrederList(map);
	}

	//나의 주문내역 상세보기
	@Override
	public Map<String, Object> myOrderDetail(Map<String, Object> map) throws Exception 
	{
		return memberDao.myOrderDetail(map);
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
    
	//주문삭제 (입금전일때)
	@Override
	public void orderDelete(Map<String, Object> map) throws Exception 
	{
		memberDao.orderDelete(map);
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

	//나의 장바구니 내역보기
	@Override
	public List<Map<String, Object>> myBasketList(Map<String, Object> map) throws Exception 
	{
		return memberDao.myBasketList(map);
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

	//위시리스트 내역 삭제
	@Override
	public void deleteMyWish(Map<String, Object> map) throws Exception 
	{
		memberDao.deleteMyWish(map);
	}

	//상품 이름으로 검색
	@Override
	public List<Map<String, Object>> searchWish0(Map<String, Object> map) throws Exception 
	{
		return memberDao.searchWish0(map);
	}

}
