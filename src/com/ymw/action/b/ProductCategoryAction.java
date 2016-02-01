package com.ymw.action.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.ProductCategoryDao;
import com.ymw.model.Easybuy_product_category;

public class ProductCategoryAction extends RootAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7446261972104788375L;

	private List<Easybuy_product_category> list;
	private List<Map<String, Object>> tree;
	private ProductCategoryDao dao;
	private Easybuy_product_category epc;

	public List<Map<String, Object>> getTree() {
		return tree;
	}

	public void setTree(List<Map<String, Object>> tree) {
		this.tree = tree;
	}

	public Easybuy_product_category getEpc() {
		return epc;
	}

	public void setEpc(Easybuy_product_category epc) {
		this.epc = epc;
	}

	public ProductCategoryAction() {
		dao = new ProductCategoryDao();
	}

	public List<Easybuy_product_category> getList() {
		return list;
	}

	public void setList(List<Easybuy_product_category> list) {
		this.list = list;
	}

	//========================================上面是注入方法====下面是应用方法===============================================//
	
	//商品分类管理主页面
	@Override
	public String execute() {
		tree = new ArrayList<Map<String, Object>>();
		List<Easybuy_product_category> plist = dao.query(0);
		for (Easybuy_product_category pepc : plist) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("epc", pepc);
			List<Easybuy_product_category> clist = dao.query(pepc.getEpc_id());
			map.put("clist", clist);
			tree.add(map);
		}
		return Action.SUCCESS;
	}
	
	//添加页面的父类列表
	public String add() {
		list = dao.query(0);
		return "add";
	}
	
	//添加新类别
	public String insert() {
		if(dao.add(epc)>0){
			System.out.println("添加成功");
		}
		return execute();
	}
	
	//查询页面的父类列表
	public String modify(){
		epc = dao.queryById(epc.getEpc_id());
		list = dao.query(0);
		return "modify";
	}
	
	//修改类别
	public String update(){
		dao.update(epc);
		return execute();
	}
	
	//删除类别
	public String delete(){
		//删除选定的类别
		dao.delete(epc);
		//删除这个类别的子类
		dao.delete(new Easybuy_product_category(epc.getEpc_id()));
		return execute();
	}
}
