package com.bluestone.todolistapp.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluestone.todolistapp.dao.IDao;
import com.bluestone.todolistapp.model.LoginUserData;
import com.bluestone.todolistapp.modelMapper.LoginUserMapper;
import com.bluestone.todolistapp.utility.EncryptionUtil;

@Repository
public class LoginDao implements IDao<LoginUserData> {

	@Override
	@Transactional
	public long insert(LoginUserData data, JdbcTemplate template) throws SQLException {
		String sql = "insert into user(code,name,password,email,address,phone) value(?,?,?,?,?,?)";
		long longResult = template.update(sql,data.getCode(),data.getName(),EncryptionUtil.encrypt(data.getPassword()),data.getEmail(),data.getAddress(),data.getPhone());
		if(longResult > 0) {
			sql = "select id from user where email=? and phone=?";
			longResult = template.queryForObject(sql, new Object[] { data.getEmail(),data.getPhone() }, Long.class);
		}
		return longResult;
	}

	@Override
	public int update(LoginUserData data, JdbcTemplate template) throws SQLException {
		String sql = "update user set code=?,name=?,password=?,email=?,address=?,phone=? where id=?";
		return template.update(sql,data.getCode(),data.getName(),EncryptionUtil.encrypt(data.getPassword()),data.getEmail(),data.getAddress(),data.getPhone(),data.getId());
	}

	@Override
	public int delete(String id, JdbcTemplate template) throws SQLException {
		String sql = "delete from user where id=?";
		return template.update(sql, id);
	}

	@Override
	public List<LoginUserData> getAllData(JdbcTemplate template) throws SQLException {
		String sql = "select * from user";
		return template.query(sql, new LoginUserMapper());
	}

	@Override
	public LoginUserData readDataById(String id, JdbcTemplate template) throws SQLException {
		String sql = "select * from user where id=?";
		return template.queryForObject(sql, new Object[] { id }, new LoginUserMapper());
	}

	public LoginUserData login(LoginUserData data, JdbcTemplate template) {
		String sql = "select * from user where email=? and password=?";
		return template.queryForObject(sql, new Object[] { data.getEmail(),EncryptionUtil.encrypt(data.getPassword()) }, new LoginUserMapper());
	}

}
