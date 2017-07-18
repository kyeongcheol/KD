package SG.com.goods.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import SG.com.goods.dao.ToppingDao;


@Service
public class ToppingServiceImpl implements ToppingService {

	@Resource
	private ToppingDao toppingDao;
	
	   public List<Map<String,Object>> toppingList(){
		return toppingDao.toppingList();
		   
	   }

	@Override
	public Map<String,Object> toppingOne(int no) {
		// TODO Auto-generated method stub
		Map<String,Object> map = toppingDao.toppingOne(no);
		System.out.println("service :"+map);
		
		return map;
	}

}
