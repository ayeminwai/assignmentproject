package com.bluestone.todolistapp.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bluestone.todolistapp.daoImpl.LoginDao;
import com.bluestone.todolistapp.model.LoginUserData;
import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.UserData;
import com.bluestone.todolistapp.service.IService;
import com.bluestone.todolistapp.utility.EncryptionUtil;
import com.bluestone.todolistapp.utility.StatusUtil;

@Service
public class LoginService implements IService<LoginUserData> {
	private static final Logger log = LogManager.getLogger(LoginService.class);
	@Autowired
	LoginDao dao;
	
	@Autowired
	JdbcTemplate template;

	@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public ResponseData<LoginUserData> save(LoginUserData data, UserData user) {
		ResponseData<LoginUserData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		long id = 0L;

		if (user != null) {
			try (Connection conn = getDataSource().getConnection();) {
				if (data.getId().equals("0")) {
					id = dao.insert(data, template);
					if (id > 0)
						data.setId(id + "");
				} else
					id = dao.update(data, template);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		if (id > 0) {
			result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
			result.setStatus(1);
			result.setToken(EncryptionUtil.generateEncryptedToken(user));
			result.setResult(data);
		}
		return result;
	}

	@Override
	public ResponseData<LoginUserData> delete(String id, UserData user) {
		ResponseData<LoginUserData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		if (user != null) {
			try {
				int effectedRow = dao.delete(id, template);
				if (effectedRow > 0) {
					result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
					result.setStatus(1);
					result.setToken(EncryptionUtil.generateEncryptedToken(user));
				}

			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

		return result;
	}

	@Override
	public ResponseListData<LoginUserData> getAllData(UserData user) {
		ResponseListData<LoginUserData> result = new ResponseListData<>();
		template.setDataSource(getDataSource());
		if (user != null) {
			try {
				result.getResult().addAll(dao.getAllData(template));
				if (result.getResult().size() > 0) {
					result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
					result.setStatus(1);
					result.setToken(EncryptionUtil.generateEncryptedToken(user));
				}

			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}

		return result;
	}

	@Override
	public ResponseData<LoginUserData> readDataById(String id, UserData user) {
		ResponseData<LoginUserData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		if (user != null) {
			try {
				LoginUserData data = dao.readDataById(id, template);
				if (data != null) {
					result.setResult(data);
					result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
					result.setStatus(1);
					result.setToken(EncryptionUtil.generateEncryptedToken(user));
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return result;
	}

	public ResponseData<LoginUserData> login(LoginUserData data, UserData user) {
		ResponseData<LoginUserData> result = new ResponseData<>();
		template.setDataSource(getDataSource());
		try {
			result.setResult(dao.login(data, template));
			if (!result.getResult().getId().equals("0")) {
				result.setMessage(StatusUtil.RepStatus.SUCCESS.name());
				result.setStatus(1);
				result.setToken(EncryptionUtil.generateEncryptedTokenForLogin(data));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return result;
	}
}
