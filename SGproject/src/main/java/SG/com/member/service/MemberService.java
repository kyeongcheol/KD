package SG.com.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface MemberService {
	
	 // ���� ȸ������ ��ȸ
	 Map<String, Object> myinfoDetail(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	 // ���� ȸ������ ����
	 void updateMyinfo(Map<String, Object> map) throws Exception;

	 //ȸ��Ż��
	 void deleteMember(Map<String, Object> map) throws Exception;
	


}
