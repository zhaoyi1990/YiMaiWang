package com.ymw.action.b;

import java.util.List;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.NewsDao;
import com.ymw.model.Easybuy_news;
import com.ymw.tools.Pages;

public class NewsAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7456454884553235124L;
	
	private Easybuy_news news;
	private List<Easybuy_news> list;
	
	private Pages pages = new Pages("Easybuy_news", 5);
	
	private NewsDao dao = new NewsDao();
	
	public Easybuy_news getNews() {
		return news;
	}
	public Pages getPages() {
		return pages;
	}
	public List<Easybuy_news> getList() {
		return list;
	}
	public void setNews(Easybuy_news news) {
		this.news = news;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public void setList(List<Easybuy_news> list) {
		this.list = list;
	}
	
	//========================================上面是注入方法====下面是应用方法===============================================//
	@Override
	public String execute() {
		pages.setCurrentPage(pages.getCurrentPage());
		list = dao.query(pages);
		
		return Action.SUCCESS;
	}
	
	//添加新闻
	public String insert(){
		if(dao.add(news)>1){
			System.out.println("添加新闻成功");
		}
		return execute();
	}
	
	//删除新闻
	public String delete(){
		if(dao.delete(news)>1){
			System.out.println("删除新闻成功");
		}
		return execute();
	}
	
	//更新页面默认数据
	public String modify(){
		news = dao.queryById(news.getEn_id());
		return "modify";
	}
	
	//修改新闻
	public String update(){
		if(dao.update(news)>0){
			System.out.println("修改新闻成功");
		}
		return execute();
	}
	
}
