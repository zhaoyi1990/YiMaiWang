package com.ymw.tools;

/**
 * ��ҳ��װ
 * */
public class Pages {
	private Integer totalRow; // ������
	private Integer pageRow; // ÿҳ����
	private Integer pageCount; // ҳ��
	private Integer currentPage;// ��ǰҳ��

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
		//�趨ҳ��������1��pageCount֮��
		if (currentPage == null) { //��ֵΪ1
			this.currentPage = 1;  
		} else if (currentPage > this.pageCount) { //������ȡ���ҳ��
			this.currentPage = this.pageCount;
		} else {
			this.currentPage = currentPage;
		}
	}

	/** ��ǰҳ��һ��*/ 
	public Integer getMin() {
		return (currentPage - 1) * pageRow + 1;
	}

	/** ��ǰҳ���һ��*/ 
	public Integer getMax() {
		return currentPage * pageRow;
	}

	/** ��ѯ������*/ 
	private Integer queryTotalRow(String tableName) {
		String sql = "select count(*) from `" + tableName + "`";
		Object[] obj = new BaseDao().queryObject(sql);
		if (obj.length == 1) {
			return ((Long) obj[0]).intValue();
		} else {
			return 0;
		}
	}

	/** ��һҳ*/ 
	public Integer getPageUp() {
		return currentPage > 1 ? currentPage - 1 : 1;
	}

	/** ��һҳ*/ 
	public Integer getPageDown() {
		return currentPage < pageCount ? currentPage + 1 : pageCount;
	}

	@Override
	public String toString() {
		return "Pages [totalRow=" + totalRow + ", pageRow=" + pageRow + ", pageCount=" + pageCount + ", currentPage="
				+ currentPage + "]";
	}
	
	/** ɾ����ҳ��¼*/
	public void delete() {
		totalRow--;
		if(totalRow % pageRow == 0){
			pageCount--;
		}	
	}
}
