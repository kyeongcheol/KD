package SG.com.goods.dao;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository
public class OrderDao extends AbstractDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	public void orderInsert(Map<String,Object> map) throws Exception{
		sqlSession.insert("order.orderInsert", map);
	}
	
	public void insertDeli(Map<String, Object> map) throws Exception{
		sqlSession.insert("deli.insertDeli", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object>selectLastOrder() throws Exception {
		
		return sqlSession.selectOne("order.selectLastOrder");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object>selectLastDeli() throws Exception {
		
		return sqlSession.selectOne("order.selectLastDeli");
	}
	
	public void updateDeli(Map<String,Object> map) throws Exception{
		sqlSession.update("order.updateDeli", map);
	}


}
