<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file ="../Common/include_header.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >
<head>
<%--css 사용 --%>
<link rel="stylesheet" type="text/css" href="./resources/file/css/loginForm.css"/>

<%--쿠키 스크립트 파일 불러오기 --%>
<script src="<c:url value='./resources/file/js/cookie.js'/>" charset="utf-8"></script>
<%--ajax --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
</script>

<script>
function find_Id()
{
	//아이디 찾기 팝업 창
	window.open('/SG/login/findIdForm','','toolbar=no,menubar=no,location=no,height=650,width=600'); 
} 

function find_Pw()
{
	//비밀번호 찾기 팝업 창
	window.open('/SG/login/findPwForm','','toolbar=no,menubar=no,location=no,height=650,width=600'); 
}

//아이디 저장 cookie 시작
//cookie jquery
$(document).ready(function()
{
		    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
		    var userInputId = getCookie("userInputId");
		    $("input[name='MEMBER_ID']").val(userInputId); 
		     
		    if($("input[name='MEMBER_ID']").val() != "") // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
		    { 
		        $("#idsave").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		    }
		     
		    $("#idsave").change(function() // 체크박스에 변화가 있다면,
		    {
		    	var isRemember; //메시지를 담을 객체 변수
		    	
		        if($("#idsave").is(":checked")) // ID 저장하기 체크했을 때,
		        {
		        	isRemember = confirm("이 PC에 로그인 정보를 저장하시겠습니까? PC방등의 공공장소에서는 개인정보가 유출될 수 있으니 주의해주십시오.");
		            var userInputId = $("input[name='MEMBER_ID']").val();
		            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
		        }
		        else // ID 저장하기 체크 해제 시,
		        { 
		            deleteCookie("userInputId");
		        }
		    });
		     
		    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		    $("input[name='MEMBER_ID']").keyup(function() // ID 입력 칸에 ID를 입력할 때,
		    { 
		        if($("#idsave").is(":checked")) // ID 저장하기를 체크한 상태라면,
		        { 
		            var userInputId = $("input[name='MEMBER_ID']").val();
		            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
		        }
		    });
		});

//아이디 저장 쿠키 끝
</script>
</head>

<body>
<div id="loginform">
      <div id="loginformtop"></div>
      
<form name="loginform" method="post" action="/SG/loginSuccess">
      <div id="loginformmiddle">

         <h2>Login</h2>

         <div id="id_label">ID</div>
         <div id="username_input">

            <div id="username_inputleft"></div>
            
               <div id="username_inputmiddle">
                  
                  <input type="text" name="MEMBER_ID" id="ID" required="required"> 
                  <img id="url_user" src="resources/file/img/login/mailicon.png" alt="">

               </div>

               <div id="username_inputright"></div>
         </div>
         <div id="password_label">비밀번호</div>
     
         <div id="password_input">

            <div id="password_inputleft"></div>

            <div id="password_inputmiddle">
                    
               <input type="password" name="MEMBER_PASSWORD" id="PW" required="required"> 
               <img id="url_password" src="resources/file/img/login/passicon.png" alt="">

            </div>


            <div id="password_inputright"></div>

         </div>

         <div id="submit">
            <%--로그인 버튼이 이미지이기 때문에 따로 submit 설정 --%>
            <input type="image" src="resources/file/img/login/login.png" id="submit2"
               value="Sign In" onchange="javascript:document.getElementById('frm').value=this.value">
               <%--onchange : 버튼 -> 태그 ID가 frm인 것의 값을 찾아서 바꿔줌 --%>
            <input id="frm" type="submit">
         </div>
         
         <%--아이디/비밀번호 찾기 --%>
         <div id="links_left">
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:find_Id();">아이디 찾기</a>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:find_Pw();">비밀번호 찾기</a>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             
             <%--아이디 저장 --%>
             <input type="checkbox" id="idsave" name="idsave" value="" onclick="">아이디 저장
             
         </div>
         
         <%--회원가입 페이지로 --%>
         <div id="links_right">
            <a href="/SG/joinEmail">회원가입</a>
         </div>
      </div>
      </form>
      <div id="loginformbottom"></div>
   </div>
   <div class="message">${message}</div>
</body>
