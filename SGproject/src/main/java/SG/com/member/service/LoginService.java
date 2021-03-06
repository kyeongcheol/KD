package SG.com.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface LoginService {
	
	//아이디 찾기
	String findId(Map<String, Object> map) throws Exception;
			
	//비번 찾기
	String findPw(Map<String, Object> map) throws Exception;
		
	//로그인 정보 불러오기		
	Map<String, Object> selectId(Map<String, Object> map) throws Exception;
		  
}
