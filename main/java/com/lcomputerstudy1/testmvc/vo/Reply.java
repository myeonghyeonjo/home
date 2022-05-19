package com.lcomputerstudy1.testmvc.vo;

public class Reply {

	
	private String r_content;
	private String r_date;
	private String r_writer;
	private int r_idx;
	private int r_group;
	private int r_order;
	private int r_depth;
	private int r_replygroup;
	
	public String getr_content() {
		return  r_content;
	}
	public void setr_content(String r_content) {
		this.r_content =  r_content;
	}
	public String getr_writer() {
		return r_writer;
	}
	public void setr_writer(String r_writer) {
		this.r_writer = r_writer;
	}
	public String getr_date() {
		return r_date;
	}
	public void setr_date(String r_date) {
		this.r_date = r_date;
	}
	public int getr_idx() {
		return r_idx;
	}
	public void setr_idx(int r_idx) {
		this.r_idx = r_idx;
	}
	public void setr_order(int r_order) {
		this.r_order=r_order;
	}
	public int getr_order() {
		return r_order;
	}
	public int getr_depth() {
		return r_depth;
	}
	public void setr_depth(int r_depth) {
		this.r_depth=r_depth;
	}
	public void setr_group(int r_group) {
		this.r_group=r_group;
		
	}
	public int getr_group() {
		return r_group;
	}
	public void setr_replygroup(int r_replygroup) {
		this.r_replygroup=r_replygroup;
		
	}
	public int getr_replygroup() {
		return r_replygroup;
	}
	
}