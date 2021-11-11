package org.kostagram.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kostagram.model.MemberVO;
import org.kostagram.model.PostDAO;
import org.kostagram.model.PostVO;

public class MainController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO loginVO = (MemberVO) session.getAttribute("loginVO");
		
		String memberId = loginVO.getMemberId();
		ArrayList<PostVO> postList = PostDAO.getInstance().mainPostList(loginVO.getMemberId());
		
		request.setAttribute("postList", postList);
		
		//template layout 페이지를 이용해 리스트 화면을 제공 
		request.setAttribute("url", "main.jsp");
		return "layout.jsp";
	}
}
