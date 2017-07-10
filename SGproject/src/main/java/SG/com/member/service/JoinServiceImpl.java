package SG.com.member.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import SG.com.member.dao.JoinDao;

@Service("joinService")
public class JoinServiceImpl implements JoinService {
	

	@Resource(name="joinDAO")
	private JoinDao JoinDao;
	
	@Override
	public void insertMember(Map<String, Object> map, HttpServletRequest request) throws Exception{
		JoinDao.insertMember(map);
	} 	// ȸ�������� DB�� ���(ȸ������)
	
	@Override
	public int checkEmail(Map<String, Object> map) throws Exception{
		return JoinDao.checkEmail(map);
	}     // �̸��� ����- �̸��� �ߺ�Ȯ��
		
	
	@Override
	public int checkId(Map<String, Object> map) throws Exception {
		return JoinDao.checkId(map);
	}   //ȸ������ id�ߺ�üũ

		

		
		
		

}
