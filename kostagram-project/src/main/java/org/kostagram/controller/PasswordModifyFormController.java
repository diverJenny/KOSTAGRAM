package org.kostagram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PasswordModifyFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String url = "member/modifypassword-form.jsp";
	    HttpSession session = request.getSession(false);
	    if(session.getAttribute("withdrawal") == "1") {
		    url = "member/withdrawal-form.jsp";
		}
	    request.setAttribute("url", url);
		return "layout.jsp";
	}
}
