<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#list").on("click", function(e){ //목록으로 버튼
		e.preventDefault();
		fn_openTOPPINGList();
	});
	
	$("#write").on("click", function(e){ //작성하기 버튼
		e.preventDefault();
		 if($('#TOPPING_NAME').val()==""){
			alert("상품명을 입력해 주세요.");
			return false;
		 } else if($('#TOPPING_PRICE').val()==""){
			 alert("상품가격을 입력해 주세요.");
				return false;
		 } else if($('#TOPPING_TYPE').val()==''){
			 alert("상품 타입을 선택해 주세요.");
				return false;
		 } else if($('#TOPPING_THUMBNAIL').val()==""){
			 alert("썸네일이미지를 등록해 주세요.");
				return false;
		 } else if($('#TOPPING_DETAIL').val()==""){
			 alert("상품 상세설명을 입력해 주세요.");
				return false;
		 } else if($('#TOPPING_AMOUNT').val()==""){
			 alert("상품 수량을 입력해 주세요.");
				return false;
		 } else if($('#IMAGE').val()==""){
			 alert("상품이미지를 등록해 주세요.");
				return false;
		 }
		 else{
		fn_insertGood();
		}
	});
	
	$("#addFile").on("click", function(e){ //파일 추가 버튼
		e.preventDefault();
		fn_addFile();
	});
	
	$("#addKind").on("click", function(e){ //파일 추가 버튼
		e.preventDefault();
		fn_addKind();
	});
	
	$("a[name='delete']").on("click", function(e){ //삭제 버튼
		e.preventDefault();
		fn_deleteKind($(this));
	});
});

function fn_openTOPPINGList(){
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("/SG/adminTOPPINGList");
	comSubmit.submit();
}

function fn_insertGood(){
	var comSubmit = new ComSubmit("frm");
	comSubmit.setUrl('/SG/adminTOPPINGInsert');
	comSubmit.submit();
}
function fn_addFile(){
	var str = "<p><input type='file' name='IMAGE'> <a href='#this' class='btn' name='delete'>삭제</a></p>";
	$("#fileDiv").append(str);
	$("a[name='delete']").on("click", function(e){ //삭제 버튼
		e.preventDefault();
		fn_deleteFile($(this));
	});
}

function fn_deleteFile(obj){
	obj.parent().remove();
}


</script>
</head>
<body>

<div class="row" style="padding-left:15px;width:900px;">    
	<h1 class="page-header">재료등록</h1>
</div>
	<form id="frm" name="frm" enctype="multipart/form-data">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*"/>
			</colgroup>
			<caption>재료등록</caption>
			<tbody>
			    <tr>
					<th scope="row">카테고리</th>
					<td>
						<select id="TOPPING_TYPE" name="TOPPING_TYPE" size=1>
						<OPTION value=''>---TYPE을 선택하세요---</OPTION>
						<OPTION value='0'>샐러드</OPTION>
						<OPTION value='1'>드레싱</OPTION>
						<OPTION value='2'>음료수</OPTION>
						</select>
					</td>
				</tr>
				
				<tr>
					<th scope="row">상품 노출 여부</th>
					<td>
						<select id="TOPPING_ONOFF" name="TOPPING_ONOFF" size=1>
						<OPTION value=''>---ON/OFF를 선택하세요---</OPTION>
						<OPTION value='0'>ON</OPTION>
						<OPTION value='1'>OFF</OPTION>
						</select>
					</td>
				</tr>


				<tr>
					<th scope="row">상품명</th>
					<td><input type="text" id="TOPPING_NAME" name="TOPPING_NAME" class="wdp_90"></input></td>
				</tr>
				<tr>
					<th scope="row">가격</th>
					<td><input type="text" id="TOPPING_PRICE" name="TOPPING_PRICE" class="wdp_90"></input></td>
				</tr>

				<tr>
					<th scope="row">썸네일 이미지</th>
					<td><input type="file" id="TOPPING_THUMBNAIL" name="TOPPING_THUMBNAIL"></td>
				</tr>
				<tr>
					<th scope="row">수량</th>
					<td><input type="text" id="TOPPING_AMOUNT" name="TOPPING_AMOUNT" class="wdp_90"></input></td>
				</tr>
				<tr>
					<th scope="row">칼로리</th>
					<td><input type="text" id="TOPPING_KCAL" name="TOPPING_KCAL" class="wdp_90"></input></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text">
						<textarea rows="10" cols="100" title="내용" id="TOPPING_DETAIL" name="TOPPING_DETAIL" placeholder="상세 설명"></textarea>
					</td>
				</tr>
				<tr>
					
					
				<td width="15%"><a href="#this" class="btn" id="addFile">이미지 추가</a></td>
				<td width="50%"><div id="fileDiv"><p><input type="file" id="IMAGE" name="IMAGE_IMAGE"></p></td>
				<td width="35%"><a href="#this" class="btn" id="delete" name="delete">삭제</a></p></div></td>
				</tr>
			</tbody>
		</table>
		
		<br/><br/>
		<a href="#this" class="btn" id="write">작성하기</a>
		<a href="#this" class="btn" id="list">목록으로</a>
	</form>
</body>
</html>