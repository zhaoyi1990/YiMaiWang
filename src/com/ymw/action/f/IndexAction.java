package com.ymw.action.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.NewsDao;
import com.ymw.dao.ProductCategoryDao;
import com.ymw.model.Easybuy_news;
import com.ymw.model.Easybuy_product_category;
import com.ymw.model.Easybuy_user;
import com.ymw.tools.Pages;

public class IndexAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 555221060422584908L;
	private List<Easybuy_news> enlist;
	
	public List<Easybuy_news> getEnlist(){
		return enlist;
	}
	//========================================上面是注入方法====下面是应用方法===============================================//
	@Override
	public String execute(){
		Pages pages = new Pages("Easybuy_news", 10);
		pages.setCurrentPage(pages.getCurrentPage());
		NewsDao ndao = new NewsDao();
		enlist = ndao.query(pages);
		
		return Action.SUCCESS;
	}
	
}
