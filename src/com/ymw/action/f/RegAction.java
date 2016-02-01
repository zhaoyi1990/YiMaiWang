package com.ymw.action.f;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;

public class RegAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419339816285012661L;
	private Easybuy_user user;

	private UserDao dao = new UserDao();

	public Easybuy_user getUser() {
		return user;
	}

	public void setUser(Easybuy_user user) {
		this.user = user;
	}
	
	//========================================上面是注入方法====下面是应用方法===============================================//
	
	//用户添加
	public String add() {
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer day = Integer.parseInt(request.getParameter("day"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(String.format("%d%02d%02d", year, month, day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEu_birthday(date);
		user.setEu_status(1);
		if(dao.add(user)>0){
			session.setAttribute("user", user);
		}
		return Action.SUCCESS;
	}

	// 用户名验证
	public void yz() {
		PrintWriter out = null;
		Integer result = dao.reg(request.getParameter("username"));
		try {
			out = response.getWriter();
			out.print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
