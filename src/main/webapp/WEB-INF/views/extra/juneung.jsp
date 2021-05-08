<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/_inc/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/juneung.css?time=${currentTime}" />
<link rel="stylesheet" type="text/css" href="${contextPath}/assets/plugins/sweetalert/sweetalert2.min.css?time=${currentTime}" />
<c:choose>
<%--jstl로 회원의 jn_result가 있는지 검사 --%>
	<%--주능 검사 이력이 있을 경우 아래 body 태그 출력 --%>
	<c:when test="${output.jn_result != null }">
</body>

<%--body 뜨자마자 jn() 함수 호출 -> sweet alert로 재검사 여부 묻기 -> juneung.js 에 있음 ! --%>
<body onload="jn();">
</c:when>
</c:choose>

<div id="juneung_content" >
    <h1>술학능력시험 - 주(酒)능</h1>

    <form action="${pageContext.request.contextPath}/juneung_ok.do" method="post" name="imgradio" data-tab="my_recommend">

        <h2>${output.name }님의 최애 주종+안주 조합 고르기</h2>

        <h4>내가 좋아하는 술</h4>
        <label>
            <input type="radio" name="drink" value="beer" required="required"/>
            <img src="${contextPath}/assets/img/juneung/d_beer.png"  class="icons"/>
            <span class="icon_description text_visible">맥주</span>
        </label>
        <label>
            <input type="radio" name="drink" value="soju" />
            <img src="${contextPath}/assets/img/juneung/d_soju.png" class="icons"/>
            <span class="icon_description text_visible">소주</span>
        </label>
        <label>
            <input type="radio" name="drink" value="wine">
            <img src="${contextPath}/assets/img/juneung/d_wine.png" class="icons">
            <span class="icon_description text_visible">와인</span>
        </label>
        <label>
            <input type="radio" name="drink" value="ricewine">
            <img src="${contextPath}/assets/img/juneung/d_ricewine.png" class="icons">
            <span class="icon_description text_visible">막걸리</span>
        </label>

        <h4>내가 좋아하는 음식</h4>
        <label>
            <input type="radio" name="food" value="meat" required="required" />
            <img src="${contextPath}/assets/img/juneung/f_meat.png" class="icons">
            <span class="icon_description text_visible">고기</span>
        </label>
        <label>
            <input type="radio" name="food" value="fish">
            <img src="${contextPath}/assets/img/juneung/f_fish.png" class="icons">
            <span class="icon_description text_visible">생선</span>
        </label>
        <label>
            <input type="radio" name="food" value="fruit">
            <img src="${contextPath}/assets/img/juneung/f_fruit.png" class="icons">
            <span class="icon_description text_visible">과일</span>
        </label>
        <label>
            <input type="radio" name="food" value="snack">
            <img src="${contextPath}/assets/img/juneung/f_snack.png" class="icons">
            <span class="icon_description text_visible">견과류</span>
        </label>

        <h2>오늘의 술자리 분위기</h2>

        <h4>누구와 함께 하나요?</h4>
        <label>
            <input type="radio" name="people" value="alone" required="required" />
            <img src="${contextPath}/assets/img/juneung/p_alone.png" class="icons">
            <span class="icon_description text_visible">혼술</span>
        </label>
        <label>
            <input type="radio" name="people" value="lover">
            <img src="${contextPath}/assets/img/juneung/p_couple.png" class="icons">
            <span class="icon_description text_visible">연인</span>
        </label>
        <label>
            <input type="radio" name="people" value="friends" >
            <img src="${contextPath}/assets/img/juneung/p_friend.png" class="icons">
            <span class="icon_description text_visible">친구</span>
        </label>
        <label>
            <input type="radio" name="people" value="family">
            <img src="${contextPath}/assets/img/juneung/p_family.png" class="icons">
            <span class="icon_description text_visible">가족</span>
        </label>

        <h4>오늘 당신의 기분은?</h4>
        <label>
            <input type="radio" name="mood" value="sunny" required="required" />
            <img src="${contextPath}/assets/img/juneung/m_sunny.png" class="icons">
            <span class="icon_description text_visible">맑음</span>
        </label>
        <label>
            <input type="radio" name="mood" value="cloudy">
            <img src="${contextPath}/assets/img/juneung/m_cloudy.png" class="icons">
            <span class="icon_description text_visible">흐림</span>
        </label>
        <label>
            <input type="radio" name="mood" value="rainy">
            <img src="${contextPath}/assets/img/juneung/m_rainy.png" class="icons">
            <span class="icon_description text_visible">비</span>
        </label>
        <div class="juneung_buttons">
	        <button type="submit" onclick="check()">나의 주능결과</button>
	        <button type="reset">다시하기</button>
        </div>
    </form>
</div>
<script src="${contextPath}/assets/plugins/sweetalert/sweetalert2.min.js?time=${currentTime}" type="text/javascript" charset="utf-8"></script>

<script src="${contextPath}/assets/js/juneung.js?time=${currentTime}" type="text/javascript" charset="utf-8"></script>
<%@ include file="/WEB-INF/views/_inc/footer.jsp"%>