package com.library.manage.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors {
	String msg;
	List<String> detail;
	HttpStatus status;
	LocalDateTime datetime;
	
	public ApiErrors() {
		super();
	}

	public ApiErrors(String msg, List<String> detail, HttpStatus status, LocalDateTime datetime) {
		super();
		this.msg = msg;
		this.detail = detail;
		this.status = status;
		this.datetime = datetime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<String> getDetail() {
		return detail;
	}

	public void setDetail(List<String> detail) {
		this.detail = detail;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	
}
