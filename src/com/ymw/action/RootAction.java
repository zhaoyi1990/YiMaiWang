package com.ymw.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RootAction extends ActionSupport{
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response= ServletActionContext.getResponse();
	protected HttpSession session = ServletActionContext.getRequest().getSession();
	protected ServletContext application = ServletActionContext.getServletContext();
	
}
