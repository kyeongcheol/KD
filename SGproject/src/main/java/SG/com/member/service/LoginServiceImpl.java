package SG.com.member.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import SG.com.member.dao.LoginDao;

 @Service("loginService")
 public class LoginServiceImpl implements LoginService {
	
	@Resource(name="loginDAO")
	private LoginDao loginDao;
	
	//���̵� ã��
	@Override
	public String findId(Map<String, Object> map) throws Exception
	{
		return loginDao.findId(map);
	}
		
	//��� ã��
	@Override
	public String findPw(Map<String, Object> map) throws Exception
	{
		return loginDao.findPw(map);
	}
	
	
	//�α��� ���� �ҷ�����	
	public Map<String, Object> selectId(Map<String, Object> map) throws Exception
	{
		return loginDao.selectId(map);
	}

}
