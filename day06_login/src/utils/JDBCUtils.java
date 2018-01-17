package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * C3P0工具类-- 提供方法获得连接\提供方法获得数据源
 *
 */
public class JDBCUtils {

	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	// 添加线程局部变量
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

	/**
	 * 提供方法获得连接
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 从本地线程变量ThreadLocal中获取连接
		Connection conn = local.get();

		// 是第一次获取
		if (conn == null) {
			// 从连接池获得一个连接
			conn = dataSource.getConnection();
			// 将连接添加到本地线程变量中共享
			local.set(conn);
		}

		return conn;
	}

	/**
	 * 提供方法获得数据源
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 提交事务&关闭连接＆从本地线程变量移除连接
	 * 
	 * @param conn
	 */
	public static void commitAndClose(Connection conn) {
		try {
			// 提交事务
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 从本地线程变量移除连接
				local.remove();
			}

		}

	}

	/**
	 * 回滚事务&关闭连接＆从本地线程变量移除连接
	 * 
	 * @param conn
	 */
	public static void rollbackAndClose(Connection conn) {
		try {
			// 回滚事务
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 从本地线程变量移除连接
				local.remove();
			}

		}
	}

}
