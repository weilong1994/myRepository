package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.User;
import utils.JDBCUtils;

public class LoginDao {

	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from category where username=? and pwd=?";
		
		return qr.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
