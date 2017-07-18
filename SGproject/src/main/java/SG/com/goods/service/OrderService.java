package SG.com.goods.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {
	
	public void orderInsert(Map<String,Object> map) throws Exception;

	public void insertDeli(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> selectLastOrder() throws Exception;
	
	public Map<String, Object> selectLastDeli() throws Exception;

	public void updateDeli(Map<String, Object> map) throws Exception;



}
