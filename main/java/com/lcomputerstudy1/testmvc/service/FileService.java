package com.lcomputerstudy1.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy1.testmvc.dao.BoardDAO;
import com.lcomputerstudy1.testmvc.dao.FileDAO;
import com.lcomputerstudy1.testmvc.vo.Pagination;
import com.lcomputerstudy1.testmvc.vo.User;
import com.lcomputerstudy1.testmvc.vo.Board;
import com.lcomputerstudy1.testmvc.vo.File;

public class FileService {
	
	private static FileService service = null;
	private static FileDAO dao = null;
    
	private FileService() {
		
	}

	public static FileService getInstance() {
		if(service == null) {
			service = new FileService();
			dao = FileDAO.getInstance();
		}
		return service;
	}

	
	
	

	
	
	
}