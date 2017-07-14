<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session = "true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/SG/resources/file/css/main.min.css">
<link rel="stylesheet" href="/SG/resources/file/css/joinForm.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script charset="UTF-8" type="text/javascript" src="http://s1.daumcdn.net/svc/attach/U03/cssjs/postcode/1484723365148/170118.js"></script>
<script type="text/javascript">
    function zipfind() 
    {
        new daum.Postcode
        ({
            oncomplete: function(data) 
            {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') 
                { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } 
                else 
                { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R')
                {
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== '')
                    {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== '')
                    {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
  //비밀번호 일치 확인
  function checkpw()
  {
      var f = document.frm;
      var mem_id = f.MEMBER_ID.value;
      var mem_pw = f.MEMBER_PASSWORD.value;
      var mem_phone = f.MEMBER_PHONE.value;
      var mem_zip = f.MEMBER_ZIP.value;
      var mem_addr1 = f.MEMBER_ADDR1.value;
      var mem_addr2 = f.MEMBER_ADDR2.value;
      var mem_email1 = f.MEMBER_EMAIL1.value;
      var mem_email2 = f.MEMBER_EMAIL2.value;
      var mem_height = f.MEMBER_HEIGHT.value;
      var mem_weight = f.MEMBER_WEIGHT.value;
      
      
      if(mem_pw != f.password_confirm.value)
      {
    	 alert("비밀번호가 일치하지 않습니다.");
    	 
    	 f.MEMBER_PASSWORD.value = "";
    	 f.password_confirm.value = "";
    	 
    	 return false;
      }
      
      else if(mem_pw == "" || f.password_confirm.value == "")
      {
    	  confirm("비밀번호를 입력해주세요.");
    	  
      }
      
      else
      {
    	 var update = 
    		 ({
    		   "MEMBER_ID":mem_id,	
    		   "MEMBER_PASSWORD":mem_pw,
    		   "MEMBER_PHONE":mem_phone,
    		   "MEMBER_ZIP":mem_zip,
    		   "MEMBER_ADDR1":mem_addr1,
    		   "MEMBER_ADDR2":mem_addr2, 
    		   "MEMBER_EMAIL1":mem_email1,
    		   "MEMBER_EMAIL2":mem_email2, 
    		   "MEMBER_HEIGHT":mem_height,
    		   "MEMBER_WEIGHT":mem_weight    		 
    		 });
   
    	 $.ajax
    	 ({
    		 type : "POST",
    		 url : "/SG/memberUpdateAction",
    		 data : update,
    		 success : function(data)
    		 {
    			 confirm("회원정보가 수정되었습니다.");
    			 self.close();
    		 },
    		 
    		 error : function()
		     {
		    	 alert("수정이 되지 않았습니다." + e);
		     }
    	 });
    	 
      }
      return true;
      
    }
    </script>

  

</head>

<body>
<div class="body_center">
<form name="frm" method="post" >
<section class="input-horizontal list-horizontal section box-shadow">
            <div class="main_subject">
               <h2>회원정보 수정</h2>
            </div>

<ul class="section-body">
   <li class="id">
      <div class="item-label col-lg-3 col-md-4">
         <label for="mi">
            <strong>아이디</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <input type="text" name="MEMBER_ID" id="MEMBER_ID" value="${myInfo.MEMBER_ID}" class="xx-control" label="아이디" readOnly>
         
      
   </li>
   
   <li class="password">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-password">
            <strong>비밀번호</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <input type="password" id="input-password" class="xx-control" value="" name="MEMBER_PASSWORD" required="required" label="비밀번호">
      </div>
   </li>
   <li class="password check">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-password-check">
            <strong>비밀번호 확인</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <input type="password" id="input-password-check" class="xx-control" value="" name="password_confirm" label="비밀번호" onkeyup="checkPwd()" required="">
      </div>
   </li>
   <li class="name">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-name">
            <strong> 이름</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <input type="text" id="input-name" class="xx-control" name="MEMBER_NAME" value="${myInfo.MEMBER_NAME}" readOnly label="이름">
      </div>
   </li>
   <li class="cell-phone">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-cell-phone01">
            <strong> 핸드폰</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <div class="input-box">
            <input type="text" name="MEMBER_PHONE" id="input-cell-phone01" label="휴대폰" value="${myInfo.MEMBER_PHONE}"  maxlength="11" class="xx-control" required="required">
         </div>
         
         
      </div>
   </li>
   <li class="mail">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-mail">
            <strong>이메일</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
        <div class="input-box">
            <input type="text" name="MEMBER_EMAIL1" class="xx-control x01" label="이메일" required="required" value="${sessionScope.email1}" size="10" >
            <span>@</span>
            <input type="text" name="MEMBER_EMAIL2" id="email2" class="xx-control x02" label="이메일" required="required" value="${sessionScope.email2}" size="20" >
            
         </div>
         
      </div>
   </li>
   <li class="address">
      <div class="item-label col-lg-3 col-md-4">
         <strong>주소</strong>
      </div>
       <div class="col-lg-21 col-md-20">
         <div class="input-box">
         <input type="text" id="sample6_postcode"  name="MEMBER_ZIP"  label="우편번호" value="${myInfo.MEMBER_ZIP}" maxlength="6"  readonly=""  required="">
            <span class="button button-dimmed" onclick="zipfind()">주소 찾기</span>
         </div>
         <input type="text" id="sample6_address" class="xx-control" name="MEMBER_ADDR1" label="주소"value="${myInfo.MEMBER_ADDR1}" size="48" readonly="" required="">
         <input type="text" id="sample6_address2" class="xx-control" name="MEMBER_ADDR2" value="${myInfo.MEMBER_ADDR2}" label="주소" required="">
      </div>
   </li>
   
   <li class="birth input-placeholder">
      <div class="item-label col-lg-3 col-md-4">
         <label for="input-birth01">
            <strong>생년월일</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <div class="input-box">
               <input type="text" id="input-birth01" name="MEMBER_BIRTHDAY" class="xx-control" value="${myInfo.MEMBER_BIRTHDAY}" readOnly >
         </div>
      </div>
   </li>
   <li class="birth input-placeholder">
      <div class="item-label col-lg-3 col-md-4">
         <label for="height">
            <strong>키</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <div class="input-box">
               <input type="text" id="height" name="MEMBER_HEIGHT" value="${myInfo.MEMBER_HEIGHT}" maxlength="8" placeholder="예)180" class="xx-control" >
         </div>
      </div>
   </li>
   <li class="birth input-placeholder">
      <div class="item-label col-lg-3 col-md-4">
         <label for="weight">
            <strong>몸무게</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20">
         <div class="input-box">
               <input type="text" id="weight" name="MEMBER_WEIGHT" value="${myInfo.MEMBER_WEIGHT}"maxlength="8" placeholder="예)180" class="xx-control">
         </div>
      </div>        
   </li>
</ul>
 <div class="btnArea">
  <input class="effect effect-5" type="submit" onclick="checkpw();" value="회원정보 수정하기"/>
 </div>
</section>
</form>
</div>
</body>
</html>