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
	} 	// 회원정보를 DB에 등록(회원가입)
	
	@Override
	public int checkEmail(Map<String, Object> map) throws Exception{
		return JoinDao.checkEmail(map);
	}     // 이메일 인증- 이메일 중복확인
		
	
	@Override
	public int checkId(Map<String, Object> map) throws Exception {
		return JoinDao.checkId(map);
	}   //회원가입 id중복체크

		

		
		
		

}
