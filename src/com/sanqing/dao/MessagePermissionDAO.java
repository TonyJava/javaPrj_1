package com.sanqing.dao;


import com.sanqing.bean.Message;

/**
 * 
 * @author grass
 *
 */
public interface MessagePermissionDAO {
	/**
	 * 添加消息和对应的可见人
	 * @param e_list
	 * @param message
	 */
	public void addMesagePermission(int[] employeeIDS,Message message );
}
