<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*"%>
<%@ page import ="javax.naming.*"%>

<%@ include file ="../header.jsp" %>
    
<c:set var="board_id" value="${board_id}"/>

	
<div class="container toppadding">

<div class="col-md-12">
	<h2 class="text-center p8060">コメント修正</h2>
	</div>
	
		<form action="BoardCommentReWrite?board_id=${board_id}" method="post">
				<div class="form-group">
					<label class="mb-2">내용</label>
					<textarea name="content" class="form-control03">${commentEditView.content } </textarea>
				</div>
				
				<div>
					<input type="hidden" name="comment_num" value="${commentEditView.num }">			
					<input type="hidden" name="board_num" value="${board_num }">
				</div>		
							
				<button type="submit" class="btn00 btn-primary submit mt-2">修正完了</button>			
		</form>
</div>



<%@ include file ="../footer.jsp" %>