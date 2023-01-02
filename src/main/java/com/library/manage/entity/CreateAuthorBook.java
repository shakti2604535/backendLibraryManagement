package com.library.manage.entity;

import java.util.List;

public class CreateAuthorBook {
	
	private List<AuthorAllBooks> ab;
	private int ps;
	private long te;
	public List<AuthorAllBooks> getAb() {
		return ab;
	}
	public void setAb(List<AuthorAllBooks> ab) {
		this.ab = ab;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public long getTe() {
		return te;
	}
	public void setTe(long l) {
		this.te = l;
	}
	

}
