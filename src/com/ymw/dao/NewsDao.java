package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_news;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class NewsDao {
	BaseDao dao = new BaseDao();
	
	/**添加新闻*/
	public int add(Easybuy_news news){
		return dao.add(news);
	}
	/**查询新闻列表*/
	public List<Easybuy_news> query(Pages pages) {
		return dao.query(Easybuy_news.class, pages);
	}
	/**查询新闻列表*/
	public Easybuy_news queryById(Integer id) {
		List<Easybuy_news> list = dao.query(Easybuy_news.class,"en_id="+id);
		if(list!=null&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**删除新闻*/
	public int delete(Easybuy_news news) {
		return dao.delete(news);
	}
	/**修改新闻*/
	public int update(Easybuy_news news){
		return dao.update(news, "en_id="+news.getEn_id());
	}
}
