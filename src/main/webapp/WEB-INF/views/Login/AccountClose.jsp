<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="javax.naming.*"%>

<%@ include file ="../header.jsp" %>
	
    
<%
	String message = (String)request.getAttribute("Message");
%>
<c:if test="${message ne null}">
	<script>
		alert("<%=message%>");
	</script>
</c:if>

<div class="container toppadding01">

<div class="col-md-12">
	<h2 class="text-center p8060">アカウント削除確認</h2>
	</div>
	
		<form action="AccountCloseComplete" method="post" name="Passcheck" onsubmit="return PassCheck()">
			
				<div class="form-group">
					<input type="hidden" name="num" value="${loginUser.num }">
				</div>
				
				<div class="form-group">
					<label class="mb-2">パスワードを入力してください。</label>
					<input type="password" name="pass" class="form-control" id="password1" >
				</div>

				<button type="submit" class="btn00 btn-primary submit">完了</button>
			</form>
			
</div>

<!--  체크 -->
	<script>
		function PassCheck() {
			if(document.Passcheck.pass.value.length==0) {
				alert("パスワードを入力してください。");
				Passcheck.pass.focus();
				return false;
			}
			if(document.Passcheck.pass.value.length<4) {
				alert("パスワードは4字以上入力してください。");
				Passcheck.pass.focus();
				return false;
			}
			return true;
	}
	</script>


<%@ include file ="../footer.jsp" %>