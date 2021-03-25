<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/_inc/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/css/index.css" />
<!-- ========== 컨텐츠 영역 시작 ========== -->
<!-- 개인(정현수) 수정버전 입니다. -->
<div class="content">
	<!-- 1. 메인배너 3개 -> 슬라이드 화살표, 버튼 구현 -->
	<div id="main_banner">
		<div id="slides">
			<ul class="slides-container">
				<!-- 슬라이드 1 (무료배송, 고객센터로 이동)-->
				<li>
				<a href="${contextPath}/cscenter/cscenter_main.do#notice"> 
				<img src="${contextPath}/assets/img/index/banner1.png" alt="메인배너" />
				</a>
				</li>
				
				<!-- 슬라이드 2 (내가 찾는 술) -->
				<li>
				<a href="${pageContext.request.contextPath}/item_filtered.do">
				<img src="${contextPath}/assets/img/index/banner2.png" alt="메인배너" />
				</a>
				</li>
				
				<!-- 슬라이드 3 (우리 술 종류 -> 탁주로 이동합니다.) -->
				<li>
				<a href="${pageContext.request.contextPath}/takju.do">
				<img src="${contextPath}/assets/img/index/banner3.png" alt="메인배너" />
				</a>
				</li>
			</ul>
		</div>
	</div>
	
	<!-- 2. 주능 테스트 결과
        1. 로그인 ok , 테스트 ok -> 주능 결과에 맞게 요약 보여줌
        2. 로그인 ok , 테스트 x -> 주능 테스트 해보세요 ! 배너 띄움
        3. 로그인 x -> 주능 테스트 해보세요 ! 배너 띄움 -->
        
	<c:choose>
		<c:when test="${jn_result}">
			<%-- 1번의 경우 (true) --%>
			<div class="main_best">
				<h1>취향에 맞는 술을 추천해드려요 !</h1>
				<c:forEach var="item" items="${jn_output}" varStatus="status">
					<c:url value="/item_details.do" var="viewUrl">
						<c:param name="prodid" value="${item.id}" />
					</c:url>
					<a href="${viewUrl}">
						<div class="main_best_img" id="best_img1">
							<img src="${contextPath}/assets/img/items/${item.img}" alt="주능 결과 추천 술" />
							<div class="over">
								<div class="main_best_over">
									<span class="over_title">${item.name}</span>
									<p class="over_des"><fmt:formatNumber value="${item.price}" pattern="#,###" />원</p>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</c:when>
		
		<c:otherwise>
			<%-- 2,3번의 경우 (false) --%>
			<div class="juneung_empty">
				<div class="test_img">
					<a href="${pageContext.request.contextPath}/juneung.do"> 
					<img src="${contextPath}/assets/img/index/juneung.png" alt="주능 테스트배너" />
					</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
	<!-- 3. 인기 많은 술 best 4 -->
	<div class="main_best" id="main_best_top">
		<h1>인기 많은 술</h1>
		<c:forEach var="item" items="${best_output}" varStatus="status">
			<c:url value="/item_details.do" var="viewUrl">
					<c:param name="prodid" value="${item.id}" />
			</c:url> 
				<a href="${viewUrl}">
					<div class="main_best_img" id="best_img1">
						<img src="${contextPath}/assets/img/items/${item.img}" alt="인기많은술1" />
						<div class="over">
						<div class="main_best_over">
						<span class="over_title">${item.name}</span>
						<p class="over_des"><fmt:formatNumber value="${item.price}" pattern="#,###" />원</p>
						</div>
						</div>
					</div>
				</a>
		</c:forEach>
	</div>
	<!-- 4. 우리 술 종류
         각 종류의 대표 이미지 , hover시 강조효과
         img, 종류명 클릭 시 각 페이지로 이동 가능(a태그에 같은 페이지 걸어두기) -->
	<span id="main_drink"></span>
	<div class="main_drink">
		<h1>우리 술 종류</h1>
		<ul class="box" id="box1">
			<li><a href="${pageContext.request.contextPath}/takju.do"><img
					src="${contextPath}/assets/img/drinks/takju.jpg" alt="탁주" /></a> <br />
				<span class="main_drink_text">탁주</span></li>
		</ul>
		<ul class="box">
			<li><a href="${pageContext.request.contextPath}/fruit_drink.do"><img
					src="${contextPath}/assets/img/drinks/fruit_drink.jpg" alt="과실주" /></a>
				<br /> <span class="main_drink_text">과실주</span></li>
		</ul>
		<ul class="box">
			<li><a href="${pageContext.request.contextPath}/cheongju.do"><img
					src="${contextPath}/assets/img/drinks/cheongju.png" alt="약주/청주" /></a>
				<br /> <span class="main_drink_text">약주/청주</span></li>
		</ul>
		<div class="main_drink_2">
			<ul class="box" id="box2">
				<li><a href="${pageContext.request.contextPath}/spirits.do"><img
						src="${contextPath}/assets/img/drinks/soju.jpg" alt="증류주" /></a> <br />
					<span class="main_drink_text">증류주</span></li>
			</ul>
			<ul class="box">
				<li><a href="${pageContext.request.contextPath}/liquerurs.do"><img
						src="${contextPath}/assets/img/drinks/extra_drink.jpg" alt="기타주류" /></a>
					<br /> <span class="main_drink_text">기타주류</span></li>
			</ul>
		</div>
	</div>
</div>

<!--// ========== 컨텐츠 영역 끝 ========== -->
<%@ include file="/WEB-INF/views/_inc/footer.jsp"%>
<!--  스크립트 적용 -->
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 플러그인 JS 참조 -->
<script
	src="${contextPath}/assets/plugins/superslides/jquery.easing.1.3.js?time=${currentTime}"
	type="text/javascript" charset="utf-8"></script>
<script
	src="${contextPath}/assets/plugins/superslides/jquery.superslides.min.js?time=${currentTime}"
	type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/assets/js/index.js?time=${currentTime}"
	type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/assets/js/juneung.js?time=${currentTime}" type="text/javascript" charset="utf-8"></script>