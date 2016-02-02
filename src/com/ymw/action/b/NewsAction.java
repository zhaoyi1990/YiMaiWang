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
	
	//========================================������ע�뷽��====������Ӧ�÷���===============================================//
	@Override
	public String execute() {
		pages.setCurrentPage(pages.getCurrentPage());
		list = dao.query(pages);
		
		return Action.SUCCESS;
	}
	
	//�������
	public String insert(){
		if(dao.add(news)>1){
			System.out.println("������ųɹ�");
		}
		return execute();
	}
	
	//ɾ������
	public String delete(){
		if(dao.delete(news)>1){
			System.out.println("ɾ�����ųɹ�");
		}
		return execute();
	}
	
	//����ҳ��Ĭ������
	public String modify(){
		news = dao.queryById(news.getEn_id());
		return "modify";
	}
	
	//�޸�����
	public String update(){
		if(dao.update(news)>0){
			System.out.println("�޸����ųɹ�");
		}
		return execute();
	}
	
}
