package com.bluestone.todolistapp.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bluestone.todolistapp.dao.IDao;
import com.bluestone.todolistapp.model.ToDoStepData;
import com.bluestone.todolistapp.modelMapper.ToDoStepDataMapper;

@Repository
public class ToDoStepDao implements IDao<ToDoStepData> {

	@Override
	@Transactional
	public long insert(ToDoStepData data, JdbcTemplate template) throws SQLException {
		String sql = "select IFNULL(Max(position),0) position from step where user_id=?";
		data.setPosition(template.queryForObject(sql, new Object[] {data.getUserId()}, Integer.class)+1);
		sql = "insert into step(name,position,watch,user_id) values(?,?,?,?)";
		long longResult = template.update(sql, data.getName(), data.getPosition(), data.getWatch(), data.getUserId());
		if(longResult > 0) {
			sql = "select id from step where name=? and user_id=?";
			longResult = template.queryForObject(sql, new Object[] {data.getName(), data.getUserId()}, Long.class);
			
		}
		return longResult;
	}

	@Override
	public int update(ToDoStepData data, JdbcTemplate template) throws SQLException {
		String sql = "update step set name=? where id=?";
		return template.update(sql, data.getName(), data.getId());
	}

	@Override
	public int delete(String id, JdbcTemplate template) throws SQLException {
		String sql = "delete from step where id=?";
		return template.update(sql,id);
	}

	@Override
	public List<ToDoStepData> getAllData(JdbcTemplate template) throws SQLException {
		String sql = "select * from step";
		return template.query(sql,new ToDoStepDataMapper());
	}

	@Override
	public ToDoStepData readDataById(String id, JdbcTemplate template) throws SQLException {
		String sql = "select * from step where id=?";
		return template.queryForObject(sql, new Object[] {id},new ToDoStepDataMapper());
	}
}
