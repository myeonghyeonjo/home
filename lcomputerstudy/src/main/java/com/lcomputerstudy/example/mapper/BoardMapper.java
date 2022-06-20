package com.lcomputerstudy.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.User;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList();
	
	  public void createBoard(Board board);
}


