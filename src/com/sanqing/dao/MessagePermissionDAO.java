package com.sanqing.dao;


import com.sanqing.bean.Message;

/**
 * 
 * @author grass
 *
 */
public interface MessagePermissionDAO {
	/**
	 * �����Ϣ�Ͷ�Ӧ�Ŀɼ���
	 * @param e_list
	 * @param message
	 */
	public void addMesagePermission(int[] employeeIDS,Message message );
}
