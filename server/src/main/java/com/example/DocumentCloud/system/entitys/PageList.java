package com.autumn.system.entitys;
 
import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
 
	private int page;
	private int totalRows;
	private int pages;
	private List<T> list=new ArrayList<T>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		if(list==null){
			list=new ArrayList<T>();
		}
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
}
