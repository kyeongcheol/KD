package SG.com.goods.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ToppingDao {
	
	   @Autowired
	   private SqlSessionTemplate sqlSession;
	
	   public List<Map<String,Object>> toppingList(){
		   
		return sqlSession.selectList("topping.toppingList");		
	}
	   
	   public Map<String,Object> toppingOne(int no){
		   System.out.println("dao : "+no);
		   return sqlSession.selectOne("topping.toppingOne",no);
	   }

}
