package SG.com.goods.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository
public class GoodsDao extends AbstractDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsList() throws Exception {
		
		return sqlSession.selectList("goods.goodsList");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> wishGoodsList(int MEMBER_NO) throws Exception{
		return sqlSession.selectList("goods.wishGoodsList",MEMBER_NO);
		
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectOneGoods(int num) throws Exception{
		return sqlSession.selectOne("goods.selectOneGoods",num);
	}
	
	@SuppressWarnings("unchecked")
	public String imageList(int num) throws Exception{
		return sqlSession.selectOne("goods.imageList",num);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> commentList(int num) throws Exception{
		return sqlSession.selectList("goods.commentList",num);
	}
	
	public void insertComment(Map<String,Object> map) throws Exception{
		 sqlSession.insert("goods.insertComment",map);
	}
	

	public void basketInsert(Map<String, Object> map) throws Exception{
	    sqlSession.insert("basket.basketInsert", map);
	}
	
	public void basketDelete(Map<String,Object> map)throws Exception{
		sqlSession.delete("basket.basketDelete",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodscategory(Map<String,Object> map){
		return sqlSession.selectList("goods.goodscategory",map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchGoods(Map<String,Object> map){
		return sqlSession.selectList("goods.searchGoods",map);
	}
	
	public void deleteComment(int no){
		sqlSession.delete("goods.deleteComment",no);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectOneGoodsforBasket(int num) throws Exception{
		return sqlSession.selectOne("goods.selectOneGoodsforBasket",num);
	}
	
	

}
