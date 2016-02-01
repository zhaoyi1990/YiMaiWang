package com.ymw.dao;

import java.util.List;

import com.ymw.model.Easybuy_product_category;
import com.ymw.tools.BaseDao;

public class ProductCategoryDao {
	BaseDao dao = new BaseDao();
	
	/**�����������ݿ�*/
	public int add(Easybuy_product_category epc) {
		return dao.add(epc);
	}

	/**��ѯ�����б�*/
	public List<Easybuy_product_category> query(Integer epc_id) {
		return dao.query(Easybuy_product_category.class, "`epc_parent_id`="+epc_id);
	}

	/**��ѯһ���������*/
	public Easybuy_product_category queryById(Integer epc_id) {
		List<Easybuy_product_category> list = dao.query(Easybuy_product_category.class, "`epc_id`="+epc_id);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}else{
			return null;
		}
	}

	/** �޸�һ����������*/
	public void update(Easybuy_product_category epc) {
		dao.update(epc, "`epc_id`="+epc.getEpc_id());
	}

	/**ɾ�����϶������Ե����*/
	public void delete(Easybuy_product_category epc) {
		dao.delete(epc);
	}
	
}
