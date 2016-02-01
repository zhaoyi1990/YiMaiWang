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

	//========================================������ע�뷽��====������Ӧ�÷���===============================================//
	
	//��Ʒ���������ҳ��
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
	
	//����ҳ��ĸ����б�
	public String add() {
		list = dao.query(0);
		return "add";
	}
	
	//���������
	public String insert() {
		if(dao.add(epc)>0){
			System.out.println("���ӳɹ�");
		}
		return execute();
	}
	
	//��ѯҳ��ĸ����б�
	public String modify(){
		epc = dao.queryById(epc.getEpc_id());
		list = dao.query(0);
		return "modify";
	}
	
	//�޸����
	public String update(){
		dao.update(epc);
		return execute();
	}
	
	//ɾ�����
	public String delete(){
		//ɾ��ѡ�������
		dao.delete(epc);
		//ɾ�������������
		dao.delete(new Easybuy_product_category(epc.getEpc_id()));
		return execute();
	}
}