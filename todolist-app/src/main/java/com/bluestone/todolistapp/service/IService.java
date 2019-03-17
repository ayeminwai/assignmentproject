package com.bluestone.todolistapp.service;



import com.bluestone.todolistapp.model.ResponseData;
import com.bluestone.todolistapp.model.ResponseListData;
import com.bluestone.todolistapp.model.UserData;

public interface IService<T> {
	ResponseData<T> save(T data, UserData user);
	ResponseData<T> delete(String id, UserData user);
	ResponseListData<T> getAllData(UserData user);
	ResponseData<T> readDataById(String id, UserData user);
}
