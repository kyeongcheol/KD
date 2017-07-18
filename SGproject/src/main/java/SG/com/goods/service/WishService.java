package SG.com.goods.service;

import java.util.List;
import java.util.Map;

public interface WishService {
	
	public void insertWish(Map<String, Object> map) throws Exception;

	public void deleteMyWish(Map<String,Object> map) throws Exception;

	public List<Map<String, Object>> selectMyWish(int WISH_MEMBER_NO);

}
