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
	
	//id查询对象
	public Easybuy_user queryById(Integer id){
		List<Easybuy_user> list = dao.query(Easybuy_user.class, "eu_user_id="+id);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	// 更新用户星系
	public void update(Easybuy_user user) {
		dao.update(user, "eu_user_id="+user.getEu_user_id());
	}

	public void delete(Integer id) {
		Easybuy_user user = new Easybuy_user();
		user.setEu_user_id(id);
		dao.delete(user);
	}
}
