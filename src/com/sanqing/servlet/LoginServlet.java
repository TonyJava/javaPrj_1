package com.sanqing.servlet;


import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.factory.EmployeeDAOFactory;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//保存loginService变量
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
	
	
	
	
	//处理get请求
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	
	//处理post请求
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//保存写出流
		Writer out=null;
		
		try{
			//获取EmployeeDAO
			EmployeeDAO e_dao = EmployeeDAOFactory.getEmployeeDAOInstance(); 
			//获取employee list
			List<Employee> e_list = e_dao.findAllEmployee();
			
			
			//获取writer对象
			out = resp.getWriter();
			
			//获取客户端的传来用户信息
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			int validecode =-1;
			if(!(req.getParameter("validecode")==null)&&!"".equals(req.getParameter("validecode")))
				validecode = Integer.parseInt(req.getParameter("validecode"));
			
			//获取session对象
			HttpSession session = req.getSession();
			int valide = Integer.parseInt(session.getAttribute(RANDOMCODEKEY).toString());
			
			//检查session中是否存有employee数据
			Employee employee = (Employee)session.getAttribute("employee");
			
			//查看验证码
			if(validecode!=valide)
			{
				resp.sendRedirect("index.jsp");
			}else if(employee!=null){
				if(username.equals(employee.getEmployeeName())&&password.equals(employee.getPassword())){
					resp.sendRedirect("index.jsp");
				}else{
					req.getRequestDispatcher("login.jsp").forward(req,resp);
				}
			}
			else if(e_list!=null){
				//记录用户名是否存在
				boolean exist=false;
				//便利集合
				for(int i =0;i<e_list.size();i++){
					Employee _employee = (Employee)e_list.get(i);
					//密码正确跳转到首页
					if(username.equals(_employee.getEmployeeName())&&password.equals(_employee.getPassword())){
						session.setAttribute("employee",_employee);
						exist=true;
						resp.sendRedirect("index.jsp");
					}
				}
				
				//当用户名不存在时重定向
				if(!exist){
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			}
			else{
				req.getRequestDispatcher("login.jsp").forward(req,resp);
			}
			 
		}catch(Exception e){
			e.printStackTrace();
			resp.sendRedirect("login.jsp");
		}
		finally{
			out.flush();
			out.close();
		}
		 
		 
	}
	

}
