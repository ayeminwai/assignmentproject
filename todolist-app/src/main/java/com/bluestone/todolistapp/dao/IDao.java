package com.bluestone.todolistapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IDao<T> {
	long insert(T data, JdbcTemplate template) throws SQLException;
	int update(T data, JdbcTemplate template) throws SQLException;
	int delete(String id, JdbcTemplate template) throws SQLException;
	List<T> getAllData(JdbcTemplate template) throws SQLException;
	T readDataById(String id, JdbcTemplate template) throws SQLException;
}
