package com.sanqing.factory;

import com.sanqing.dao.MessagePermissionDAO;
import com.sanqing.daoImpl.MessagePermissionDAOImpl;

/**
 * 获取MessagePermissionDAO对象
 * @author grass
 *
 */
public class MessagePermissionDAOFactory {
	public static MessagePermissionDAO getMessagePermissionDAO(){
		return new MessagePermissionDAOImpl();
	}
}
