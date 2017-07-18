package SG.com.goods.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import SG.com.goods.dao.GoodsDao;
import SG.com.member.dao.JoinDao;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	
	@Override
	public List<Map<String, Object>> goodsList() throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.goodsList();
	}

	@Override
	public Map<String, Object> selectOneGoods(int num) throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.selectOneGoods(num);
	}

	@Override
	public String imageList(int num) throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.imageList(num);
	}

	@Override
	public List<Map<String, Object>> commentList(int num) throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.commentList(num);
	}

	@Override
	public void insertComment(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		goodsDao.insertComment(map);
	}

	@Override
	public void basketInsert(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
	    goodsDao.basketInsert(map);
	}

	@Override
	public List<Map<String, Object>> wishGoodsList(int MEMBER_NO) throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.wishGoodsList(MEMBER_NO);
	}

	@Override
	public List<Map<String, Object>> goodscategory(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return goodsDao.goodscategory(map);
	}

	@Override
	public List<Map<String, Object>> searchGoods(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsDao.searchGoods(map);
	}

	@Override
	public void deleteComment(int no) {
		// TODO Auto-generated method stub
		goodsDao.deleteComment(no);
		
	}

	@Override
	public void basketDelete(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		goodsDao.basketDelete(map);
	}

	@Override
	public Map<String, Object> selectOneGoodsforBasket(int num) throws Exception {
		// TODO Auto-generated method stub
		return goodsDao.selectOneGoodsforBasket(num);
	}



}
