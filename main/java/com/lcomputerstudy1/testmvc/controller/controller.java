package com.lcomputerstudy1.testmvc.controller;


import java.util.*;
import java.text.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.lcomputerstudy1.testmvc.service.BoardService;
import com.lcomputerstudy1.testmvc.service.FileService;
import com.lcomputerstudy1.testmvc.service.ReplyService;
import com.lcomputerstudy1.testmvc.service.UserService;
import com.lcomputerstudy1.testmvc.vo.Board;
import com.lcomputerstudy1.testmvc.vo.File;
import com.lcomputerstudy1.testmvc.vo.Pagination;
import com.lcomputerstudy1.testmvc.vo.Reply;
import com.lcomputerstudy1.testmvc.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		MultipartRequest multi = null;
		
		
		
		
		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String ndate = formatter.format(currentTime);

		
		
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;

		User user = null;
		UserService userService = null;
		
		Board board = null;
		BoardService boardService = null;
		
		Reply reply = null;
		ReplyService replyService = null;
		
		File file = null;
		FileService fileService = null;
		
		int usercount = 0;
		int page = 1;
		int count = 0;
		int boardcount = 0;
		int page2 = 1;
		
		String pw = null;
		String idx = null;
		HttpSession session = null;
		command = checkSession(request,response, command);
		
		
		switch (command) {
			case "/user-list.do":
				String reqPage = request.getParameter("page");
				if(reqPage != null)
					page = Integer.parseInt(reqPage);
				
				userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				Pagination pagination = new Pagination();
				pagination.setPage(page);
				pagination.setCount(usercount);
				pagination.init();
				
				ArrayList<User> list = userService.getUsers(pagination);
				
				
				request.setAttribute("list", list);
				request.setAttribute("pagination",pagination);
				
				view = "user/list";
				break;
			case "/user-insert.do":
				view = "user/insert";
				break;
			case "/user-insert-process.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user); 
						
				view = "user/insert-result";
				break;
			case "/user-Detail.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				user = userService.getDetail(user);
				
				request.setAttribute("user", user);
				
			
				view = "user/Detail";
				break;
			case "/user-edit.do":				
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userService = UserService.getInstance();
				user = userService.getDetail(user);
				user.setArr_tel(user.getU_tel().split("-"));
				request.setAttribute("user", user);
				view = "user/edit";
				break;
			case "/user-inserteditprocess.do":
				user = new User();
				user.setU_id(request.getParameter("id"));
				user.setU_pw(request.getParameter("password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				
				userService = UserService.getInstance();
				userService.editUser(user);
				
				view = "user/insert-result";
				break;
			case "/user-deleteprocess.do":
				user = new User();
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				userService = UserService.getInstance();
				userService.getdelete(user);
				view = "user/delete";
				break;
				
				
			case "/user-login.do":
				view = "user/login";
				break;
			case "/user-login-process.do":
				idx = request.getParameter("login_id");
				pw = request.getParameter("login_password");
				
				userService = UserService.getInstance();
				user = userService.loginUser(idx,pw);
							
				if(user != null) {
					session = request.getSession();
//					session.setAttribute("u_idx", user.getU_idx());
//					session.setAttribute("u_id", user.getU_id());
//					session.setAttribute("u_pw", user.getU_pw());
//					session.setAttribute("u_name", user.getU_name());
					session.setAttribute("user", user);
					view = "user/login-result";
				} else {
					view = "user/login-fail";
				}			
				break;
			case "/logout.do":
				session = request.getSession();
				session.invalidate();
				view = "user/login";
				break;
			case "/access-denied.do":
				view = "user/access-denied";
				break;
				
				
				
	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			case "/board-list.do":
				
				
				String reqPage1 = request.getParameter("page");
				if(reqPage1 != null)
					page = Integer.parseInt(reqPage1);
				board = new Board();
				boardService = BoardService.getInstance();
				board.setb_opt(0);
				boardcount = boardService.getBoardsCount(board);
				
				Pagination pagination2 = new Pagination();
				pagination2.setPage(page);
				pagination2.setCount(boardcount);
				pagination2.init();
				
				ArrayList<Board> list1 = boardService.getBoards(pagination2);
				
				
			
				
				request.setAttribute("board",board);
				request.setAttribute("list", list1);
				request.setAttribute("pagination",pagination2);
				
				view = "board/list";
				break;
				
				
				
				
				/*String reqPage1 = request.getParameter("page");
				if (reqPage1 != null) { 
					page = Integer.parseInt(reqPage1);
					
				}
				boardService = BoardService.getInstance();
				ArrayList<Board> list1 = boardService.getBoards(page);
				boardcount = boardService.getBoardsCount();
				request.setAttribute("list", list1);
				request.setAttribute("boardcount", boardcount);
				
				view = "board/list";
				break;*/
	
				
				
				
				
				
				
				
				/*boardService = BoardService.getInstance();
				ArrayList<Board> list1 = boardService.getBoards();
				boardcount = boardService.getBoardsCount();
				request.setAttribute("list", list1);
				request.setAttribute("boardcount", boardcount);


				view = "board/list";
				break;*/
				
				
				
				
				
				
				
				
				
				/*boardService = BoardService.getInstance();
				ArrayList<Board> list1 = boardService.getBoards();
				request.setAttribute("list", list1);
				view = "board/list";
				break;*/
				
			case "/board-insert.do":
				view = "board/insert";	
				break;
				
			case "/board-insert-process.do":
				
				
				
				int sizeLimit = 10 * 1024 * 1024 ; // 10메가입니다.
				String savePath = request.getSession().getServletContext().getRealPath("/upload");// 파일이 업로드될 실제 tomcat 폴더의 WebContent 기준
				try{
				multi=new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				}catch (Exception e) {
					e.printStackTrace();
				}
				String filename = multi.getFilesystemName("filename");

				String title = multi.getParameter("title");
				String writer = multi.getParameter("writer");
				int count2 = 0;
				String content = multi.getParameter("content");
				String regip = request.getRemoteAddr();
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				board = new Board();
				board.setb_title(title);
				board.setb_writer(writer);
				board.setb_count(0);
				board.setb_content(content);
				board.setb_date(ndate);
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				file = new File();
				file.setf_title(title);
				file.setf_group(writer);
				file.setf_idx(board.getb_idx());
				
				fileService = FileService.getInstance();
				fileService.insertFile(file);
				
				/*board = new Board();
				board.setb_title(request.getParameter("title"));
				board.setb_count(0);
				board.setb_content(request.getParameter("content"));
				board.setb_date(ndate);
				board.setb_writer(request.getParameter("writer"));
				
				
				boardService = BoardService.getInstance();
				boardService.insertBoard(board);*/
					
				view = "board/insert-result";
				break;
				
			case "/board-detail.do":
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				board = boardService.getDetail(board);
				
				
				
				
				replyService = ReplyService.getInstance();
				ArrayList<Reply> list2 = replyService.getReplys();
				request.setAttribute("list", list2);
				
				
				
			
				
				
				
				
				
				
				
				
				
				
				request.setAttribute("board", board);
				view = "board/detail";
				break;

			case "/board-edit.do":				
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				boardService = BoardService.getInstance();
				board = boardService.getDetail(board);
				request.setAttribute("board", board); // testxx
				view = "board/edit";				
				break;
				
			case "/board-inserteditprocess.do":
				board = new Board();
				
				board.setb_title(request.getParameter("title"));
				board.setb_writer(request.getParameter("writer"));
				board.setb_date(ndate);
				board.setb_content(request.getParameter("content"));
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				
				boardService = BoardService.getInstance();
				boardService.editBoard(board);
				
				view = "board/insert-result";
				break;
			case "/board-deleteprocess.do":
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				boardService.getdelete(board);
				
				request.setAttribute("board", board);
				view = "board/delete";
				break;
				
				
			case "/board-reply.do":
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				boardService = BoardService.getInstance();
				board = boardService.getDetail(board);
				
				request.setAttribute("board", board);
				
				
				view = "board/reply";
				break;
				
			case "/board-reply-process.do":
				board = new Board();
				board.setb_title(request.getParameter("title"));
				board.setb_count(0);
				board.setb_content(request.getParameter("content"));
				board.setb_date(ndate);
				board.setb_writer(request.getParameter("writer"));
				board.setb_group(Integer.parseInt(request.getParameter("b_group")));
				board.setb_depth(Integer.parseInt(request.getParameter("b_depth")));
				board.setb_order(Integer.parseInt(request.getParameter("b_order")));
				
				boardService = BoardService.getInstance();
				boardService.insertReply(board);
						
				view = "board/insert-result";
				break;
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			case "/reply-reply.do":   //대댓글
				reply = new Reply();
				reply.setr_order(Integer.parseInt(request.getParameter("r_order")));
				replyService = ReplyService.getInstance();
				reply = replyService.getReplyDetail(reply);
				
				request.setAttribute("reply", reply);
				
				
				view = "reply/reply-insert";
				break;
				
				
			case "/reply-reply-process.do":   //대댓글
				
				reply = new Reply();
			
				reply.setr_content(request.getParameter("content"));
				reply.setr_date(ndate);
				reply.setr_writer(request.getParameter("writer"));
				reply.setr_group(Integer.parseInt(request.getParameter("r_group")));
				reply.setr_order(Integer.parseInt(request.getParameter("r_order"))+1);
				reply.setr_depth(Integer.parseInt(request.getParameter("r_depth"))+1);
				replyService = ReplyService.getInstance();
				replyService.insertReplyReply(reply);
						
				view = "board/insert-result";
				break;
				
				
			
			case "/reply-insert-process.do":  //첫번째 댓글
				reply = new Reply();
				reply.setr_content(request.getParameter("content"));
				reply.setr_date(ndate);
				reply.setr_writer(request.getParameter("writer"));
				reply.setr_group(Integer.parseInt(request.getParameter("r_group")));
				reply.setr_order(Integer.parseInt(request.getParameter("r_order")));
				reply.setr_depth(Integer.parseInt(request.getParameter("r_depth")));
				
				replyService = ReplyService.getInstance();
				replyService.insertReply(reply);
				
				view = "reply/result";
				break;
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			case "/aj-insertComment.do":
				// insert
				reply = new Reply();
				reply.setr_content(request.getParameter("content"));
				reply.setr_date(ndate);
				reply.setr_writer(request.getParameter("writer"));
				reply.setr_group(Integer.parseInt(request.getParameter("b_idx")));
				reply.setr_order(1);
				reply.setr_depth(0);
				
				
				
				
				
				replyService = ReplyService.getInstance();
				replyService.insertReply(reply);
				
				
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				
				
				// get list
				ArrayList<Reply> list3 =replyService.getreplylist(reply);
				
			
				request.setAttribute("list", list3);
				request.setAttribute("board", board);
				
				view = "reply/result";
				break;
				
			
				
				
			case "/aj-updateComment.do":
				
				reply = new Reply();	
				reply.setr_content(request.getParameter("content"));
				reply.setr_writer(request.getParameter("writer"));
				reply.setr_idx(Integer.parseInt(request.getParameter("r_idx")));
				reply.setr_group(Integer.parseInt(request.getParameter("b_idx")));
				replyService = ReplyService.getInstance();
				replyService.editReply(reply);
				
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				
				ArrayList<Reply> list4 =replyService.getreplylist(reply);
				
				
				request.setAttribute("list", list4);
				request.setAttribute("board", board);
				
				view = "reply/result";
				break;
				
				
				
			case "/aj-deleteComment.do":
				
				reply = new Reply();
				reply.setr_idx(Integer.parseInt(request.getParameter("r_idx")));
				reply.setr_group(Integer.parseInt(request.getParameter("b_idx")));
				replyService = ReplyService.getInstance();
				replyService.deleteReply(reply);
				
				
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				
				ArrayList<Reply> list5 =replyService.getreplylist(reply);
				
				
				request.setAttribute("list", list5);
				request.setAttribute("board", board);
				
				view = "reply/result";
				
				break;
				
				
			case "/aj-reReplyComment.do":
				
				reply = new Reply();
				reply.setr_idx(Integer.parseInt(request.getParameter("r_idx")));
				reply.setr_group(Integer.parseInt(request.getParameter("b_idx")));
				reply.setr_replygroup(Integer.parseInt(request.getParameter("r_replygroup")));
				reply.setr_writer(request.getParameter("writer"));
				reply.setr_content(request.getParameter("content"));
				reply.setr_order(Integer.parseInt(request.getParameter("r_order")));
				reply.setr_depth(Integer.parseInt(request.getParameter("r_depth")));
				
				
				replyService = ReplyService.getInstance();
				replyService.reReply(reply);
				
				
				board = new Board();
				board.setb_idx(Integer.parseInt(request.getParameter("b_idx")));
				
				ArrayList<Reply> list6 =replyService.getreplylist(reply);
				
				
				request.setAttribute("list", list6);
				request.setAttribute("board", board);
				
				view = "reply/result";
				break;
				
				
				
				
				
				
			case "/board-search-process.do":
				
				board = new Board();
				
				board.setb_opt(Integer.parseInt(request.getParameter("opt")));
				
				if(board.getb_opt()==1) {
					
					board.setb_title(request.getParameter("keyWord"));
					
					String reqPage2 = request.getParameter("page");
					if(reqPage2 != null)
						page = Integer.parseInt(reqPage2);
					
					boardService = BoardService.getInstance();
					boardcount = boardService.getBoardsCount(board);
					
					Pagination pagination3 = new Pagination();
					pagination3.setPage(page);
					pagination3.setCount(boardcount);
					pagination3.init();
					ArrayList<Board> list8 = boardService.searchtitle(pagination3,board);
					request.setAttribute("board",board);
					request.setAttribute("list", list8);
					request.setAttribute("pagination",pagination3);
					/*board.setb_title(request.getParameter("keyWord"));
					boardService = BoardService.getInstance();
					ArrayList<Board> list7 = boardService.searchtitle(board);
					request.setAttribute("list", list7);*/
				
				} else if(board.getb_opt()==2) {
					board.setb_writer(request.getParameter("keyWord"));
					
					String reqPage2 = request.getParameter("page");
					if(reqPage2 != null)
						page = Integer.parseInt(reqPage2);
					
					boardService = BoardService.getInstance();
					boardcount = boardService.getBoardsCount(board);
					
					Pagination pagination3 = new Pagination();
					pagination3.setPage(page);
					pagination3.setCount(boardcount);
					pagination3.init();
					
					
					ArrayList<Board> list8 = boardService.searchwriter(pagination3,board);
					request.setAttribute("board",board);
					request.setAttribute("list", list8);
					request.setAttribute("pagination",pagination3);
					
				} else if(board.getb_opt()==3) {
					board.setb_content(request.getParameter("keyWord"));
					board.setb_title(request.getParameter("keyWord"));
					
					String reqPage2 = request.getParameter("page");
					if(reqPage2 != null)
						page = Integer.parseInt(reqPage2);
					
					boardService = BoardService.getInstance();
					boardcount = boardService.getBoardsCount(board);
					
					Pagination pagination3 = new Pagination();
					pagination3.setPage(page);
					pagination3.setCount(boardcount);
					pagination3.init();
					
					
					ArrayList<Board> list8 = boardService.searchtitlecontent(pagination3,board);
					request.setAttribute("board",board);
					request.setAttribute("list", list8);
					request.setAttribute("pagination",pagination3);
					
				}
				
				view = "board/list";
				break;
				
				
				
		}
		RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
		rd.forward(request, response);
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}