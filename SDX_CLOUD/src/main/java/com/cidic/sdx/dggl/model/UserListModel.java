package com.cidic.sdx.dggl.model;

import java.util.List;

public class UserListModel {

	private List<User> list;
	private long count;
	
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
