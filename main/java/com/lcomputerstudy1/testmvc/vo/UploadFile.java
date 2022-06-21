package com.lcomputerstudy1.testmvc.vo;

public class UploadFile {
	
	
	private String f_title;
	private int f_group;
	private int f_idx;
	private String f_filename;
	private String f_fileRealname;
	
	public String getf_title() {
		return  f_title;
	}
	public void setf_title(String f_title) {
		this.f_title =  f_title;
	}
	public int getf_group() {
		return f_group;
	}
	public void setf_group(int f_group) {
		this.f_group = f_group;
	}
	
	public int getf_idx() {
		return f_idx;
	}
	public void setf_idx(int f_idx) {
		this.f_idx = f_idx;
	}
	public void setf_filename(String f_filename) {
		this.f_filename = f_filename;
		
	}
	public String getf_filename() {
		return f_filename;
	}
	public void setf_fileRealname(String f_fileRealname) {
		this.f_fileRealname = f_fileRealname;
		
	}
	public String getf_fileRealname() {
		return f_fileRealname;
	}
}