package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_product_category;
import com.ymw.tools.BaseDao;

public class ProductCategoryDao {
	BaseDao dao = new BaseDao();
	
	/**新类别插入数据库*/
	public int add(Easybuy_product_category epc) {
		return dao.add(epc);
	}

	/**查询分类列表*/
	public List<Easybuy_product_category> query(Integer epc_id) {
		return dao.query(Easybuy_product_category.class, "`epc_parent_id`="+epc_id);
	}

	/**查询一个类别数据*/
	public Easybuy_product_category queryById(Integer epc_id) {
		List<Easybuy_product_category> list = dao.query(Easybuy_product_category.class, "`epc_id`="+epc_id);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}

	/** 修改一个类别的数据*/
	public void update(Easybuy_product_category epc) {
		dao.update(epc, "`epc_id`="+epc.getEpc_id());
	}

	/**删除符合对象属性的类别*/
	public void delete(Easybuy_product_category epc) {
		dao.delete(epc);
	}
	
}
