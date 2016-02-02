package com.ymw.action.b;

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


@SuppressWarnings("serial")
public class UserAction extends RootAction {
	private List<Easybuy_user> list;
	private Easybuy_user user;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer max;
	private UserDao dao;
	private Pages pages;
	
	public UserAction() {
		list = new ArrayList<Easybuy_user>();
		dao = new UserDao();
		pages = new Pages("Easybuy_user", 10);
		pages.setTotalRow(pages.getTotalRow()-1);
	}

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
	public List<Easybuy_user> getList() {
		return list;
	}
	public Pages getPages() {
		return pages;
	}
	public void setUser(Easybuy_user user) {
		this.user = user;
	}
	public void setList(List<Easybuy_user> list) {
		this.list = list;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}


	//========================================上面是注入方法====下面是应用方法===============================================//

	//用户管理主页面
	@Override
	public String execute(){
		pages.setCurrentPage(pages.getCurrentPage());
		list = dao.query(pages);
		return Action.SUCCESS;
	}
	
	//用户资料修改页面数据
	public String modify() {
		user = dao.queryById(user.getEu_user_id());
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
		case 4:case 6:case 9:case 11:
			max = 30;
			break;
		default:
			max = 31;
			break;
		}
		return "modify";
	}

	//更新用户资料
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
		if(dao.update(user)>0){
			System.out.println("修改用户成功");
		}
		return execute();
	}
	
	//删除用户资料
	public String delete() {
		if(dao.delete(user)>0){
			System.out.println("删除用户成功");
			pages.delete();
		}
		return execute();
	}
}
