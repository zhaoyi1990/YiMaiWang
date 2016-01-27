package com.ymw.tools;


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

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	// 当前页第一行
	public Integer getMin() {
		return (currentPage - 1) * pageRow + 1;
	}

	// 当前页最后一行
	public Integer getMax() {
		return currentPage * pageRow;
	}

	// 查询总行数
	private Integer queryTotalRow(String tableName) {
		String sql = "select count(*) from `" +tableName+"`";
		Object[] obj = new BaseDao().queryObject(sql);
		if (obj.length == 1) {
			return ((Long) obj[0]).intValue();
		} else {
			return 0;
		}
	}
	
	//上一页
	public Integer getPageUp(){
		return currentPage>1?currentPage-1:1;
	}
	//下一页
	public Integer getPageDown(){
		return currentPage<pageCount?currentPage+1:pageCount;
	}
}
