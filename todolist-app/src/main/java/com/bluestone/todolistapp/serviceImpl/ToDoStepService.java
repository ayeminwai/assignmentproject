package com.bluestone.todolistapp.serviceImpl;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bluestone.todolistapp.daoImpl.ToDoStepDao;
import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.ToDoStepData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.service.IService;
import com.bluestone.todolistapp.utility.EncryptionUtil;
import com.bluestone.todolistapp.utility.StatusUtil;

@Service
public class ToDoStepService implements IService<ToDoStepData> {
	private static final Logger log = LogManager.getLogger(ToDoStepService.class);
	@Autowired
	ToDoStepDao dao;

	@Autowired
	JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	public ResponseData<ToDoStepData> save(ToDoStepData data, UserData user) {
		ResponseData<ToDoStepData> result = new ResponseData<>();
		long id = 0L;
		template.setDataSource(getDataSource());
		
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
				log.error(e.getClass() +" :: "+ e.getMessage());
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
	public ResponseData<ToDoStepData> delete(String id, UserData user) {
		ResponseData<ToDoStepData> result = new ResponseData<>();
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
				log.error(e.getClass() + " :: "+e.getMessage());
			}
		}
		return result;
	}

	@Override
	public ResponseListData<ToDoStepData> getAllData(UserData user) {
		ResponseListData<ToDoStepData> result = new ResponseListData<>();
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
	public ResponseData<ToDoStepData> readDataById(String id, UserData user) {
		ResponseData<ToDoStepData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		
		if(user != null) {
			try {
				ToDoStepData data = dao.readDataById(id, template);
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
	
}
