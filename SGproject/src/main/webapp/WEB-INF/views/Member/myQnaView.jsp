<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/file/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="resources/file/js/common.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
<style>
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');
#qna_wrap{
   width:1000px;
   margin-left:auto;
   margin-right:auto;
   font-family:PureunJeonnam;
}


.qna_line {margin: 0 50px 0 50px;}
</style>
<script>

	$(document).ready(function(){
		   $("#resister").click(function(){
			  alert("삭제하기 진입")
		      qnaDelete();
		     
		   });
		   
		   $("#list").click(function(){
		     
		       window.opener.location.reload(); 
		       self.close();


		   });
		   
		   $("#upupdate").click(function(){
			   alert("수정하기 진입")
			    qnaUpdate();
		   })
		   
		});

	function qnaDelete(){
		alert("삭제함수진입")
		var f = document.qnaview;
		var qna_no = f.QNA_NO.value;
	
		var update =
		   ({
		      "QNA_NO":qna_no
		  
		   });

		$.ajax
		({
		   type : "POST",
		   url : "/SG/myQnaView/deleteAction",
		   data : update,
		   success : function(data)
		   {
		      confirm("작성 글이 삭제되었습니다.");
		      window.opener.location.reload(); 
		      self.close();
		   },
		   error : function(e)
		   {
		      alert("작성글이 삭제되지 않았습니다." + e);
		   }
		});
		return true;
	}

	function qnaUpdate(){
		alert("수정함수진입")
		var f = document.qnaview;
		var qna_no = f.QNA_NO.value;
		var qna_title = f.QNA_TITLE.value;
		var qna_content = f.QNA_CONTENT.value;
		
	
		var update =
		   ({
		      "QNA_NO":qna_no,
		      "QNA_TITLE":qna_title,
		      "QNA_CONTENT":qna_content
		  
		   });

		$.ajax
		({
		   type : "POST",
		   url : "/SG/myQnaView/updateAction",
		   data : update,
		   success : function(data)
		   {
		      confirm("작성 글이 수정되었습니다.");
		      window.opener.location.reload(); 
		      self.close();
		   },
		   error : function(e)
		   {
		      alert("작성글이 수정되지 않았습니다." + e);
		   }
		});
		return true;
	}

</script>
</head>
<body>
<div id="qna_wrap">
<div>
<h3 style="text-align:center;">
<span style="color:black; font-size:30px; font-weight:bold;">나의 문의 확인하기 </span>
</h3>
</div>

<div class="qna_line">
   <hr color="#777" width="100%" size="1">
</div>

<form id="qnaview" name="qnaview" method="post" enctype="multipart/form-data">
<input type="hidden" name="QNA_NO" id="QNA_NO" value="${myQnaView.QNA_NO }">


<div>

   <div style="width:50%; float:left;">
      <div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:10px;">문의 카테고리</div>
      <input type="text" class="form-control" value= "${myQnaView.QNA_CATEGORY}"  disabled="disabled" style="width:300px;">
   
   </div>
   
   <div style="width:100%; float:left; margin-bottom:30px;">
      <div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:10px;">문의 제목</div>
      <input type="text" class="form-control" value= "${myQnaView.QNA_TITLE}" id="QNA_TITLE" name="QNA_TITLE" style="width:480px; margin-right:20px;">

   </div>
   

</div>




<div>


<div style="width:100%;float:left;">
<div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:10px;">문의 내용</div>
<textarea class="form-control" name="QNA_CONTENT" rows="10" cols="50"id="QNA_CONTENT" name="QNA_CONTENT">${myQnaView.QNA_CONTENT}</textarea>
</div>



<div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:20px;width:55%;float:left; margin-top:25px;">답변 내용</div>  <div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:25px;margin-left:95px;float:left; width:10%;">답변 날짜</div>
      <input type="text" class="form-control" value= "${myQnaView.QNA_REPDATE}"  disabled="disabled" style="width:25%; float:left;margin-top:15px;">
<textarea class="form-control" name="QNA_REPCONTENT" rows="10" cols="50" disabled="disabled" >${myQnaView.QNA_REPCONTENT}</textarea>
</div>
</form>
<div>

<div style="float:left; margin-top:20px;width:100%">

<button type="button" id="list" class="btn btn-info" style="margin-left:10px;float:left">목록으로</button>

<c:choose>
<c:when test="${myQnaView.QNA_REPSTATE == '답변대기'}">
<button type="button" id="upupdate" class="btn btn-info" style="margin-left:700px;margin-right:0px;">수정하기</button>
<input type="hidden" id="QNA_NO" name="QNA_NO" value="${myQnaView.QNA_NO}">  
<button type="button" id="resister" class="btn btn-info" style="margin-left:45px;margin-right:0px;">삭제하기</button>
</c:when>

<c:otherwise>
<button type="button" id="resister" class="btn btn-info" style="margin-left:830px;margin-right:0px;float:left;">삭제하기</button>
</c:otherwise>
</c:choose>




</div>   
</div>

</div>
</body>
</html>