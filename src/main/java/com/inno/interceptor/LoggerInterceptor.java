package com.inno.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;
import com.inno.service.UserLog;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private HttpSession session;

	@Autowired
	UserLog UserLogServ;

	private static final long MAX_INACTIVE_SESSION_TIME = 1 * 1000;

	static Logger logger = Logger.getLogger(LoggerInterceptor.class);

	static {
		BasicConfigurator.configure();
	}
	

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
 		logger.info("Request URL::" + request.getRequestURL().toString()
				+ ":: End Time=" + System.currentTimeMillis());
		System.out.println("prehandle:interceptor");
		
		if(!UserLogServ.runurl("/NewWeb/") && !UserLogServ.runurl("/NewWeb/login"))
		{
			String logdata = (String) request.getSession().getAttribute("websessn");
		//	String checkvalue=logdata.getRole();
			System.out.println(logdata);
		}
			//to check whether there is session timeout or no existing session
		if(!UserLogServ.runurl("/NewWeb/login")  ){
	        if(session.getMaxInactiveInterval() < MAX_INACTIVE_SESSION_TIME)
	        {
	System.out.println(session.getMaxInactiveInterval());
	 
	    response.sendRedirect("/NewWeb/");
	    
	    return false;
	   }
	        
	    	
}
		               
		
		//if returned false, we need to make sure 'response' is sent
 		return true;
		}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());

		System.out.println("INTERCEPTOR:post handler" );

		System.out.println(request.getSession().getAttribute("websessn"));
		
	
		if(UserLogServ.runurl("/NewWeb/delete.htm")){
			response.sendRedirect("/NewWeb/Fileform");
		}
		
		
				
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		logger.info("Request URL::" + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());

		System.out.println("INTERCEPTOR:completion handler");
	//	PrintWriter out=response.getWriter();
	//	Cookie[] ck =request.getCookies();
//		out.println("welcome"+ck[0].getValue());
	}
}
