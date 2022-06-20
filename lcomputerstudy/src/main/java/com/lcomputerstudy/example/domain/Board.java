package com.lcomputerstudy.example.domain;

public class Board {
	private int bId;
	private String title;
	private String content;
	private String writer;
	private String bDateTime;
	
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbTitle() {
		return title;
	}
	public void setbTitle(String title) {
		this.title = title;
	}
	public String getbContent() {
		return content;
	}
	public void setbContent(String content) {
		this.content = content;
	}
	public String getbWriter() {
		return writer;
	}
	public void setbWriter(String writer) {
		this.writer = writer;
	}
	public String getbDateTime() {
		return bDateTime;
	}
	public void setbDateTime(String bDateTime) {
		this.bDateTime = bDateTime;
	}
}
