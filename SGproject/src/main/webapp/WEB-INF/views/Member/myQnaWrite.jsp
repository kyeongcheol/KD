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
   width:850px;
   margin-left:auto;
   margin-right:auto;
   font-family:PureunJeonnam;
   height:600px;
}


.qna_line {margin: 0 50px 0 50px;}
</style>
<script>
$(document).ready(function(){
   $("#resister").click(function(){
      fn_insertQna();
     
   });
   
   $("#list").click(function(){
     
       window.opener.location.reload(); 
       self.close();


   });
   
});


function fn_insertQna(){

var f = document.qnawrite;
var qna_category = f.QNA_CATEGORY.value;
var qna_title = f.QNA_TITLE.value;
var qna_content = f.QNA_CONTENT.value;

var update =
   ({
      "QNA_CATEGORY":qna_category,
      "QNA_TITLE":qna_title,
      "QNA_CONTENT":qna_content
   });

$.ajax
({
   type : "POST",
   url : "/SG/myQnaWrite/writeAction",
   data : update,
   success : function(data)
   {
      confirm("문의가 정상적으로 접수되었습니다.");
      window.opener.location.reload(); 
      self.close();
   },
   error : function(e)
   {
      alert("문의가 등록되지 않았습니다." + e);
   }
});

return true;
}

</script>
</head>
<body>
<div id="qna_wrap"style="border:2px Solid #CCCCCC ;">
<div>
<h3 style="text-align:center;">
<span style="color:black; font-size:30px; font-weight:bold;">샐러드 구쁘다에게 묻는다 </span> 
</h3>
</div>

<div class="qna_line">
   <hr color="#777" width="100%" size="1">
</div>

<form id="qnawrite" name="qnawrite" method="post" enctype="multipart/form-data">

   <div style="width:100%; float:left;margin-left:35px;">
      <div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:10px;"> 문의 카테고리</div>
    <!--   <input type="text" class="form-control" value= ""  style="width:500px;"> -->
        <select id="select" name="QNA_CATEGORY"  id="QNA_CATEGORY" style="width:250px;margin-bottom:30px;">
          				 <option selected="selected">---</option>
            			 <option value="상품문의">상품문의</option>
            			 <option value="홈페이지이용문의">홈페이지이용문의</option>
            			 </select>
   </div>

<div style="margin-left:35px;">
<div style="font-family:PureunJeonnam; font-weight:bold; margin-bottom:10px; margin-top:10px;">문의 제목</div>
<input type="text" class="form-control" id="QNA_TITLE" name="QNA_TITLE" value= "${qna.QNA_TITLE}" style="width:480px; margin-right:20px;">
</div>

<div style="margin-left:35px;">
<div style="font-family:PureunJeonnam; width:500px; font-weight:bold; margin-bottom:10px; margin-top:10px;">문의 내용</div>
<textarea class="form-control" name="QNA_CONTENT" id="QNA_CONTENT" rows="10" cols="50" style="width:85%; float:left;" >${qna.QNA_CONTENT}</textarea>
</div>

</form>
<div>

<div style="float:left; margin-top:20px;width:100%;margin-left:35px;" >

<button type="button" id="list" class="btn btn-info">목록으로</button>

<button type="button" id="resister" class="btn btn-info" style="margin-left:530px;">문의하기</button>
</div>   
</div>
</div>
</body>
</html>