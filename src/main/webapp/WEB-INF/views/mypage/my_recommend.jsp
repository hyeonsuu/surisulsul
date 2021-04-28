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
<div class="psn_recommend">
<c:choose>
		<%--c:when test 조건 -> 주능 테스트 이력이 있는 경우 -> 주능 결과 DB 내용 존재 --%>
		<c:when test="${jn_result }">
	<div class="psn_title">
			<c:choose>
				<%--c:when test 조건 -> 무궁화 --%>
				<c:when test="${jn_result_name eq '무궁화'}" >
		<h2 class="psn_title_txt">나의 주(酒)능 결과 : <span class="flower_type">무궁화</span></h2>
		<p>엉뚱하고 가끔 알 수 없지만 알고보면 섬세한 감정을 보유한 당신<br/>
		당신은 자신만의 환상의 세계에 대해 자주 꿈꾸곤 합니다.<br/>
		현재를 즐길 줄 알며, 자신만의 공간을 중요시합니다.<br/>
		자신만의 도덕적 원칙이 있으며, 분쟁을 마주하는 용기가 있습니다.<br/>
		강한 의지와 신념을 지니고 있어 목표를 이루기 위해 끈질기게 노력하는 경향이 있습니다. </p>
	</div>
		<div class="psn_jn_result">
		<img alt="무궁화" src="${contextPath }/assets/img/mypage/flower01.png"/>
		</div>
		
		<h3 class="psn_title_dsc">무궁화 같은 당신을 위해 추천하는 술</h3>
		<div class="psn_keyword">
			<a class="psn_keyword1" href=# onclick="return false;">#국내파</a>
			<a class="psn_keyword2" href=# onclick="return false;">#육류와_잘어울리는</a>
			<a class="psn_keyword3" href=# onclick="return false;">#깔끔한</a>
			<a class="psn_keyword4" href=# onclick="return false;">#높은도수</a>
		</div>
				</c:when>
				
				<%--c:when test 조건 -> 해바라기 --%>
				<c:when test="${jn_result_name eq '해바라기'}" >
		<h2 class="psn_title_txt">나의 주(酒)능 결과 : <span class="flower_type">해바라기</span></h2>
		<p>고귀하고 자유로우며 내면에서부터 빛을 뿜어내는 당신<br/>
		성격이 둥글둥글한 당신은 주위 사람들을 소중히 여길 줄 아는 사람입니다.<br/>
		목표 달성을 위해 집중할 수 있는 확고한 의지를 지니고 있습니다.<br/>
		또한, 자신이 하는 일을 즐기며 재밌는 인생을 살아가는 것을 중요시합니다.<br/>
		털털하며 매력적인 성격을 지니고 있어 주위 사람들을 즐겁게 만드는 능력이 있습니다. </p>
	</div>
		<div class="psn_jn_result">
		<img alt="해바라기" src="${contextPath }/assets/img/mypage/flower02.png"/>
		</div>
		
		<h3 class="psn_title_dsc">해바라기 같은 당신을 위해 추천하는 술</h3>
		<div class="psn_keyword">
			<a class="psn_keyword1" href=# onclick="return false;">#해외파</a>
			<a class="psn_keyword2" href=# onclick="return false;">#육류와_잘어울리는</a>
			<a class="psn_keyword3" href=# onclick="return false;">#달콤한</a>
			<a class="psn_keyword4" href=# onclick="return false;">#낮은도수</a>
		</div>
				</c:when>
				
				<%--c:when test 조건 -> 진달래 --%>
				<c:when test="${jn_result_name eq '진달래'}" >
		<h2 class="psn_title_txt">나의 주(酒)능 결과 : <span class="flower_type">진달래</span></h2>
		<p>모든 일에 애정이 넘치며 어디서나 눈에 띄는 당신<br/>
		꿈을 이루기 위해서라면 무엇이든 할 수 있는 당신입니다.<br/>
		자신의 삶에 높은 기대치를 지니고 있으며 목표 달성에 중점을 두고 살아갑니다.<br/>
		문제를 해결하기 위해 계획을 짜는 것을 좋아하며 새로운 아이디어를 받아들일 줄 압니다.<br/>
		세상을 바꾸는데 관심이 있어 문제점을 발견하고, 고치기 위한 해결책을 찾는데 능숙합니다. </p>
	</div>
		<div class="psn_jn_result">
		<img alt="진달래" src="${contextPath }/assets/img/mypage/flower03.png"/>
		</div>
		
		<h3 class="psn_title_dsc">진달래 같은 당신을 위해 추천하는 술</h3>
		<div class="psn_keyword">
			<a class="psn_keyword1" href=# onclick="return false;">#국내파</a>
			<a class="psn_keyword2" href=# onclick="return false;">#가벼운</a>
			<a class="psn_keyword3" href=# onclick="return false;">#깔끔한</a>
			<a class="psn_keyword4" href=# onclick="return false;">#중간도수</a>
		</div>
				</c:when>
				
				<%--c:when test 조건 -> 수국 --%>
				<c:when test="${jn_result_name eq '수국'}" >
		<h2 class="psn_title_txt">나의 주(酒)능 결과 : <span class="flower_type">수국</span></h2>
		<p>부드럽고 침착하며, 뛰어난 감성을 지닌 당신<br/>
		당신은 정서적으로 안정적이며 사교성이 좋습니다.<br/>
		공감 능력이 뛰어나며 사랑하는 사람들을 챙기는 것을 좋아합니다.<br/>
		사람들을 좋아하는 당신은 남들과 함께 일하는 것을 즐깁니다.<br/>
		타인에게 친절을 베푸는 당신은 주위사람들의 마음을 평온하게 만드는 능력이 있습니다.</p>
	</div>
		<div class="psn_jn_result">
		<img alt="수국" src="${contextPath }/assets/img/mypage/flower04.png"/>
		</div>
		
		<h3 class="psn_title_dsc">수국 같은 당신을 위해 추천하는 술</h3>
		<div class="psn_keyword">
			<a class="psn_keyword1" href=# onclick="return false;">#해외파</a>
			<a class="psn_keyword2" href=# onclick="return false;">#가벼운</a>
			<a class="psn_keyword3" href=# onclick="return false;">#상큼한</a>
			<a class="psn_keyword4" href=# onclick="return false;">#낮은도수</a>
		</div>
				</c:when>
			</c:choose>
			
			
	<div class="psn_result">
	<ul class="psn_result_inner">
	<%--조회 결과에 따른 반복처리 --%>
	<c:forEach var="item" items="${pro_output}" varStatus="status">
		<li class="pick_item">
		
		<%--상세 페이지로 이동하는 URL 
		출력된 상품의 item.id에 따른 상세 페이지로 이동한다. --%>
		<c:url value="/item_details.do" var="viewUrl">
			<c:param name="prodid" value="${item.id}" />
		</c:url>
		
			<a href="${viewUrl}" class="pick_link"> 
			<span class="psn_pick_bg">
				<img class="psn_pick" src="${contextPath}/assets/img/items/${item.img}">
			</span> 
			<span class="psn_pick_text">
					<h3 class="psn_pick_title">${item.name}</h3>
					<p class="psn_pick_price"><fmt:formatNumber value="${item.price}" pattern="#,###"/>원</p>
			</span>
		</a></li>
	</c:forEach>
	</ul>
	</div>
	<div class="psn_jn_again">
	<a href="${pageContext.request.contextPath}/juneung.do" class="btn_yellow">💌주(酒)능 다시보기</a>
	</div>
	</c:when>
	
		<%--c:otherwise --> 주능 테스트 이력이 없는 경우 -> 주능 결과 DB에 데이터 X --%>
		<c:otherwise>
		<div class="psn_title">
		<h2 class="psn_title_txt">나의 주(酒)능 결과</h2>
	</div>
	<div class="psn_no_result">
		<img src="${contextPath}/assets/img/mark_alert.png"/>
		<h3 class="psn_title_dsc">
		아직 주(酒)능 응시 기록이 없습니다<br/>
		<span class="psn_gotest">나만을 위한 술을 추천받기 위해 술학능력시험:주(酒)능을 응시하러 가볼까요? </span>
		</h3>
		<a href="${pageContext.request.contextPath}/juneung.do" class="btn_yellow">👉1분 만에 보는 주(酒)능</a>
	</div>
		</c:otherwise>
</c:choose>
</div>
