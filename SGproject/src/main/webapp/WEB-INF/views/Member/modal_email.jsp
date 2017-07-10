<%@ page contentType="text/html; charset=utf-8" %>
<head>
<link rel="stylesheet" type="text/css" href="resources/file/css/modal_email.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">

//이메일 유효성 검증
function email_code()
{
	  
	   var f = document.frm;
	   var email = f.email1.value+"@"+f.email2.value;
	   var email1 = f.email1.value; //앞 주소 이메일 
	   var email2 = f.email2.value; //뒤 주소 이메일
	   
	      //이메일 주소를 아예 입력하지 않은경우
	 	  if(email == '@') 
	 	  {
		      alert("이메일을 입력하세요.");
		  }
	      //이메일 주소를 한 부분만 입력한 경우
	 	  else if(f.email1.value=="" || f.email2.value=="")
	 	  {
		      alert("이메일을 정확히 입력하세요.");
		      console.log("로그 내용1");  
		  }
	      //둘다 입력한 경우
	 	  else
	 	  {
	 		  var total = {/* "email1":email1, "email2":email2,  */ "email":email};
	    	  $.ajax
	    	  ({
	    	        type: "POST",
	    	        url: "./joinStep1/modal_email_auth",
	    	        data: total,
	    	        //contentType: "text/plain; charset=euc-kr",
	    	        success: function(data) 
	    	        {
	    	        	
	    	        	if(data != 0)
	    	        	{
	    	        		alert("이미 가입된 이메일입니다.다른이메일을 입력해주세요");
	    	        	}
	    	        	else
	    	        	{
	    	        		alert("인증번호를 요청하신 이메일로 발송했습니다.");
	    	        	}
	    	        	
	    	        },
	    	        error: function(e)
	    	        {
	    	         alert("error" + e);
	    	        }
	    	    });
	      }
	   
	}
	
//인증번호 유효성 검증
function member_send()
{
	 var f = document.frm;
	
	 var email = f.email1.value+"@"+f.email2.value;

	 if(email == '@') 
	 {
	      alert("이메일을 입력하세요.");
	 }
	 else if(f.email1.value=="" || f.email2.value=="")
	 {
	      alert("이메일을 정확히 입력하세요.");
	      console.log("로그 내용1");  
	 }
	 else
	 {
   		$.ajax
   		({
      	 type: "POST",
       	 url: "./joinStep1/modal_email_auth_success",
      	  //data: ({Id:$("#Id").val(), Pwd:$("#Pwd").val()}),
      	  //contentType: "text/plain; charset=euc-kr",
      	  success: function(data) 
      	  {
        		 
   	     	 console.log("로그 내용1");
        	 //인증번호가 NULL값이 아니면..	 
           	 if(data != null)    
           	 {
           		    //인증번호를 입력하지 않을 경우
            		if(!f.auth_code.value)
            		{
        				alert("인증번호를 입력해 주세요");
        				f.auth_code.focus(); //커서 focus
        			}
            		else if(f.auth_code.value !=  data)
            		{
        				alert("인증번호가 맞지 않습니다.");

        			}
            		else
            		{
        				//alert("인증번호가 맞습니다.");
        				f.target = opener.name;
        				f.submit();
        				self.close();
        				/* if(opener != null)
        				{
        					opener.insert = null;
        					
        					self.close();
        				} */
        			}
         	   } 
           	    else 
           	    {
          	  	alert("data값없음"+data);
          	  	console.log("로그 내용3");
       	        }
     	   },
     	   error: function(e)
     	   {
     	    alert('error' + e);
   	       }
   	 });
	}
}	
</script>
</head>

<body>
<form name="frm" method="post" action="joinAgree" target="joinAgree">
<div class="form">
  <div class="form-toggle"></div>
  <div class="form-panel one">
    <div class="form-header">
      <h2>Email 인증하기</h2>
    </div>
    
    <div>
        <div class="form-group">
          <label for="username">이메일</label>
        <%--이메일 입력 --%>  
        <input name="email1" id="email1" class="form-control" size="10" type="text"> @ 
		<input name="email2" id="email2" class="form-control" size="10" type="text"> 
        <button type="button" onclick="javascript:email_code();">인증번호받기</button>	
        </div>
        <div class="form-group">
          <label for="sing_code">인증번호</label>
          <input type="password" id="auth_code" name="auth_code"/>
        </div>
        <div class="form-group2">
           <button type="button" onclick="javascript:member_send();">회원가입 하기</button>
        </div>
    </div>
  </div>
</div>
</form>

</body>
