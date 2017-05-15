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
		Connection conn = DBConnection.getConnection();//��������
		PreparedStatement pstmt = null;
		CallableStatement cstmt = null;
		
		//����SQL
		String SQL="create procedure p()"+
					" BEGIN"+
					" declare _messageID INT; "+
					" select  messageID into _messageID "+
					" FROM tb_message where messageContent= ? and messageTitle=?;"+
					" insert into tb_messagepermission(messageID,employeeID) ";

		try{
			//ƴ��SQL
			for(int i=0;i<employeeIDS.length;i++){
				if(i==0)
					SQL+=" values(_messageID,?)";
				else
					SQL+=",(_messageID,?) ";
			}
			SQL+=" ;END";
			
			pstmt = conn.prepareStatement(SQL);

			int count =3;//��¼�����±�
			//����messageContent and messageTtitleֵ
			pstmt.setString(1,message.getMessageContent());
			pstmt.setString(2,message.getMessageTitle());
			
			for(int i=0;i<employeeIDS.length;i++){
				pstmt.setInt(count++,employeeIDS[i]);
			}
			pstmt.execute();//Ϊ���ݿ���Ӵ洢����
			
			//���ô洢����
			cstmt=conn.prepareCall("{call p()}");
			cstmt.executeQuery();//ִ��
			
			//ɾ���洢����
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
