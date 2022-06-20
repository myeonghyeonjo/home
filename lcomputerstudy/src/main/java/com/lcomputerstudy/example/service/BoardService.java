package com.lcomputerstudy.example.service;

import java.util.List;
import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.User;

public interface BoardService {
	public List<Board> selectBoardList();
	
	
	public void createBoard(Board board);
}


