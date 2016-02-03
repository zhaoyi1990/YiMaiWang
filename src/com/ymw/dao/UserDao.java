package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_user;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class UserDao {
	private BaseDao dao = new BaseDao();

	/**��֤�û��˺�����*/
	public Easybuy_user login(Easybuy_user user) {
		List<Easybuy_user> list = dao.query(Easybuy_user.class, 
				"EU_USER_NAME='" + user.getEu_user_name() + "'",
				"EU_PASSWORD='" + user.getEu_password() + "'");
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/** ��֤�û����ظ�*/
	public Integer reg(String username) {
		List<Easybuy_user> list = dao.query(Easybuy_user.class, "EU_USER_NAME='" + username + "'");
		return list.size();
	}

	/** ��ѯ�û��б�*/
	public List<Easybuy_user> query(Pages pages) {
		List<Easybuy_user> list = dao.query(Easybuy_user.class, pages, "eu_status=1");
		return list;
	}

	/** ���ݿ����һ�����û�����*/
	public int add(Easybuy_user user) {
		return dao.add(user);
	}

	/** id��ѯ����*/ 
	public Easybuy_user queryById(Integer id) {
		List<Easybuy_user> list = dao.query(Easybuy_user.class, "eu_user_id=" + id);
		if (list != null && list.size() == 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/** �����û���Ϣ*/ 
	public int update(Easybuy_user user) {
		return dao.update(user, "eu_user_id=" + user.getEu_user_id());
	}
	
	/** ɾ��һ���û�����*/
	public int delete(Easybuy_user user) {
		return dao.delete(user);
	}
}
