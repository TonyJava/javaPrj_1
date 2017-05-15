package com.sanqing.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sanqing.bean.Message;
import com.sanqing.dao.MessagePermissionDAO;
import com.sanqing.util.DBConnection;

public class MessagePermissionDAOImpl implements MessagePermissionDAO {

	@Override
	public void addMesagePermission(int[] employeeIDS, Message message) {
		Connection conn = DBConnection.getConnection();//创建连接
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		
		//构造SQL
		String SQL="create procedure p()"+
					" BEGIN"+
					" declare _messageID INT; "+
					" select  messageID into _messageID "+
					" FROM tb_message where messageContent= ? and messageTitle=?;"+
					" insert into tb_messagepermission(messageID,employeeID) ";

		try{
			//拼接SQL
			for(int i=0;i<employeeIDS.length;i++){
				if(i==0)
					SQL+=" values(_messageID,?)";
				else
					SQL+=",(_messageID,?) ";
			}
			SQL+=" ;END";
			
			pstmt = conn.prepareStatement(SQL);

			int count =3;//记录参数下标
			//设置messageContent and messageTtitle值
			pstmt.setString(1,message.getMessageContent());
			pstmt.setString(2,message.getMessageTitle());
			
			for(int i=0;i<employeeIDS.length;i++){
				pstmt.setInt(count++,employeeIDS[i]);
			}
			pstmt.execute();//为数据库添加存储过程
			
			//调用存储过程
			cstmt=conn.prepareCall("{call p()}");
			cstmt.executeQuery();//执行
			
			//删除存储过程
			cstmt.execute("drop procedure p");
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBConnection.close(cstmt);
			DBConnection.close(pstmt);
			DBConnection.close(conn);
		}
	}
	

}
