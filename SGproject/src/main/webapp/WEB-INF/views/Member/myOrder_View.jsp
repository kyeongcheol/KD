<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page session = "true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="/SG/resources/file/css/main.min.css">
<!-- <link rel="stylesheet" href="/SG/resources/file/css/joinForm.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script charset="UTF-8" type="text/javascript" src="http://s1.daumcdn.net/svc/attach/U03/cssjs/postcode/1484723365148/170118.js"></script>
<script type="text/javascript">

function zipfinds() 
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
            document.getElementById('DELI_RECEIVE_ZIP').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('DELI_RECEIVE_ADDR1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('DELI_RECEIVE_ADDR2').focus();
        }
    }).open();
}

function deliupdate(order_deli_no)
{
   var f = document.frm;
   var deli_name = f.DELI_RECEIVE_NAME.value;
   var deli_zip = f.DELI_RECEIVE_ZIP.value;
   var deli_addr1 = f.DELI_RECEIVE_ADDR1.value;
   var deli_addr2 = f.DELI_RECEIVE_ADDR2.value;
   var deli_phone = f.DELI_RECEIVE_PHONE.value;
   var deli_memo = f.DELI_ORDER_MEMO.value;
   
   
   var update =
      ({
         "DELI_RECEIVE_NAME":deli_name,
         "DELI_RECEIVE_ZIP":deli_zip,
         "DELI_RECEIVE_ADDR1":deli_addr1,
         "DELI_RECEIVE_ADDR2":deli_addr2,
         "DELI_RECEIVE_PHONE":deli_phone,
         "DELI_ORDER_MEMO":deli_memo,
         "ORDER_DELI_NO":order_deli_no,
         "ORDER_GOODS_AMOUNT":order_goods_amount,
         
      });
   
   $.ajax
   ({
      type : "POST",
      url : "/SG/orderUpdateAction",
      data : update,
      success : function(data)
      {
         confirm("회원정보가 수정되었습니다.");
         self.close();
      },
      error : function(e)
      {
         alert("수정이 되지 않았습니다." + e);
      }
   });

   return true;
   }
   

</script>

<style type="text/css">
@import url('//cdn.rawgit.com/young-ha/webfont-archive/master/css/PureunJeonnam.css');

.effect 
{
  text-align: center;
  display: inline-block;
  position: relative;
  text-decoration: none;
  color: #fff;
  text-transform: capitalize;
  background-color: #00CCCF;;
  font-size: 15px;
  padding-top: 12px; 
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 12px;
  width: 150px;
  height: 50px;
  border-radius: 6px;
  overflow: hidden;
 /*  margin-left:700px; */
  font-family: PureunJeonnam;
}

.effect.effect-5 
{
  -webkit-transition: all 0.2s linear 0s;
  transition: all 0.2s linear 0s;
}

.effect.effect-5:before 
{
  content: ">"; 
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
      -ms-flex-align: center;
          align-items: center;
  -webkit-box-pack: center;
      -ms-flex-pack: center;
          justify-content: center;
  position: absolute;
  top: 0;
  left: 0px;
  height: 100%;
  width: 30px;
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 0 50% 50% 0;
  -webkit-transform: scale(0, 1);
          transform: scale(0, 1);
  -webkit-transform-origin: left center;
          transform-origin: left center;
  -webkit-transition: all 0.2s linear 0s;
  transition: all 0.2s linear 0s;
}

 .effect.effect-5:hover 
{
  text-indent: 15px;
} 
.effect.effect-5:hover:before 
{
  -webkit-transform: scale(3, 1);
          transform: scale(1, 1);
  text-indent:0px;
}

.btnArea 
{
   overflow: hidden;
   margin: 20px 0 50px;
   text-align: right;
   font-size: 1%;
   line-height: 1%;
}

.order_view_section 
{
 width:1000px;
 height:auto;
 margin-left:70px;
 margin-right:50px;
 margin-top : 30px;
 margin-bottom : 50px;
 font-family: PureunJeonnam;

}

.trade_info_section 
{
 width:1000px;
 height:100px;
 margin-left:70px;
 margin-right:50px;
 margin-top : 30px;
 margin-bottom : 100px; 
 font-family: PureunJeonnam;

}

.seller_info
{
 width:1000px;
 margin-left:70px;
 margin-right:50px;
 margin-top : 30px;
 margin-bottom : 100px; 
 font-family: PureunJeonnam;
 height:500px;

}
.main_subject 
{
 color:000000;
 text-align:left;
 margin-left:10px;
 margin-bottom:10px;
}

#ordert_wrap 
{
   font-family: PureunJeonnam;
}
.ordert_title div {padding-top: 20px; padding-bottom: 10px;}
.title_font2 {font-family: PureunJeonnam; font-size: 20px; color: #999;}

.ordert_table
{
   text-align: center;
   font-family: PureunJeonnam;
   margin-left : auto;
   margin-right: auto;
   width:700px;
   postion:absolute;
   font-weight:bold;
}

.ordert_table th 
{
   text-align: center; 
   border-top: 1px solid #e5e5e5; 
   border-bottom: 1px solid #e5e5e5;
   padding: 8px 0; 
   background: #f5f5f5;
   font-family: PureunJeonnam;
  
}

.ordert_table td {
   text-align: center; 
   border-top: 1px solid #e5e5e5; 
   border-bottom: 1px solid #e5e5e5; 
   font-family: PureunJeonnam;
}

.item_info
{
   text-align: center;
   font-family: PureunJeonnam;
   margin-left : auto;
   margin-right: auto;
   width:700px;
   postion:absolute;
   font-weight:bold;
}

.faq_line {margin: 0 50px 0 50px;}

.black {margin: 0 50px  50px;}
</style>
</head>
<body>

<div class="order_view_section" style="height:auto;">
<form name="frm">
 <div class="order_view_section" style="height:auto; padding-top:0px;">         
  <section class="input-horizontal list-horizontal section box-shadow">
   <div class="main_subject">
     <h2>상품 정보</h2>
   </div>

<div class="order_view_section" style=" width:680px; height:auto;">            
  <div id="ordert_wrap">

  <c:choose>
    <c:when test="${fn:length(myOrderDetail) > 0}">   
       <table class="ordert_table" width="100%">
          <thead> 
          <colgroup>
          <col width="30%" />
          <col width="20%"/>
          <col width="20%" />
          <col width="10%" />
          <col width="10%" />
          </colgroup>
          
           <tr>
           <th>이미지</th>
           <th>상품이름</th>
           <th>토핑</th>
           <th>주문금액</th>
           <th>수량</th>
           </tr>
          </thead>
         
   <c:forEach var="orderinfo" items="${myOrderDetail}" varStatus="stat">
   
   <tr> 
      <td><img src="resources/file/goodsFile/${orderinfo.GOODS_THUMBNAIL}" style="width:148px; height:148px" /></td>
      <td>${orderinfo.GOODS_NAME}</td>
      <td>${orderinfo.ORDER_TOPPING_NAME}</td>
      <td>${orderinfo.ORDER_MONEY}</td>
	  <td>${orderinfo.ORDER_GOODS_AMOUNT}</td>
	  
   </tr>

   </c:forEach>
   </table>
       
   </c:when>
                
   <c:otherwise>
      <tr>
       <td colspan="5">주문 상품이 없습니다.</td>
      </tr>
   </c:otherwise>
   </c:choose>
      


<div style="height:15px;"></div>
</div>
</div>
</section>
</div>

<div class="trade_info_section">
<section class="input-horizontal list-horizontal section box-shadow">
<div class="main_subject">
               <h2>결제 정보</h2>
            </div>
            
<div id="ordert_wrap">
<table class="ordert_table" width="50%">
<colgroup>
   <col width="20%" />
   <col width="15%" />
   <col width="15%"/>
</colgroup>

<tr>
    <th>송장번호</th>
    <th>사용한 포인트</th>
    <th>총 결제 금액</th>
  
</tr>

   <tr> 
      <td>${myOrderDetail[0].DELI_INVOICE_NO}</td>
      <td>${orderUsePoint}</td>
      <td>${myOrderDetail[0].SUM_ORDER_MONEY}</td>
   </tr>


</table>

<div style="height:10px;"></div>
</div>    
</section>
</div>

<div class="seller_info">

<section class="input-horizontal list-horizontal section box-shadow">
            <div class="main_subject" style="margin-left:15px;margin-top:30px;">
               <h2>주문자 정보</h2>
            </div>
<table>            
 
<div style="margin-top:20px;margin-left:110px;">
<ul class="section-body">
     <li class="id">
      <div class="item-label col-lg-3 col-md-4" style="width:630px;height:23px; font-size:12px;">
         <label for="mi">
            <strong>* 주문자 아이디</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20" style="width:630px;">
         <input type="text" name="MEMBER_ID" id="" value="${sessionScope.MEMBER_ID}" maxlength="20" class="xx-control" label="" required="required" readonly>
      </div>
    </li>
    
   <li class="name">
      <div class="item-label col-lg-3 col-md-4" style=" width:630px;height:23px; font-size:12px; vertical-align:middle;margin-top:10px;">
         <label for="input-name">
            <strong>* 수령자 이름</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20" style="width:630px;">
         <input type="text" id="DELI_RECEIVE_NAME" class="xx-control" name="DELI_RECEIVE_NAME" value="${myOrderDetail[0].DELI_RECEIVE_NAME}" required="required" label="이름">
      </div>
   </li>

   <li class="address">
      <div class="item-label col-lg-3 col-md-4" style=" width:630px; font-size:12px; vertical-align:middle;margin-top:10px;">
         <strong>* 수령자 주소</strong>
      </div>
      <div class="col-lg-21 col-md-20" style="width:630px;margin-top:5px;">
         <div class="input-box" style="margin-bottom:10px;" >
         <input type="text" id="DELI_RECEIVE_ZIP"  name="DELI_RECEIVE_ZIP"  label="우편번호" value="${myOrderDetail[0].DELI_RECEIVE_ZIP}" maxlength="6" required="">
            <span class="button button-dimmed" onclick="zipfinds()">주소 찾기</span>
         </div>
         <input type="text" id="DELI_RECEIVE_ADDR1" class="xx-control" name="DELI_RECEIVE_ADDR1" label="주소" value="${myOrderDetail[0].DELI_RECEIVE_ADDR1}" size="48" readonly="" required="">
         <div style="height:10px;width:500px;"></div>
         <input type="text" id="DELI_RECEIVE_ADDR2" class="xx-control" name="DELI_RECEIVE_ADDR2" value="${myOrderDetail[0].DELI_RECEIVE_ADDR2}" label="주소" required="">
      </div>
      
   </li>
   <li class="cell-phone">
      <div class="item-label col-lg-3 col-md-4" style=" width:630px;height:30px; font-size:12px; vertical-align:middle;margin-top:10px;">
         <label for="input-cell-phone01">
            <strong>* 수령자 연락처</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20" style="width:630px;">
         <div class="input-box">
            <input type="text" name="DELI_RECEIVE_PHONE" id="DELI_RECEIVE_PHONE" label="휴대폰" value="${myOrderDetail[0].DELI_RECEIVE_PHONE}"  maxlength="11" class="xx-control" required="required">
         </div>   
      </div>
   </li>
   
   <li class="memo">
      <div class="item-label col-lg-3 col-md-4" style=" width:630px; height:30px; font-size:12px; vertical-align:middle;margin-top:10px;">
         <label for="input-cell-phone01">
            <strong>* 기타사항</strong>
         </label>
      </div>
      <div class="col-lg-21 col-md-20" style="width:630px;margin-bottom:40px;">
         <div class="input-box">
            <input type="text" name="DELI_ORDER_MEMO" id="DELI_ORDER_MEMO" label="기타사항" value="${myOrderDetail[0].DELI_ORDER_MEMO}"  maxlength="11" class="xx-control" required="required">
         </div>  
      </div>
   </li>

</ul>
</div>


<tr>
</tr>
<c:choose>
<c:when test="${myOrderDetail[0].ORDER_STATE == 0}">
<td class="update01">  <div class="btnArea">

  <input class="effect effect-5" type="button" value="주문내역수정"/> </div>
  <input type="hidden" id="ORDER_DELI_NO" name="ORDER_DELI_NO" value="${myOrderDetail[0].ORDER_DELI_NO}">
</td>
</c:when>
<c:when test="${myOrderDetail[0].ORDER_STATE == 1}">
 <td class="update02"> <div class="btnArea">

   <input class="effect effect-5" type="button" value="주문내역수정"/> </div>
   <input type="hidden" id="ORDER_DELI_NO" name="ORDER_DELI_NO" value="${myOrderDetail[0].ORDER_DELI_NO}">  
</td>
</c:when>
<c:otherwise>
<div class="btnArea"></div>
</c:otherwise>
</c:choose>

</table>
 
</div>
</section>

</form>
</div>

</body>
<script>
$(".update01").on("click", function(e) 
	      { 
	         e.preventDefault();           
	         var order_deli_no =$(this).parent().find("#ORDER_DELI_NO").val();
	         deliupdate(order_deli_no, order_no);
	               
	      });

$(".update02").on("click", function(e) 
	      { 
	         e.preventDefault();
	         var order_deli_no =$(this).parent().find("#ORDER_DELI_NO").val();
	         deliupdate(order_deli_no, order_no);
	               
	      });   
</script>
</html>