package com.ymw.action.b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class UserAction extends RootAction {
	private List<Easybuy_user> list = new ArrayList<Easybuy_user>();
	private Easybuy_user user;
	private UserDao dao = new UserDao();
	
	public Easybuy_user getUser() {
		return user;
	}

	public void setUser(Easybuy_user user) {
		this.user = user;
	}

	public List<Easybuy_user> getList() {
		return list;
	}

	public void setList(List<Easybuy_user> list) {
		this.list = list;
	}
	@Override
	public String execute() throws Exception {
		
		Pages pages = new Pages("easybuy_user", 10);
		String s = request.getParameter("page");
		pages.setCurrentPage(Integer.parseInt(s == null ? "1" : s));
		list = dao.query(pages);
		return Action.SUCCESS;
	}
	public String add(){
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer day = Integer.parseInt(request.getParameter("day"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(String.format("%d%02d%02d", year,month,day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEu_birthday(date);

		dao.add(user);
		
		return Action.SUCCESS;
	}
}
