<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>샐러드구쁘다</title>
<style>
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
	.body{width:1000px;}
	.write_title {text-align: center; color: #212121; padding-top: 50px;}
	.write_title div {padding-bottom: 10px;}
	.write_font1 {font-family: PureunJeonnam; font-size: 24px; font-weight: bold;}
	.write_font2 {font-family: PureunJeonnam; font-size: 12px; color: #999;}
	.line {margin: 0 50px 0 50px;}
	
	.write_box {padding: 40px 0 30px; width: 900px; height: 350px; margin: auto; }
	.table {width: 1000px; height: 310px;}
	.title {float: left; width: 300px; height: 40px; background-color: #fafafa;border-top: 1px solid #c9c9c9; border-right: 1px solid #c9c9c9;}
	.title_font {color: #212121; font-size: 14px; font-weight: bold; font-family: PureunJeonnam; padding: 10px;}
	.content {float: left; width: 585px; height: 30px; padding: 10px 0 0 10px; border-top: 1px solid #c9c9c9}
	.content_text {text-align: left;}
	.content_title {float: left; width: 300px; height: 170px; background-color: #fafafa; border-top: 1px solid #c9c9c9; border-right: 1px solid #c9c9c9; border-bottom: 1px solid #c9c9c9;}
	.content_font {color: #212121; font-size: 16px; font-weight: bold; font-family: PureunJeonnam; padding: 70px;}
	.content_box {float: left; width: 585px; height: 160px; padding: 10px 0 0 10px; border-top: 1px solid #c9c9c9; border-bottom: 1px solid #c9c9c9;}
	.content_textarea {text-align: left;}
	.btn {text-align: right; margin-right: 100px;}
	#font{font-size: 15px; color: black; font-weight: bold; font-family: PureunJeonnam;}
</style>
<script type="text/javascript" src="resources/file/js/jquery-2.2.2.min.js"></script>
<script>
$(document).ready(function() {
	$("#resister").click(function() {
		document.frm.submit();
		
	});
	$("#cancel").click(function() {
		document.location.href='memberFaqList';
	});
	
});
</script>
</head>
<body>
	<div class="total">
		<!-- body start -->
		<div class="body">
			<div class="write_title">
				<div class="write_font1">FAQ게시판</div>
				<div class="write_font2">등록 및 수정</div>
			</div>
			<div class="line">
				<hr color="#777" width="100%" size="1">
			</div>
			<div class="write_box" align="center">
			<c:choose>
			<c:when test="${modify} == null"> 
				<form action="adminFaqModify" method="post" name="frm" > <!-- 수정 -->
			</c:when>
			<c:otherwise>
				<form action="adminFaqWrite" method="post" name="frm" > <!-- 등록 -->
		
			</c:otherwise>
			</c:choose>
					<div class="table">
						<div class="title">
							<div class="title_font">문의분류</div>
						</div>
						<div class="content">
							<div class="content_text">
								        <select id="select" name="FAQ_CATEGORY">
          								  <option selected="selected">회원안내</option>
            							  <option value="배송안내">배송안내</option>
            							  <option value="주문/결제">주문/결제</option>
            							  <option value="주문취소/변경">주문취소/변경</option>
            							  <option value="교환/반품">교환/반품</option>
            							  <option value="환불">환불</option>
                                          <option value="상품">상품</option>
                                          <option value="사이트 이용안내">사이트 이용안내</option>
        								</select>
							</div>
						</div>
						<div class="title">
							<div class="title_font">제목</div>
						</div>
						<div class="content">
							<div class="content_text">
								<input type="text" size="68" placeholder="제목을 입력하세요." name="FAQ_TITLE" onblur="Writer()" value="${list.FAQ_TITLE}">
							</div>
						</div>
						<div class="title">
							<div class="title_font">작성자</div>
						</div>
						<div class="content">
							<div class="content_text">
								<span id="font">SG관리자</span>
							</div>
						</div>
						<div class="title">
							<div class="title_font">이미지첨부</div>
						</div>
						<div class="content">
							<div class="content_text">
								<input type="file" name="FAQ_IMAGE" />
							</div>
						</div>
						<div class="content_title">
							<div class="content_font">내용</div>
						</div>
						<div class="content_box">
							<div class="content_textarea">
								<textarea rows="10" cols="70" placeholder="내용을 입력하세요" name="FAQ_CONTENT" >${list.FAQ_CONTENT}</textarea>
							</div>
						</div>
					</div>
					<div class="btn">
					<c:choose>
					<c:when test="${modify == 0}">
						<input type="button" value="수정하기" id="modify">	
					</c:when>
					<c:otherwise>
						<input type="submit" value="등록하기" id="resister">
					</c:otherwise>	
					</c:choose>
						<input type="button" value="목록으로" id="cancel">
					</div>
				</form>
			</div>
		</div>
		<!-- body end -->
	</div>
</body>
</html>