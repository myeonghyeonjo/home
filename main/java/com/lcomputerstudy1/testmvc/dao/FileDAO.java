package com.lcomputerstudy1.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy1.testmvc.database.DBConnection;
import com.lcomputerstudy1.testmvc.vo.Board;
import com.lcomputerstudy1.testmvc.vo.UploadFile;

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

	
	public void insertFile(UploadFile file) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			
			
			
			String sql = "insert into file(f_filename,f_fileRealname) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getf_filename());
			pstmt.setString(2, file.getf_fileRealname());
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

	public UploadFile getfiles(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		UploadFile file  = new UploadFile();
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from file where b_idx = ?";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, board.getb_idx());
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	
	        	 file  = new UploadFile();
       	       	file.setf_filename(rs.getString("f_filename"));
       	       	
       	       	
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return file;
		
	}	

}