package SG.com.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�α�
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

//Interceptor
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter
{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	
	//��Ʈ�ѷ� �����ϱ� ��
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler) throws Exception 
		{
			if(log.isDebugEnabled())
		     {
		    	   log.debug("======================================          "
		    	   		+ "START         ======================================");
		    	   
		    	   log.debug(" Request URI \t: " + request.getRequestURI());
		      }
			
		   return super.preHandle(request, response, handler);
		}
		
	//��Ʈ�ѷ� �����ϰ� �� �����ϱ� ��
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception 
	{
		if(log.isDebugEnabled())
		{
			log.debug("======================================           "
					+ "END          ======================================\n");
		}
      
	}
	
}
