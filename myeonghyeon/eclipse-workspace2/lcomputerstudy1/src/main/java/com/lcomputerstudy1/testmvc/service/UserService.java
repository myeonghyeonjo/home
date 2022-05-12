package com.lcomputerstudy1.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy1.testmvc.dao.UserDAO;
import com.lcomputerstudy1.testmvc.vo.Pagination;
import com.lcomputerstudy1.testmvc.vo.User;

public class UserService {
	
	private static UserService service = null;
	private static UserDAO dao = null;
    
	private UserService() {
		
	}

	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance();
		}
		return service;
	}

	public ArrayList<User> getUsers(Pagination pagination) {
		return dao.getUsers(pagination);
	}
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}

	public User getDetail(User user) {
		return dao.getDetail(user);
	}
	
	public void editUser(User user) {
		dao.editUser(user);
	}

	public void getdelete(User user) {
		
		dao.getdelete(user);
	}
	public int getUsersCount() {
		return dao.getUsersCount();
	}

	public User loginUser(String idx, String pw) {
		return dao.loginUser(idx,pw);
	}
	
}