package com.sk.hide.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.sk.hide.exception.HideException;
import com.sk.hide.util.ApplicationContextUtil;

@RestController
public class TransactionManaController {
	private static Logger logger = Logger.getLogger(TransactionManaController.class); 
	
	@GetMapping("/transac")
	@Transactional //开启事务管理 NO.3
	public Map testTransac(HttpServletRequest res) {
		String id = res.getParameter("id");
		
		DataSource ds = (DataSource)ApplicationContextUtil.getBean("dataSource");
		SqlMapClient sql = (SqlMapClient)ApplicationContextUtil.getBean("sqlMapClient");
//		Connection conn = null;
//		
//		String name = null;
//		try {
//			conn = ds.getConnection();
////			conn.setAutoCommit(false);
//			PreparedStatement ps = conn.prepareStatement("select * from user where id=" + id);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				name = rs.getString(2);
//				System.out.println(name);
//			}
//			
//			Thread.sleep(10000);
//			
//			ResultSet rs2 = ps.executeQuery();
//			while(rs2.next()) {
//				name = rs2.getString(2);
//				System.out.println(name);
//			}
//			
//		} catch (SQLException e) {
//			logger.error("获取连接失败", e);
//			throw new HideException(500, "获取连接失败");
//		} catch (InterruptedException e) {
//			String errmsg = "线程睡眠出错";
//			
//			logger.error(errmsg, e);
//			throw new HideException(500, errmsg);
//		} finally {
//			if(conn!=null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					System.out.println("关闭连接失败");
//				}
//			}
//		}
		
		Map paramM = new HashMap();
		paramM.put("id", id);
		Map m2 = null;
		try {
			Map m1 = (Map)sql.queryForObject("user.query", paramM);  //开启事务管理 NO.4
			System.out.println(m1.get("name"));
			
			Thread.sleep(10000);
			
			m2 = (Map)sql.queryForObject("user.query", paramM);
			System.out.println(m2.get("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			String errmsg = "线程睡眠出错";
			
			logger.error(errmsg, e);
			throw new HideException(500, errmsg);
		} 
			
		Map m = new HashMap();
		m.put("name", m2.get("name"));
		return m;
	}
}
