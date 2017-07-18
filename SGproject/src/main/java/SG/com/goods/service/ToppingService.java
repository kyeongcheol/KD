package SG.com.goods.service;

import java.util.List;
import java.util.Map;

public interface ToppingService {

	   public List<Map<String,Object>> toppingList();
	   
	   public Map<String,Object> toppingOne(int no);


}
