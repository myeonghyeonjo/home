package com.lcomputerstudy1.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy1.testmvc.database.DBConnection;
import com.lcomputerstudy1.testmvc.vo.Board;
import com.lcomputerstudy1.testmvc.vo.Reply;

public class ReplyDAO {

	private static ReplyDAO dao = null;
	
	private ReplyDAO() {
		
	}
	
	
	public void insertReply(Reply reply) {  //첫번째 댓글
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into reply(r_content,r_date,r_writer, r_group, r_order, r_depth) values(?,now(),?, ?, 1, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getr_content());
			pstmt.setString(2, reply.getr_writer());
			pstmt.setInt(3, reply.getr_group());
			pstmt.executeUpdate();
			pstmt.close();
				
			
			
			
			
			String sql2 = "UPDATE reply SET r_order = r_order+1 where r_group = ? and r_order >= ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, reply.getr_group());
			pstmt.setInt(2, reply.getr_order());
			pstmt.executeUpdate();
			
			
			String sql3 = "update reply set r_replygroup = last_insert_id() where r_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql3);
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
			String query = "select * from reply order by r_order asc";
	       	pstmt = conn.prepareStatement(query);
	       
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Reply>();

	        while(rs.next()){     
	        	Reply Reply = new Reply();    
	        	Reply.setr_date(rs.getString("r_date"));
	        	Reply.setr_writer(rs.getString("r_writer"));
	        	Reply.setr_idx(rs.getInt("r_idx"));
	        	Reply.setr_content(rs.getString("r_content"));
	        	Reply.setr_order(rs.getInt("r_order"));
	        	Reply.setr_depth(rs.getInt("r_depth"));
	        	Reply.setr_group(rs.getInt("r_group"));
	        	Reply.setr_replygroup(rs.getInt("r_replygroup"));
	        	
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


	public Reply getDetail(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from reply where r_group = ?";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, reply.getr_idx());
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	
	        	reply= new Reply();
       	      
       	       	reply.setr_writer(rs.getString("r_writer"));
       	       	reply.setr_date(rs.getString("r_date"));
       	       	reply.setr_content(rs.getString("r_content"));
       	       	reply.setr_idx(rs.getInt("r_idx"));
       	       	
       	       	reply.setr_order(rs.getInt("r_order"));
       	       	reply.setr_group(rs.getInt("r_idx"));
       	       	reply.setr_depth(rs.getInt("r_depth"));
       	       	
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
		
		return reply;
		
		
	}


	public Reply getReplyDetail(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from reply where r_order = ?";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, reply.getr_order());
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){
	        	
	        	reply= new Reply();
       	      
       	       	reply.setr_order(rs.getInt("r_order"));
       	       	reply.setr_group(rs.getInt("r_group"));
       	       	reply.setr_depth(rs.getInt("r_depth"));
       	       	
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
		
		return reply;
		
		
	}


	public void insertReplyReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			
			String sql1 = "UPDATE reply SET r_order = r_order+1 where r_group = ? and r_order >= ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, reply.getr_group());
			pstmt.setInt(2, reply.getr_order());
			pstmt.executeUpdate();
			
			String sql = "insert into reply(r_content,r_date,r_writer, r_group, r_order, r_depth) values(?,now(),?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getr_content());
			pstmt.setString(2, reply.getr_writer());
			pstmt.setInt(3, reply.getr_group());
			pstmt.setInt(4, reply.getr_order());
			pstmt.setInt(5, reply.getr_depth());
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


	public ArrayList<Reply> getreplylist(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reply> list = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "select * from reply where r_group = ? order by r_order asc ";
	       	pstmt = conn.prepareStatement(query);
	    	pstmt.setInt(1, reply.getr_group());
	       	
	       	
	       	
	        rs = pstmt.executeQuery();
	        list = new ArrayList<Reply>();

	        while(rs.next()){     
	        	Reply Reply = new Reply();    
	        	Reply.setr_date(rs.getString("r_date"));
	        	Reply.setr_writer(rs.getString("r_writer"));
	        	Reply.setr_idx(rs.getInt("r_idx"));
	        	Reply.setr_content(rs.getString("r_content"));
	        	Reply.setr_order(rs.getInt("r_order"));
	        	Reply.setr_depth(rs.getInt("r_depth"));
	        	Reply.setr_group(rs.getInt("r_group"));
	        	
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


	public void editReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE reply SET r_content = ?, r_writer = ? where r_idx= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getr_content());
			pstmt.setString(2, reply.getr_writer());
			pstmt.setInt(3, reply.getr_idx());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void deleteReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "delete from reply where r_idx=?";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, reply.getr_idx());
	        pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}    
	}


	public void reReply(Reply reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			
			
		
			
			
			String sql1 = "UPDATE reply SET r_order = r_order+1 where r_group = ? and r_order > ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, reply.getr_group());
			pstmt.setInt(2, reply.getr_order());
			pstmt.executeUpdate();
			
			String sql = "insert into reply(r_content,r_date,r_writer, r_group, r_order, r_depth, r_replygroup) values(?,now(),?, ?, ?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getr_content());
			pstmt.setString(2, reply.getr_writer());
			pstmt.setInt(3, reply.getr_group());
			pstmt.setInt(4, reply.getr_order()+1);
			pstmt.setInt(5, reply.getr_depth()+1);
			pstmt.setInt(6, reply.getr_replygroup());
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
}