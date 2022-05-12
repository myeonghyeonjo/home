package com.lcomputerstudy1.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy1.testmvc.database.DBConnection;
import com.lcomputerstudy1.testmvc.vo.Reply;

public class ReplyDAO {

	private static ReplyDAO dao = null;
	
	private ReplyDAO() {
		
	}
	
	
	public void insertReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into reply(r_content,r_writer,r_date,r_idx ) values(?,?,now(),?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getr_content());
			pstmt.setString(2, reply.getr_writer());
			pstmt.setInt(3, reply.getr_idx());
			pstmt.executeUpdate();
			pstmt.close();
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

	public static ReplyDAO getInstance() {
		if(dao == null) {
			dao = new ReplyDAO();
		}
		return dao;
	}


	public ArrayList<Reply> getReplys() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reply> list = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from reply";
	       	pstmt = conn.prepareStatement(query);
	       
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Reply>();

	        while(rs.next()){     
	        	Reply Reply = new Reply();    
	        	Reply.setr_date(rs.getString("r_date"));
	        	Reply.setr_writer(rs.getString("r_writer"));
	        	Reply.setr_idx(rs.getInt("r_idx"));
	        	Reply.setr_content(rs.getString("r_content"));
	        	
	        	
	        	list.add(Reply);
	        }
		} catch (Exception e) {
			
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
