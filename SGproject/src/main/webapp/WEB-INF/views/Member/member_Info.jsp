<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" href="/SG/resources/file/css/main.min.css">
<link rel="stylesheet" href="/SG/resources/file/css/joinForm.css">
<script>

//회원탈퇴
function deleteMember()
{
    var f = document.frm;
	var mem_id = f.MEMBER_ID.value;
	
   if(!confirm("삭제하시겠습니까?"))
	   {
	   return;
	   }
   
   else{
	   var total = ({"MEMBER_ID":mem_id});
		$.ajax
		({
			
			type: "POST",
			url: "/SG/memberDeleteAction",
			data : total,
			
			success: function(data)
			{
				console.log("로그 내용1");
				
				if(data != 0)
					{
					alert("그동안 이용해 주셔서 감사합니다.");
					self.close();
					opener.location.replace("main"); //부모창 새로고침
								    
					}	
				else
					{
				    alert("탈퇴에 실패 함");
				    self.close();
					} 
			
		     },
		error: function(e)
		{
			alert('error'+e);
		}
	});
}
	
} 
          
</script>
</head>

<body>
<div class="body_center">
<form action="/SG/memberUpdateForm" name="frm">
<section class="input-horizontal list-horizontal section box-shadow">
            <div class="main_subject">
               <h2>회원정보</h2>
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
         <input type="password" id="input-password" class="xx-control" value="${myInfo.MEMBER_PASSWORD}" name="MEMBER_PASSWORD" label="비밀번호" readOnly>
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
            <input type="text" name="MEMBER_PHONE" id="input-cell-phone01" label="휴대폰" value="${myInfo.MEMBER_PHONE}" readOnly class="xx-control">
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
            <input type="text" name="MEMBER_EMAIL" id="MEMBER_EMAIL" label="이메일" value="${myInfo.MEMBER_EMAIL}" readOnly class="xx-control">
         </div>
         
      </div>
   </li>
   <li class="address">
      <div class="item-label col-lg-3 col-md-4">
         <strong>주소</strong>
      </div>
      <div class="col-lg-21 col-md-20">
         <div class="input-box">
         <input type="text" id="sample6_postcode"  name="MEMBER_ZIP"  label="우편번호" value="${myInfo.MEMBER_ZIP}" readOnly>
         </div>
         <input type="text" id="sample6_address" class="xx-control" name="MEMBER_ADDR1" label="주소" value="${myInfo.MEMBER_ADDR1}" readOnly>
         <input type="text" id="sample6_address2" class="xx-control" name="MEMBER_ADDR2" label="상세주소" value="${myInfo.MEMBER_ADDR2}" readOnly>
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
               <input type="text" id="height" name="MEMBER_HEIGHT" class="xx-control" value="${myInfo.MEMBER_HEIGHT}" readOnly >
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
               <input type="text" id="weight" name="MEMBER_WEIGHT" class="xx-control" value="${myInfo.MEMBER_WEIGHT}" readOnly>
         </div>
      </div>        
   </li>
</ul>
 <div class="btnArea">
  <input class="effect effect-5" type="button" value="탈퇴하기" onclick="javascript:deleteMember();">
  <input class="effect effect-5" type="submit" value="회원정보 수정하기"/>
 </div>
</section>
</form>
</div>
</body>
</html>