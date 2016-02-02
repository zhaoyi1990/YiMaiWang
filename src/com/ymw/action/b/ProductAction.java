package com.ymw.action.b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.ymw.action.RootAction;
import com.ymw.dao.ProductCategoryDao;
import com.ymw.dao.ProductDao;
import com.ymw.model.Easybuy_product;
import com.ymw.model.Easybuy_product_category;
import com.ymw.tools.Pages;

import net.sf.json.JSONArray;

public class ProductAction extends RootAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3864260022347168924L;
	private List<Easybuy_product> list;
	private Easybuy_product product;
	private List<Easybuy_product_category> plist;
	private List<Easybuy_product_category> clist;
	private Pages pages;
	
	private Integer pid;
	private Integer cid;
	private String listName;
	
	private ProductCategoryDao pcdao;
	private ProductDao dao;
	
	private File file;
	@SuppressWarnings("unused")
	private String fileFileName;
	@SuppressWarnings("unused")
	private String fileContentType;
	
		
	public ProductAction() {
		pages = new Pages("Easybuy_product", 5);
		pcdao = new ProductCategoryDao();
		dao = new ProductDao();
	}
	
	public Pages getPages() {
		return pages;
	}
	public Easybuy_product getProduct() {
		return product;
	}
	public List<Easybuy_product> getList() {
		return list;
	}
	public List<Easybuy_product_category> getPlist() {
		return plist;
	}
	public List<Easybuy_product_category> getClist() {
		return clist;
	}
	public Integer getPid() {
		return pid;
	}
	public Integer getCid() {
		return cid;
	}
	public String getListName(){
		return listName;
	}
	
	//���ڷ�ҳע��
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public void setProduct(Easybuy_product product) {
		this.product = product;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	//�ļ�ע�� 
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	//========================================������ע�뷽��====������Ӧ�÷���===============================================//

	@Override
	public String execute() {
		plist = pcdao.query(0);
		pages.setCurrentPage(pages.getCurrentPage());
		if(cid!=null){ 					//����ѡ�ж�������ʱ���ɴ������б�
			clist = pcdao.query(pid); 	//������������б�
			
			pages.newPages("Easybuy_product", "epc_child_id="+cid); //�����µķ�ҳ���� ��ͬ
			pages.setCurrentPage(pages.getCurrentPage()); 			//��ֹ��ǰҳ�����·�ҳ ���õ�ǰҳ ��ͬ
			
			list = dao.query(pages, "epc_child_id="+cid);
			listName = pcdao.queryById(cid).getEpc_name();
			
		}else if(pid!=null&&pid>0){		//����ѡ��һ������ʱ���ɴ������б�
			clist = pcdao.query(pid);	//һ������������б�
			
			pages.newPages("Easybuy_product", "epc_id="+pid);
			pages.setCurrentPage(pages.getCurrentPage());
			
			list = dao.query(pages,"epc_id="+pid);
			listName = pcdao.queryById(pid).getEpc_name();
		}else{
			list = dao.query(pages);	//Ĭ��ȫ���б�
			listName="ȫ����Ʒ";
		}
		
		return Action.SUCCESS;
	}
	
	// �����Ʒҳ��Ĭ�Ϸ����б�
	public String add(){
		plist = pcdao.query(0);
		clist =	pcdao.query(1);
		return "add";
	}
	// ������Ʒ����
	public String insert(){
		product.setEp_file_name(upload());
		if(dao.add(product)>0){
			System.out.println("�����Ʒ�ɹ�");
		}
		return execute();
	}
	
	// ɾ����Ʒ����
	public String delete(){
		if(dao.delete(product)>0){
			pages.delete();
			System.out.println("ɾ����Ʒ�ɹ�");
		}
		return execute();
	}
	
	// �����Ʒҳ��Ĭ������
	public String modify(){
		product = dao.queryById(product.getEp_id());
		plist = pcdao.query(0);
		clist =	pcdao.query(product.getEpc_id());
		return "modify";
	}
	
	public String update(){
		product.setEp_file_name(upload());
		if(dao.update(product)>0){
			System.out.println("�޸���Ʒ�ɹ�");
		}
		return execute();
	}
	
	// ��������
	public void clist(){
		clist = pcdao.query(pid);
		JSONArray json = JSONArray.fromObject(clist);
		try {
			PrintWriter out = response.getWriter();
			out.println(json.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** �ϴ��ļ����浽��Ŀ*/
	private String upload(){
		if(file==null){
			return null;
		}
		
		String fileName = System.currentTimeMillis()+".jpg";
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			is = new FileInputStream(file);
			bis = new BufferedInputStream(is);
			
			String realPath = ServletActionContext.getServletContext().getRealPath("/");
			
			File product_file = new File(realPath+"/images/product/"+fileName); 
			os = new FileOutputStream(product_file);
			bos = new BufferedOutputStream(os);
			int len;
			byte[] b = new byte[1024];
			while((len=bis.read(b))!=-1){
				bos.write(b,0,len);
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bos!=null){
					bos.close();				
				}
				if(os!=null){
					os.close();				
				}
				if(bis!=null){
					bis.close();				
				}
				if(is!=null){
					is.close();				
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
}
