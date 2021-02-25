<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- JSTL로 변수 생성 --%>
<%-- 1) 캐시를 방지하기 위해 자주 수정되는 css,js 파일에 설정하는 용도 --%>
<c:set var="currentTime" value="<%=System.currentTimeMillis()%>" />
<%-- 2) 프로젝트이름 기반의 절대경로값 --%>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!doctype html>
<html>

<head>
<meta charset="UTF-8" />
<title>surisulsul</title>
<!-- 크로스 브라우징 호환성 시작 -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/css/reset.css" />
<!-- 크로스 브라우징 호환성 끝 -->
<!-- CSS 파일 참조 처리 -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/manage/manage_common.css?time=${currentTime}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/manage/manage_cscenter.css?time=${currentTime}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/css/popup.css?time=${currentTime}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/plugins/sweetalert/sweetalert2.min.css?time=${currentTime}" />

</head>

<body>
	<div class="pop-up">
		<div class="pop-up-title">알립니다 & 자주묻는질문 등록</div>
		<div class="pop-up-content">
			<form id="cscenter_add" action="${pageContext.request.contextPath}/manage/manage_cscenter.do" method="post">
				<input type="hidden" name="add_cs" value="" />
				<div class="form-group">
					<label class="label text-center">분류 선택</label>
					<br />
					<select
						id="category" class="category" name="type">
						<option value="0">--------</option>
						<option value=1>알립니다</option>
						<option value=2>자주묻는질문</option>
					</select>
				</div>
				<div class="form-group">
					<label class="label text-center">문의 내용 작성하기</label><br />
					<textarea class="write-content" placeholder="내용을 입력해 주세요."
						name="content"></textarea>
					<span class="input-limit">0/1,000</span>
				</div>
				<div>
					<button type="submit">등록하기</button>
				</div>
			</form>
		</div>
	</div>

	<!--Google CDN 서버로부터 jQuery 참조 -->
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- jQuery Ajax Form plugin CDN -->
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
	<!-- jQuery Ajax Setup -->
	<script
		src="${pageContext.request.contextPath}/assets/plugins/ajax/ajax_helper.js"></script>
	<script
		src="${contextPath}/assets/plugins/sweetalert/sweetalert2.min.js?time=${currentTime}"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	$(function() {
	// #addForm에 대한 submit이벤트를 가로채서 Ajax요청을 전송한다.
    $("#cscenter_add").ajaxForm({
        // 전송 메서드 지정
        method: "POST",
        // 서버에서 200 응답을 전달한 경우 실행됨
        success: function(json) {
            console.log(json);
            
            if (json.result == "OK") {
            	swal({
                    title: "제품 문의",
                    text: "선택하신 제품에 대한 문의 작성이 완료되었습니다.",
                    type: "success"
                }).then((value)=>{
                	window.opener.location.href="${pageContext.request.contextPath}/manage_cscenter.do";
                	window.close();	                	
                });                   
            } else if (json.result=="NOT_CONTENT"){
            	swal({
                    title: "내용 입력",
                    text: "등록하실 내용을 입력해 주세요.",
                    type: "warning"
                });
            } else {
            	swal({
                    title: "내용 입력",
                    text: "공지 등록에 실패했습니다. 다시 시도해주세요.",
                    type: "error"
                });
            }
        }
    });
});
    $('.write-content').keyup(function(e) {
        var content = $(this).val();
        $('.input-limit').html(content.length + "/1,000"); //글자수 실시간 카운팅

        if (content.length > 1000) {
            alert("최대 1,000자까지 입력 가능합니다.");
            $(this).val(content.substring(0, 1000));
            $('.input-limit').html("1,000/1,000");
        }
    });
    </script>
</body>