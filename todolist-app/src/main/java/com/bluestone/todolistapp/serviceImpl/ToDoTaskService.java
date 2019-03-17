package com.bluestone.todolistapp.serviceImpl;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bluestone.todolistapp.daoImpl.ToDoTaskDao;
import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.ToDoTaskData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.service.IService;
import com.bluestone.todolistapp.utility.EncryptionUtil;
import com.bluestone.todolistapp.utility.ServerUtil;
import com.bluestone.todolistapp.utility.StatusUtil;

@Service
public class ToDoTaskService implements IService<ToDoTaskData> {
	private static final Logger log = LogManager.getLogger(ToDoTaskService.class);
	@Autowired
	ToDoTaskDao dao;

	@Autowired
	JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	public ResponseData<ToDoTaskData> save(ToDoTaskData data, UserData user) {
		ResponseData<ToDoTaskData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		long id = 0L; 
		if(user != null) {
			try {
				if(data.getId().equals("0")) {
					id = dao.insert(data, template);
					if(id > 0)
						data.setId(id+"");
				}else {
					id = dao.update(data, template);
				}
				
			} catch (Exception e) {
				log.error(e.getClass() + " :: " + e.getMessage());
			}
		}
		
		if(id > 0) {
			result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
			result.setStatus(1);
			result.setToken(EncryptionUtil.generateEncryptedToken(user));
			result.setResult(data);
		}
		
		return result;
	}

	@Override
	public ResponseData<ToDoTaskData> delete(String id, UserData user) {
		ResponseData<ToDoTaskData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		
		if(user != null) {
			try {
				int effectedRow = dao.delete(id, template);
				if (effectedRow > 0) {
					result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
					result.setStatus(1);
					result.setToken(EncryptionUtil.generateEncryptedToken(user));
				}
			} catch (Exception e) {
				log.error(e.getClass() + " :: " + e.getMessage());
			}
		}
		
		return result;
	}

	@Override
	public ResponseListData<ToDoTaskData> getAllData(UserData user) {
		ResponseListData<ToDoTaskData> result = new ResponseListData<>();
		template.setDataSource(getDataSource());
		
		try {
			result.getResult().addAll(dao.getAllData(template));
			if (result.getResult().size() > 0) {
				result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
				result.setStatus(1);
				result.setToken(EncryptionUtil.generateEncryptedToken(user));
			}
		} catch (Exception e) {
			log.error(e.getClass() +" :: "+ e.getMessage());
		}
		return result;
	}

	@Override
	public ResponseData<ToDoTaskData> readDataById(String id, UserData user) {
		ResponseData<ToDoTaskData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		
		if(user != null) {
			try {
				ToDoTaskData data = dao.readDataById(id, template);
				if (data != null) {
					result.setResult(data);
					result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
					result.setStatus(1);
					result.setToken(EncryptionUtil.generateEncryptedToken(user));
				}
			} catch (Exception e) {
				log.error(e.getClass() + " :: "+ e.getMessage());
			}
		}
		
		return result;
	}

	public ResponseData<ToDoTaskData> moveStep(ToDoTaskData data, UserData user) {
		ResponseData<ToDoTaskData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		int effectedRow = 0;
		if(user != null) {
			try {
				data.setDateTime(ServerUtil.getCurrentDateTime());
				effectedRow = dao.moveStep(data, template);
			} catch (Exception e) {
				log.error(e.getClass() + " :: " + e.getMessage());
			}
		}
		
		if (effectedRow > 0) {
			result.setResult(data);
			result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
			result.setStatus(1);
			result.setToken(EncryptionUtil.generateEncryptedToken(user));
		}
		
		return result;
	}

}
