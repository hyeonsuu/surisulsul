<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/_inc/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/drinks.css" />

	<div id="drinks_img">
			
			
		<img src="${contextPath}/assets/img/drinks/cheongju.png" class="drinks_img_detail" /> 
		<span id="drinks_name">
			<h1>한국의 다양한 약주/청주</h1>
			<h3>
				문화재로 지정된 발효주들이 대체로 청주가 아니라 약주에 속하며
				<br /> 청주는 탁주의 상대적인 말로, 본래 맑은 술을
				뜻한다.
			</h3> 
			<!-- item_filtered에서 id 값 일치 -> 선택된 상태로 페이지 이동이 가능하다. -->
			<form method="post" action="${pageContext.request.contextPath}/item_filtered.do" name="filtered_form" id="filtered_form">
			<input type="hidden" name="types" class="types" id="types3" value="청주" />
			<input type="submit" value="약주/청주 종류별 보기 >" id="go_cheongju" class="go_drink_button"/>
			</form>
		</span>
	</div>
	<div id="drinks_content">
		<h2>
			현재 우리술에서 약주는 맑은 술의 대명사로 쓰인다. <br />문화재로 지정된 발효주들이 대체로 청주가 아니라 약주에
			속한다.
		</h2>
		<div>
			약주에는 몇 가지 복합적인 의미가 담겨있다.<br /> 약재가 들어간 술이거나 약효가 있는 술의 뜻을 담고 있다.<br />
			술을 높여 불러 약주라 하고, 어른들이 술을 마시면 “약주 드셨습니까?”하고 여쭤본다.<br /> 청주와 같은 의미로 약주를
			사용하기도 한다.<br /> 현재 우리 주세법에 따르면 약주와 청주를 구분하는 기준은 전통누룩의 사용량이다.<br />
			약주에는 전통누룩을 1% 이상 사용하고, 청주에는 1% 미만을 사용하게 되어 있다.<br /> 본질적인 차이는 약주는
			야생효소나 효모의 집합체인 전통 누룩을 사용하고, 청주는 분리 추출한 효소를 쌀알에 배양한 흩임누룩을 사용한다는 점이다.<br />
			즉 현재 청주는 일본스타일 청주를 지칭하는 말로 쓰이고 있다.<br /> 이는 일제 식민지를 거치면서 생겨난 변화로,
			개념어에 대한 재검토가 필요하다.<br />
		</div>
		
		<h2>
			청주는 탁주의 상대적인 말로, 본래 맑은 술을 뜻한다.
		</h2>
		<div>
			하지만 청주가 법적 명칭이 되면서 다른 의미를 띠게 되었다.<br />
			주세법에 청주는<br />
			1) 곡류 중 쌀(찹쌀을 포함한다), 국(麴) 및 물을 원료로 하여 발효시킨 술덧을 여과하여 제성한 것 또는 그 발효·제성 과정에 대통령령으로 정하는 재료를 첨가한 것.<br />
			2) 1)에 따른 주류의 발효·제성 과정에 대통령령으로 정하는 주류 또는 재료를 혼합하거나 첨가하여 여과하여 제성한 것으로서<br />
			알코올분 도수가 대통령령으로 정하는 도수 범위 내인 것’으로 규정하고 있다.<br />
			그리고 원료의 사용에서 청주는 ‘전분질 원료의 합계 중량을 기준으로 하여 누룩을 100분의 1미만으로 사용하여야 한다’고 규정하고 있다.
		</div>
	</div>
<%@ include file="/WEB-INF/views/_inc/footer.jsp"%>