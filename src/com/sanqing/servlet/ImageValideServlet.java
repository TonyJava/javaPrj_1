package com.sanqing.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanqing.util.RandomValidateCode;


public class ImageValideServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//����get����
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//������֤��ͼƬ��������
		resp.setHeader("Cache-Control","no-store");
		resp.setDateHeader("Expires",0);
		resp.setHeader("Pragma","no-cache");
	
		//��ȡͼƬ
		RandomValidateCode imageValidate = new RandomValidateCode();
		imageValidate.getRandcode(req, resp);
	}

}