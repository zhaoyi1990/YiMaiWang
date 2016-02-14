package com.ymw.action.f;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.UserDao;
import com.ymw.model.Easybuy_user;
import com.ymw.tools.VCode;

public class UserAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419339816285012661L;
	private Easybuy_user user;

	private Integer year;
	private Integer month;
	private Integer day;
	private Integer max;

	private UserDao dao = new UserDao();

	public Easybuy_user getUser() {
		return user;
	}
	public void setUser(Easybuy_user user) {
		this.user = user;
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

	// ========================================上面是注入方法====下面是应用方法===============================================//

	// 用户注册
	public String reg() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(String.format("%d%02d%02d", year, month, day));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setEu_birthday(date);
		user.setEu_status(1);
		if (dao.add(user) > 0) {
			session.setAttribute("user", user);
		}
		return "reg-success";
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
	
	// 密码验证
	public void mmyz(){
		String password = request.getParameter("password");
		user = (Easybuy_user) session.getAttribute("user");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(user.getEu_password().equals(password));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out!=null){
				out.close();							
			}
		}
	}

	// 用户登录
	public String login(){
		if ((user=dao.login(user))!=null) {
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
	
	// 用户登出
	public String logout(){
		session.removeAttribute("user");
		return Action.SUCCESS;
	}

	// 用户资料修改页面数据
	public String modify() {
		user = (Easybuy_user) session.getAttribute("user");
		
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

	// 用户数据修改
	public String update(){
		if(user.getEu_password().length()==0){
			user.setEu_password(null);
		}
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
			System.out.println("修改成功");
		}
		session.setAttribute("user", user);
		
		return "update-success";
	}
	
	//验证码生成
	public void createVCode() {
		VCode vCode = new VCode();
		session.setAttribute("code", vCode.getCode());
		try {
			ImageIO.write(vCode.getImg(), "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void vCodeYZ(){
		String code = (String)session.getAttribute("code");
		String vCode = request.getParameter("vCode").toLowerCase();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(code.equals(vCode));
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
	}
}
