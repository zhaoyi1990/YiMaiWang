package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_user;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class UserDao {
	private BaseDao dao = new BaseDao();
	//验证用户账号密码
	public boolean login(Easybuy_user user) {
		BaseDao dao = new BaseDao();
		List<Easybuy_user> list = dao.query(Easybuy_user.class, "EU_USER_NAME='" + user.getEu_user_name() + "'");
		if (list != null && list.size() == 1) {
			Easybuy_user user2 = list.get(0);
			user.setEu_user_id(user2.getEu_user_id());
			user.setEu_name(user2.getEu_name());
			user.setEu_status(user2.getEu_status());
			return true;
		} else {
			return false;
		}
	}
	
	public List<Easybuy_user> query(Pages pages){
		List<Easybuy_user> list = dao.query(Easybuy_user.class, pages,"eu_status=1");
		return list;
	}
	
	public void add(Easybuy_user user){
		dao.add(user);
	}
}
