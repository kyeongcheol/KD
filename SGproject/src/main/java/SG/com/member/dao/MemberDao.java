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
		
    //주문내역 상세보기 수정(주문개수수정, 입금전일때)
    public void myOrderUpdate(Map<String, Object>map) throws Exception 
    {
	    sqlSession.update("order.myOrderUpdate", map);
	}
	
    //주문내역 상세보기 수정(배송정보수정, 입금전,배송준비중일때)
	public void myDeliUpdate(Map<String, Object>map) throws Exception  
	{
	    sqlSession.update("deli.myDeliUpdate", map);
	}
	
	//배송번호 별 총 합계금액
	public int sumTradeMoney(Map<String, Object>map) throws Exception 
	{
	   return (int)selectOne("order.sumTradeMoney", map);
	}
	
	//주문삭제 (입금전일때)
	public void orderDelete(Map<String, Object>map) throws Exception  
	{
	   sqlSession.delete("order.orderDelete", map);
	}
	
	

}