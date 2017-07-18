package SG.com.goods.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SG.com.common.AbstractDAO;

@Repository
public class WishListDao extends AbstractDAO {

   @Autowired
   private SqlSessionTemplate sqlSession;
   
   //위시리스트 저장
   public void insertWish(Map<String, Object> map) throws Exception{
      sqlSession.insert("wishlist.insertWish", map);
   }
   //위시 리스트 삭제
   public void deleteMyWish(Map<String,Object> map) throws Exception{
	   sqlSession.delete("wishlist.deleteMyWish",map);
   }
   
   public List<Map<String, Object>> selectMyWish(int WISH_MEMBER_NO){
	   return sqlSession.selectList("wishlist.selectMyWish",WISH_MEMBER_NO);
   }
}