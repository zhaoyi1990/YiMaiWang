package com.ymw.action.f;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;

public class LoginAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8122983541586190549L;
	private Easybuy_user user;

	public Easybuy_user getUser() {
		return user;
	}

	public void setUser(Easybuy_user user) {
		this.user = user;
	}
	
	//========================================上面是注入方法====下面是应用方法===============================================//

	//登陆验证
	@Override
	public String execute(){
		UserDao dao = new UserDao();
		if (dao.login(user)) {
			session.setAttribute("user", user);
			if (user.getEu_status() == 2) {
				return "manage";
			} else {
				return Action.SUCCESS;
			}
		} else {
			request.setAttribute("error", "您输入的用户名或密码错误，请重新输入。");
			return Action.ERROR;
		}
	}
}
