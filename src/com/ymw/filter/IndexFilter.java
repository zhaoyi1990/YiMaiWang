package com.ymw.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ymw.dao.ProductCategoryDao;
import com.ymw.model.Easybuy_product_category;


/**
 * 编码格式过滤器
 */
public class IndexFilter implements Filter {

	private ServletContext application;
    /**
     * Default constructor. 
     */
    public IndexFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* ***************************
		 * 当 第一次打开页面 
		 * 或者 管理员对商品分类进行增删改操作后 
		 * 需要重新查询商品分类列表
		 * *************************/
		if(application.getAttribute("parentCategoryList")==null){
			ProductCategoryDao pcdao = new ProductCategoryDao();
			List<Easybuy_product_category> parentCategoryList = pcdao.query(0);
			application.setAttribute("parentCategoryList", parentCategoryList); 
			if(application.getAttribute("childCategorylist")==null){
				List<Map<String,Object>> childCategorylist = new ArrayList<Map<String,Object>>();
				for (Easybuy_product_category epc: parentCategoryList) {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("name", epc.getEpc_name());
					map.put("list", pcdao.query(epc.getEpc_id()));
					childCategorylist.add(map);
				}
				application.setAttribute("childCategorylist", childCategorylist);
			}
		}
		chain.doFilter(request, response);
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		application = fConfig.getServletContext();
	}

}
