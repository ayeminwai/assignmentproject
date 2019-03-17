package com.bluestone.todolistapp.modelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bluestone.todolistapp.model.ToDoTaskData;

public class ToDoTaskDataMapper implements RowMapper<ToDoTaskData> {

	@Override
	public ToDoTaskData mapRow(ResultSet rs, int rowNum) throws SQLException {
		ToDoTaskData result = new ToDoTaskData();
		result.setId(rs.getString("id"));
		result.setName(rs.getString("name"));
		result.setDateTime(rs.getString("date_time"));
		result.setStepId(rs.getString("step_id"));
		
		return result;
	}


}
