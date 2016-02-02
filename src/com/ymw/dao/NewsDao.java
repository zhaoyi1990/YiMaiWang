package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_news;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class NewsDao {
	BaseDao dao = new BaseDao();
	
	/**�������*/
	public int add(Easybuy_news news){
		return dao.add(news);
	}
	/**��ѯ�����б�*/
	public List<Easybuy_news> query(Pages pages) {
		return dao.query(Easybuy_news.class, pages);
	}
	/**��ѯ�����б�*/
	public Easybuy_news queryById(Integer id) {
		List<Easybuy_news> list = dao.query(Easybuy_news.class,"en_id="+id);
		if(list!=null&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**ɾ������*/
	public int delete(Easybuy_news news) {
		return dao.delete(news);
	}
	/**�޸�����*/
	public int update(Easybuy_news news){
		return dao.update(news, "en_id="+news.getEn_id());
	}
}
