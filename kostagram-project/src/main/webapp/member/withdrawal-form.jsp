<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function checkWithdrawal(){
        if(confirm("회원탈퇴를 진행하시겠습니까?")) {
            location.href="WithdrawMemberController.do";
        }
    }
</script>
<div align="center">
    <h2>회원탈퇴 주의사항 안내</h2>
    <h4>회원탈퇴 시 회원 정보 및 해당 계정으로 작성하신 <br>모든 게시물과 댓글이 영구적으로 삭제되며 복원이 불가능합니다.</h4>
    <button class="btn-test" onclick="checkWithdrawal()">회원 탈퇴</button>
</div>