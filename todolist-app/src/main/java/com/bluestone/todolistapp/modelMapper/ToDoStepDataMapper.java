package com.bluestone.todolistapp.modelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bluestone.todolistapp.model.ToDoStepData;

public class ToDoStepDataMapper implements RowMapper<ToDoStepData> {

	@Override
	public ToDoStepData mapRow(ResultSet rs, int rowNum) throws SQLException {
		ToDoStepData result = new ToDoStepData();
		result.setId(rs.getString("id"));
		result.setName(rs.getString("name"));
		result.setPosition(rs.getInt("position"));
		result.setWatch(rs.getInt("watch"));
		result.setUserId(rs.getString("user_id"));
		return result;
	}
}
