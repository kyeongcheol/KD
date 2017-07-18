package SG.com.goods.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import SG.com.goods.dao.WishListDao;

@Service
public class WishServiceImpl implements WishService {

	@Resource
	WishListDao wishDao;
	
	@Override
	public void insertWish(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		wishDao.insertWish(map);
	}

	@Override
	public void deleteMyWish(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		wishDao.deleteMyWish(map);
	}

	@Override
	public List<Map<String, Object>> selectMyWish(int WISH_MEMBER_NO) {
		// TODO Auto-generated method stub
		return wishDao.selectMyWish(WISH_MEMBER_NO);
	}

}
