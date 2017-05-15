package com.sanqing.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver" ;			//����������
    private static final String DBURL = "jdbc:mysql://localhost:3306/db_affairmanage?useUnicode=true&characterEncoding=GBK";//����URL
    private static final String DBUSER = "root" ;								//���ݿ��û���
    private static final String DBPASSWORD = "mymy";							//���ݿ�����
	public static Connection getConnection(){
		Connection conn = null;													//����һ�����Ӷ���
		try {
			Class.forName(DBDRIVER);											//ע������
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);		//������Ӷ���
		} catch (ClassNotFoundException e) {									//�����������޷��ҵ��쳣
			e.printStackTrace();										
		} catch (SQLException e) {												//����SQL�쳣
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {//�ر����Ӷ���
		if(conn != null) {				//���conn���Ӷ���Ϊ��
			try {
				conn.close();			//�ر�conn���Ӷ������
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pstmt) {//�ر�Ԥ�������
		if(pstmt != null) {				//���pstmtԤ�������Ϊ��
			try {
				pstmt.close();			//�ر�pstmtԤ�������
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �ر�cstmt
	 * @param cstmt
	 */
	public static void close(CallableStatement cstmt){
		if(cstmt!=null){
			try{
				cstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {//�رս��������
		if(rs != null) {				//���rs���������Ϊnull
			try {
				rs.close();				//�ر�rs���������
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
