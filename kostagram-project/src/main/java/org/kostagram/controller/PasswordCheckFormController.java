package org.kostagram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PasswordCheckFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
	    if (request.getParameter("withdrawal") != "1") {
		    session.setAttribute("withdrawal", "1");
		}
	    request.setAttribute("url", "member/checkpassword-form.jsp");
		return "layout.jsp";
	}
}
