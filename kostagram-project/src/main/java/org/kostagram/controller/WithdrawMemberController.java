package org.kostagram.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kostagram.model.MemberDAO;
import org.kostagram.model.MemberVO;

public class WithdrawMemberController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        MemberVO vo = (MemberVO) session.getAttribute("loginVO");
        String memberId = vo.getMemberId();
        MemberDAO.getInstance().withdrawMember(memberId);
        session.invalidate();
        return "MainController.do";
    }

}
