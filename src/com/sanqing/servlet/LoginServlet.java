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

	//����loginService����
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
	
	
	
	
	//����get����
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	
	//����post����
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//���ñ����ʽ
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		//����д����
		Writer out=null;
		
		try{
			//��ȡEmployeeDAO
			EmployeeDAO e_dao = EmployeeDAOFactory.getEmployeeDAOInstance(); 
			//��ȡemployee list
			List<Employee> e_list = e_dao.findAllEmployee();
			
			
			//��ȡwriter����
			out = resp.getWriter();
			
			//��ȡ�ͻ��˵Ĵ����û���Ϣ
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			int validecode =-1;
			if(!(req.getParameter("validecode")==null)&&!"".equals(req.getParameter("validecode")))
				validecode = Integer.parseInt(req.getParameter("validecode"));
			
			//��ȡsession����
			HttpSession session = req.getSession();
			int valide = Integer.parseInt(session.getAttribute(RANDOMCODEKEY).toString());
			
			//���session���Ƿ����employee����
			Employee employee = (Employee)session.getAttribute("employee");
			
			//�鿴��֤��
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
				//��¼�û����Ƿ����
				boolean exist=false;
				//��������
				for(int i =0;i<e_list.size();i++){
					Employee _employee = (Employee)e_list.get(i);
					//������ȷ��ת����ҳ
					if(username.equals(_employee.getEmployeeName())&&password.equals(_employee.getPassword())){
						session.setAttribute("employee",_employee);
						exist=true;
						resp.sendRedirect("index.jsp");
					}
				}
				
				//���û���������ʱ�ض���
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
