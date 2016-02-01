package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_product;
import com.ymw.tools.BaseDao;
import com.ymw.tools.Pages;

public class ProductDao {
	BaseDao dao = new BaseDao();
	
	public int add(Easybuy_product product){
		return dao.add(product);
	}
	
	public List<Easybuy_product> query(Pages pages) {
		return dao.query(Easybuy_product.class, pages);
	}
}
