package com.ymw.action.b;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;
import com.ymw.tools.Pages;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class UserAction extends RootAction {
	private List<Easybuy_user> list = new ArrayList<Easybuy_user>();
	private Easybuy_user user;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer max;
	private UserDao dao = new UserDao();

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getDay() {
		return day;
	}

	public Integer getMax() {
		return max;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

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
	// public String add(){
	// Integer year = Integer.parseInt(request.getParameter("year"));
	// Integer month = Integer.parseInt(request.getParameter("month"));
	// Integer day = Integer.parseInt(request.getParameter("day"));
	//
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	// Date date = null;
	// try {
	// date = sdf.parse(String.format("%d%02d%02d", year,month,day));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// user.setEu_birthday(date);
	//
	// dao.add(user);
	//
	// return Action.SUCCESS;
	// }

	public String modify() {
		Integer id = Integer.parseInt(request.getParameter("id"));
		user = dao.queryById(id);
		Calendar c = Calendar.getInstance();
		if (user.getEu_birthday() != null) {
			c.setTime(user.getEu_birthday());
		}
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MARCH) + 1;
		day = c.get(Calendar.DATE);

		switch (month) {
		case 2:
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				max = 29;
			} else {
				max = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			max = 30;
			break;
		default:
			max = 31;
			break;
		}

		return "modify";
	}

	public String update() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(String.format("%d%02d%02d", year, month, day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEu_birthday(date);
		user.setEu_status(1);
		dao.update(user);
		return Action.SUCCESS;
	}

	public void delete() {
		Integer id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
		Pages pages = new Pages("easybuy_user", 10);
		String s = request.getParameter("page");
		pages.setCurrentPage(Integer.parseInt(s == null ? "1" : s));
		list = dao.query(pages);
		JSONArray json = JSONArray.fromObject(list);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
}
