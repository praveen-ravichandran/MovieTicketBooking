package com.ticketbooking.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ticketbooking.user.constant.DBQueryConst;
import com.ticketbooking.user.model.UserModel;

public class UserDao {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public UserModel getUserPassword(String userName) {
		List<UserModel> users = template.query(DBQueryConst.GET_USER_PASSWORD, new Object[] { userName }, new RowMapper<UserModel>() {
			public UserModel mapRow(ResultSet rs, int row) throws SQLException {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("user_id"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		if(users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
