package SG.com.member.service;

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
	


}
