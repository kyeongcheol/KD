<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/file/css/modal_email.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">

 //비밀번호 찾기
 function pwsearch()
 {
	 var f = document.frm;
	 
	 //비밀번호 찾기에 필요한 이름,아이디,이메일 값 
	 var pwname = f.MEMBER_NAME.value;
	 var pwid = f.MEMBER_ID.value;
	 var pwemail = f.email1.value+"@"+f.email2.value;
	 
	 //유효성 검증
	 if(pwname == "")
     {
		 alert("이름을 입력해주세요.");
	 }
	 
	 else if(pwid == "")
	 {
		 alert("아이디를 입력해주세요.");
	 }	 
	 
	 else if(pwemail == '@' || f.email1.value=="" || f.email2.value=="")
	 {
		 alert("이메일을 정확히 입력해주세요.");
	 }
	 
	 else
	 {
		 //컨트롤러로 보낼 json data
		 var to = ({"pwname":pwname, "pwid":pwid, "pwemail":pwemail});
		 
		 $.ajax
		 ({
			 type : "POST",
		     url : "/SG/login/findPw",
		     data : to,
		     
		     success : function(data)
		     {
		    	 
		    	 if(data != 0)
		    	 {
		    		alert("가입하신 비밀번호는 " +data+ "입니다."); 
		    	 }
		    	 
		    	 else
		    	 {
		    		alert("일치하는 정보가 없습니다.");
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
      <h2>비밀번호 찾기</h2>
    </div>
    
    <div>
       <div class="form-group">
          <label for="sing_code">이름</label>
          <input type="text" id="auth_code" name="MEMBER_NAME"/>
        </div>
        
        <div class="form-group">
          <label for="sing_code">아이디</label>
          <input type="text" id="auth_code" name="MEMBER_ID"/>
        </div>
        
        <div class="form-group">
          <label for="username">이메일</label>
        <%--이메일 입력 --%>  
        <input name="email1" id="email1" class="form-control" size="10" type="text"> @
 		<input name="email2" id="email2" class="form-control" size="10" type="text"> 
        </div>
        
        <div class="form-group2">
           <button type="button" onclick="pwsearch();">비밀번호 찾기</button>
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