package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_product;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class ProductDao {
	BaseDao dao = new BaseDao();
	
	/**������Ʒ����*/
	public int add(Easybuy_product product){
		return dao.add(product);
	}
	
	/**��������ҳ��ѯ*/
	public List<Easybuy_product> query(Pages pages) {
		return dao.query(Easybuy_product.class, pages);
	}
	
	/**������ҳ��ѯ*/
	public List<Easybuy_product> query(Pages pages,String where) {
		return dao.query(Easybuy_product.class, pages,where);
	}

	/**ɾ����Ʒ����*/
	public int delete(Easybuy_product product) {
		return dao.delete(product);
	}
	
	/**id��ѯ��Ʒ*/
	public Easybuy_product queryById(Integer id){
		List<Easybuy_product> list = dao.query(Easybuy_product.class, "ep_id="+id);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**�޸���Ʒ����*/
	public int update(Easybuy_product product) {
		return dao.update(product,"ep_id="+product.getEp_id());
	}
	
}
