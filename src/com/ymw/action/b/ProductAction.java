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
	
	//用于分页注入
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

	//文件注入 
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	//========================================上面是注入方法====下面是应用方法===============================================//

	@Override
	public String execute() {
		plist = pcdao.query(0);
		pages.setCurrentPage(pages.getCurrentPage());
		if(cid!=null){ 					//当有选中二级分类时生成此类别的列表
			clist = pcdao.query(pid); 	//二级分类对象列表
			
			pages.newPages("Easybuy_product", "epc_child_id="+cid); //生成新的分页对象 下同
			pages.setCurrentPage(pages.getCurrentPage()); 			//防止当前页超出新分页 重置当前页 下同
			
			list = dao.query(pages, "epc_child_id="+cid);
			listName = pcdao.queryById(cid).getEpc_name();
			
		}else if(pid!=null&&pid>0){		//当有选中一级分类时生成此类别的列表
			clist = pcdao.query(pid);	//一级级分类对象列表
			
			pages.newPages("Easybuy_product", "epc_id="+pid);
			pages.setCurrentPage(pages.getCurrentPage());
			
			list = dao.query(pages,"epc_id="+pid);
			listName = pcdao.queryById(pid).getEpc_name();
		}else{
			list = dao.query(pages);	//默认全部列表
			listName="全部商品";
		}

		return Action.SUCCESS;
	}
	
	// 添加商品页面默认分类列表
	public String add(){
		plist = pcdao.query(0);
		clist =	pcdao.query(1);
		return "add";
	}
	// 插入商品数据
	public String insert(){
		product.setEp_file_name(upload());
		if(dao.add(product)>0){
			System.out.println("添加商品成功");
		}
		return execute();
	}
	
	// 删除商品数据
	public String delete(){
		if(dao.delete(product)>0){
			pages.delete();
			System.out.println("删除商品成功");
		}
		return execute();
	}
	
	// 添加商品页面默认数据
	public String modify(){
		product = dao.queryById(product.getEp_id());
		plist = pcdao.query(0);
		clist =	pcdao.query(product.getEpc_id());
		return "modify";
	}
	
	public String update(){
		product.setEp_file_name(upload());
		if(dao.update(product)>0){
			System.out.println("修改商品成功");
		}
		return execute();
	}
	
	// 分类联动
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
	
	/** 上传文件保存到项目*/
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
	
	public String product(){
		Easybuy_product_category epc = pcdao.queryById(product.getEpc_id());
		
		epc.getEpc_name();
		
		return Action.SUCCESS;
		
	}
}
