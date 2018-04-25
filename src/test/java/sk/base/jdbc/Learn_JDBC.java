package sk.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class Learn_JDBC {
	public static void main(String[] args) {
//		level1_baseConnection();
		level2_trasactionManage();
		
	}
	
	//基础连接
	private static void level1_baseConnection() {
		String drivername = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "123456";
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			
			PreparedStatement ps = conn.prepareStatement("insert into user(id,name) values(?,?)");
			ps.setInt(1, 2);
			ps.setString(2, "hei");
			
			boolean execute = ps.execute();
			System.out.println(execute);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//事务管理
	public static void level2_trasactionManage() {
		String drivername = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "123456";
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		try 
		{
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("insert into user(id,name) values(?,?)");
			ps.setInt(1, 6);
			ps.setString(2, "666");
			
			ps.execute();
//			System.out.println(e);
			
			ps.setInt(1, 7);
			ps.setString(2, "oooo");
			
			ps.execute();
//			System.out.println(e2);
//			if(e2==0) {
//				throw new SQLException("乱写的");
//			}
//			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("回滚失败");
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("关闭连接失败");
				}
			}
		}
	}
}
