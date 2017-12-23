package com.zrxjuly.wechat_read.wxmenu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 封装数据库工具类.
 * @author zhangrongxiang
 * @createDate 2017-12-13
 *
 */
public class DBUtil {
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;

	static {
		// 加载properties文件数据.
		// 加载属性资源文件.
		ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
		driverClass = resourceBundle.getString("driverClass");
		url = resourceBundle.getString("url");
		user = resourceBundle.getString("user");
		password = resourceBundle.getString("password");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 得到连接.
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, password);
	}

	// 关闭资源连接.
	public static void closeResource(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

}
