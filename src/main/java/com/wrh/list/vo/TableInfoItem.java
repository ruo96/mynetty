package com.wrh.list.vo;

import java.util.List;

public class TableInfoItem{
	private String dbName;
	private String tblName;
	private List<ColsItem> cols;

	public String getDbName(){
		return dbName;
	}

	public String getTblName(){
		return tblName;
	}

	public List<ColsItem> getCols(){
		return cols;
	}
}