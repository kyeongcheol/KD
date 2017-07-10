package SG.com.goods.service;

import java.util.List;
import java.util.Map;

public interface GoodsService {

	public List<Map<String, Object>> goodsList() throws Exception ;
	
	public Map<String,Object> selectOneGoods(int num) throws Exception;

	public String imageList(int num) throws Exception;
	
	public List<Map<String,Object>> commentList(int num) throws Exception;

	public void insertComment(Map<String,Object> map) throws Exception;
	
	public void basketInsert(Map<String,Object> map) throws Exception;

}
