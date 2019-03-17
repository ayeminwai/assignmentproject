package com.bluestone.todolistapp.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluestone.todolistapp.dao.IDao;
import com.bluestone.todolistapp.model.ToDoTaskData;
import com.bluestone.todolistapp.modelMapper.ToDoTaskDataMapper;
import com.bluestone.todolistapp.utility.ServerUtil;

@Repository
public class ToDoTaskDao implements IDao<ToDoTaskData> {

	@Override
	@Transactional
	public long insert(ToDoTaskData data, JdbcTemplate template) throws SQLException {
		String currentDateTime = ServerUtil.getCurrentDateTime();
		String sql = "insert into task(name,date_time,step_id) values(?,?,?)";
		long longResult = template.update(sql, data.getName(), currentDateTime, data.getStepId());
		if (longResult > 0) {
			sql = "select id from task where name=? and date_time=? and step_id=?";
			longResult = template.queryForObject(sql, new Object[] { data.getName(), currentDateTime, data.getStepId() }, Long.class);
		}
		return longResult;
	}

	@Override
	public int update(ToDoTaskData data, JdbcTemplate template) throws SQLException {
		String sql = "update task set name=? where id=?";
		return template.update(sql, data.getName(), data.getId());
	}

	@Override
	public int delete(String id, JdbcTemplate template) throws SQLException {
		String sql = "delete from task where id=?";
		return template.update(sql, id);
	}

	@Override
	public List<ToDoTaskData> getAllData(JdbcTemplate template) throws SQLException {
		String sql = "select * from task";
		return template.query(sql, new ToDoTaskDataMapper());
	}

	@Override
	public ToDoTaskData readDataById(String id, JdbcTemplate template) throws SQLException {
		String sql = "select * from task where id=?";
		return template.queryForObject(sql, new Object[] { id }, new ToDoTaskDataMapper());
	}

	public int moveStep(ToDoTaskData data, JdbcTemplate template) {
		String sql = "update task set step_id=?,date_time=? where id=?";
		return template.update(sql, data.getStepId(), data.getDateTime(), data.getId());
	}

}
