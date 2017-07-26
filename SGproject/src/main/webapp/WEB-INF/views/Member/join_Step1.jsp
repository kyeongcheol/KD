<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head><link rel="stylesheet" type="text/css" href="/SG/resources/file/css/emailauth.css" /> 
<script type="text/javascript">
function modal_email()
{
	//부모창에서 이동할 경로 설정
	window.name="/SG/joinAgree";
	
	//팝업창(자식창) 경로 설정
	window.open('/SG/modal_email','','toolbar=no,menubar=no,location=no,height=650,width=600'); 
} 
</script>
</head>

<body>
<div class="title">
<h2></h2>
</div>
<div class="container">
<div class="contents1">         
         
      <div class="xans-member-joinAgreement">
         
         <div class="agreeArea" style="width:1145px;">
            <h3>샐러드 구쁘다 회원가입을 위해 본인인증을 시작합니다.</h3>
            <div class="agree">
            <p>이메일 인증을 통하여 본인인증을 진행할 수 있습니다.<br>
		본인인증을 위해 입력하신 개인정보는 회원가입을 위해서 확인하는 것이며,<br>
		수집된 정보는 본인인증 외 어떠한 용도로도 사용되거나 샐러드 구쁘다에 저장되지 않으므로 안심하시기 바랍니다.</p>
		<br>이메일 인증은 따로 정보를 저장하지 않습니다.<br>해당 이메일로 전송받은 인증 번호를 입력해서 인증을 받는 방법입니다.
		</div>
	</div>
	
</div>

    <a href="javascript:modal_email();" class="effect effect-5" 
    style="padding: 15px 0px; width:140px; margin-top: 20px; margin-left: 1010px;">	
	이메일 인증
	</a>

</div>
</div>
</body>

<ul>
<li><a href="/SG/joinAgree">인증 후 회원가입</a></li>
</ul>


