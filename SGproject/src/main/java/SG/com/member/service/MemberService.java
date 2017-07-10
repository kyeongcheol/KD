package SG.com.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService {
	
	 // 나의 회원정보 조회
	 Map<String, Object> myinfoDetail(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	 // 나의 회원정보 수정
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //회원탈퇴
	 void deleteMember(Map<String, Object> map) throws Exception;
	


}
