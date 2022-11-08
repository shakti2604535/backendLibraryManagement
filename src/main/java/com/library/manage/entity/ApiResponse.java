package com.library.manage.entity;

public class ApiResponse<T> {
	int recordcount;
	T response;
	public int getRecordcount() {
		return recordcount;
	}
	public void setRecordcount(int recordcount) {
		this.recordcount = recordcount;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public ApiResponse(int recordcount, T response) {
		super();
		this.recordcount = recordcount;
		this.response = response;
	}

}
