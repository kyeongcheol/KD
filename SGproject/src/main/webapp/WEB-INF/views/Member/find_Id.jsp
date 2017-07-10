<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/file/css/modal_email.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">

 //아이디 찾기 
 function idsearch()
 {
	 var f = document.frm;
	 //아이디 찾기 필요한 이름, 이메일 값 
	 var idname = f.MEMBER_NAME.value;
	 var idemail = f.email1.value+"@"+f.email2.value;
	 
	 //유효성 검증
	 if(idname == "")
     {
		 alert("이름을 입력해주세요.");
	 }
	 
	 else if(idemail == '@')
	 {
		 alert("이메일을 입력해주세요.");
	 }
	 
	 else if(f.email1.value=="" || f.email2.value=="")
	 {
		 alert("이메일을 정확히 입력하세요.");
	 }
	 
	 else
	 {
		 //컨트롤러로 보낼 json data
		 var total = ({"idname":idname, "idemail":idemail});
		 $.ajax
		 ({
			 type : "POST",
		     url : "/SG/login/findId",
		     data : total,
		     
		     success : function(data)
		     {
		    	 //가입 정보가 있으면
		    	 if(data != 0)
		    	 {
		    		alert("가입하신 아이디는 " +data+ "입니다."); 
		    	 }
		    	 
		    	 else
		    	 {
		    		alert("가입된 아이디가 없습니다.");
		    	 }
		     },
		     
		     error : function(e)
		     {
		    	 alert("error" + e);
		     }
		 });
		 
	 }
	 
 }

</script>
<title>아이디 찾기</title>
</head>
<body>
<form name="frm" method="post">
<div class="form">
  <div class="form-toggle"></div>
  <div class="form-panel one">
    <div class="form-header">
      <h2>아이디 찾기</h2>
    </div>
    
    <div>
       <%--이름 입력 --%>
       <div class="form-group">
          <label for="sing_code">이름</label>
          <input type="text" id="auth_code" name="MEMBER_NAME"/>
        </div>
        <div class="form-group">
          <label for="username">이메일</label>
        <%--이메일 입력 --%>  
        <input name="email1" id="email1" class="form-control" size="10" type="text"> @
 		<input name="email2" id="email2" class="form-control" size="10" type="text"> 
        </div>
        
        <div class="form-group2">
           <button type="button" onclick="javascript:idsearch();">아이디 찾기</button>
        </div>
        
        <div class="form-group2">
           <button type="button" onclick="javascript:window.close();">닫기</button>
        </div>
    </div>
  </div>
</div>
</form>

</body>
</html>