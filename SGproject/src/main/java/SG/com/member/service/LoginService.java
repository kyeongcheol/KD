package SG.com.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface LoginService {
	
	//���̵� ã��
	String findId(Map<String, Object> map) throws Exception;
			
	//��� ã��
	String findPw(Map<String, Object> map) throws Exception;
		
	//�α��� ���� �ҷ�����		
	Map<String, Object> selectId(Map<String, Object> map) throws Exception;
		  
}
