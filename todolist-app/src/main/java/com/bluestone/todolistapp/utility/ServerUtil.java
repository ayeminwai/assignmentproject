package com.bluestone.todolistapp.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.bluestone.todolistapp.model.UserData;

public class ServerUtil {
	public static boolean IS_DEV = false;

	public static UserData getLoginUser(HttpServletRequest request) {
		try {
				return EncryptionUtil.decodeToken(request.getHeader("Content-Over"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentDateTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(EncryptionUtil.decrypt("Og5kkhWAf0aU98BRrWptaQ=="));
	}
}
