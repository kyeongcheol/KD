package SG.com.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import SG.com.common.CommandMap;

public interface JoinService 
{
	
	 // ȸ�������� DB�� ���(ȸ������)
	 void insertMember(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	// �̸��� ����- �̸��� �ߺ�Ȯ��
	 int checkEmail(Map<String, Object> map) throws Exception;

	//ȸ������ id�ߺ�üũ
	 int checkId(Map<String, Object> map) throws Exception;
	

}

