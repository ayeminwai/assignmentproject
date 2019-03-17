package com.bluestone.todolistapp.modelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bluestone.todolistapp.model.LoginUserData;

public class LoginUserMapper implements RowMapper<LoginUserData> {

	@Override
	public LoginUserData mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoginUserData loginUser = new LoginUserData();
        
        loginUser.setId(rs.getString("id"));
        loginUser.setCode(rs.getString("code"));
        loginUser.setName(rs.getString("name"));
        loginUser.setEmail(rs.getString("email"));
        loginUser.setAddress(rs.getString("address"));
        loginUser.setPhone(rs.getString("phone"));
        loginUser.setPassword(rs.getString("password"));
 
        return loginUser;
	}

}
