package com.sanqing.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanqing.bean.Employee;


/**
 * Servlet Filter implementation class BaseFilter
 */
public class BaseFilter implements Filter {

    public BaseFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestURL = httpRequest.getRequestURL().toString();
		
		//验证请求，是否需要拦截
		boolean isLogin = requestURL.contains("login")
				|| requestURL.contains(".css")
				||requestURL.contains(".gif")
				||requestURL.contains(".jpeg")
				||requestURL.contains(".png")
				||requestURL.contains(".js");
		
				
		boolean isNotJsp = requestURL.contains(".jsp");
		
		boolean isLoginJsp = requestURL.contains("login.jsp");
		
		if((!isLoginJsp&&isNotJsp)||!isLogin){
			HttpSession session = httpRequest.getSession();
			Employee employee = (Employee)session.getAttribute("employee");
			if(employee==null||employee.getEmployeeName()==null || employee.getPassword()==null){
				httpResponse.sendRedirect("login.jsp");
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	
	
	}

}
