package cn.WorkSubmit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		//�������ݿ�����
		Class.forName("com.mysql.jdbc.Driver");
		//�����������ݿ����Ϣ
		//���ݿ��IP��ַ��localhostΪ���أ�"localhost"�ɸ���ΪԶ��IP��
		String url="jdbc:mysql://localhost:3306/WorkSubmit";
		String username="root";
		String password="no.123321";
		Connection conn=DriverManager.getConnection(url,username,password);
		return conn;
	}
	
	public static void release(Statement stmt,Connection conn) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}	
	}
	
	public static void release(ResultSet rs,Statement stmt,Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		release(stmt,conn);
	}	
}
