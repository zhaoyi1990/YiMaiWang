package com.ymw.tools;

/**
 * 分页封装
 * */
public class Pages {
	private Integer totalRow; // 总行数
	private Integer pageRow; // 每页行数
	private Integer pageCount; // 页数
	private Integer currentPage;// 当前页数

	public Pages(String tableName, Integer pageRow) {
		super();
		this.totalRow = queryTotalRow(tableName);
		this.pageRow = pageRow;
		this.pageCount = totalRow % pageRow == 0 ? totalRow / pageRow : totalRow / pageRow + 1;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public Integer getPageRow() {
		return pageRow;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	
	public void setCurrentPage(Integer currentPage) {
		//设定页数必须在1到pageCount之间
		if (currentPage == null) { //空值为1
			this.currentPage = 1;  
		} else if (currentPage > this.pageCount) { //超过则取最大页数
			this.currentPage = this.pageCount;
		} else {
			this.currentPage = currentPage;
		}
	}

	/** 当前页第一行*/ 
	public Integer getMin() {
		return (currentPage - 1) * pageRow + 1;
	}

	/** 当前页最后一行*/ 
	public Integer getMax() {
		return currentPage * pageRow;
	}

	/** 查询总行数*/ 
	private Integer queryTotalRow(String tableName) {
		String sql = "select count(*) from `" + tableName + "`";
		Object[] obj = new BaseDao().queryObject(sql);
		if (obj.length == 1) {
			return ((Long) obj[0]).intValue();
		} else {
			return 0;
		}
	}

	/** 上一页*/ 
	public Integer getPageUp() {
		return currentPage > 1 ? currentPage - 1 : 1;
	}

	/** 下一页*/ 
	public Integer getPageDown() {
		return currentPage < pageCount ? currentPage + 1 : pageCount;
	}

	@Override
	public String toString() {
		return "Pages [totalRow=" + totalRow + ", pageRow=" + pageRow + ", pageCount=" + pageCount + ", currentPage="
				+ currentPage + "]";
	}
	
	/** 删除分页记录*/
	public void delete() {
		totalRow--;
		if(totalRow % pageRow == 0){
			pageCount--;
		}	
	}
}
