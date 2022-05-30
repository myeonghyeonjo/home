package com.lcomputerstudy1.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy1.testmvc.dao.BoardDAO;
import com.lcomputerstudy1.testmvc.vo.Pagination;
import com.lcomputerstudy1.testmvc.vo.User;
import com.lcomputerstudy1.testmvc.vo.Board;

public class BoardService {
	
	private static BoardService service = null;
	private static BoardDAO dao = null;
    
	private BoardService() {
		
	}

	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}

	public ArrayList<Board> getBoards(Pagination pagination) {
		return dao.getBoards(pagination);
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
		dao.insertFile(board);
	}

	public Board getDetail(Board board) {
		dao.updatecount(board);
		return dao.getDetail(board);
	}

	public void editBoard(Board board) {
		dao.editBoard(board);
	}

	public void getdelete(Board board) {
		dao.getdelete(board);
		
	}

	public void updatecount(Board board) {
		dao.updatecount(board);
		
	}

	public void insertReply(Board board) {
		dao.insertReply(board);
		
	}

	public ArrayList<Board> searchtitle(Pagination pagination, Board board) {
		return dao.searchtitle(pagination, board);
		
	}

	public ArrayList<Board> searchwriter(Pagination pagination,Board board) {
		// TODO Auto-generated method stub
		return dao.searchwriter(pagination, board);
	}

	public ArrayList<Board> searchtitlecontent(Pagination pagination,Board board) {
		// TODO Auto-generated method stub
		return dao.searchtitlecontent(pagination,board);
	}
	
	public int getBoardsCount(Board board) {
		return dao.getBoardsCount(board);
	}

	
	
	
	
}