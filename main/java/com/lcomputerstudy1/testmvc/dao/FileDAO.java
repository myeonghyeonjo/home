package com.lcomputerstudy1.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy1.testmvc.database.DBConnection;
import com.lcomputerstudy1.testmvc.vo.Board;
import com.lcomputerstudy1.testmvc.vo.File;
import com.lcomputerstudy1.testmvc.vo.Pagination;
import com.lcomputerstudy1.testmvc.vo.User;

public class FileDAO {
	
	private static FileDAO dao = null;
    
	private FileDAO() {
		
	}

	public static FileDAO getInstance() {
		if(dao == null) {
			dao = new FileDAO();
		}
		return dao;
	}

	
	public void insertFile(File file) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			
			
			
			String sql = "insert into file(f_title) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getf_title());
			pstmt.executeUpdate();
			pstmt.close();
			
			//sql = "update board set b_group = last_insert_id() where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			
			
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

}