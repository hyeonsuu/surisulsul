<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/_inc/manage_header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/manage/manage_orders.css?time=${currentTime}" />
	
<div class="content">
    <h2>주문내역</h2>
    <div class="manage_orders_count">
    전체 000건
    </div>
    <form action="${pageContext.request.contextPath}/manage_orders.do" action="POST" name="manage_orders_form" id="manage_orders_form">
    	<div class="manage_orders_details">
	    	<label for="order_status"><b>주문상태</b></label>
	    	<input type="radio" name="order_status" value="all" checked/>전체
	    	<input type="radio" name="order_status" value="order_cmpl" />주문완료
	    	<input type="radio" name="order_status" value="pay_cmpl" />입금완료
	    	<input type="radio" name="order_status" value="send_cmpl" />배송완료
    	</div>
    	
    	<div class="manage_orders_details">
	    	<label for="pay_method"><b>결제수단</b></label>
	    	<input type="radio" name="pay_method" value="all" checked/>전체
	    	<input type="radio" name="pay_method" value="cash" />무통장
	    	<input type="radio" name="pay_method" value="card" />카드
    	</div>
    	
    	<div class="manage_orders_details">
	    	<label for="date"><b>주문일자</b></label>
	    	<input type="date" name="from_date"/> ~ 
	    	<input type="date" name="to_date"/>
    	<button type="submit" class="search_button">조회</button>
    	</div>
    </form>
    <table class="manage_order_table">
    	<tr>
    		<th colspan="3">주문일-주문번호</th>
    		<th>주문자</th>
    		<th>주문자전화</th>
    		<th>받는분</th>
    		<th rowspan="2">주문합계</th>
    		<th rowspan="2">입금합계</th>
    		<th rowspan="2">미수금</th>
    		<th rowspan="2">상세보기</th>
    	</tr>
    	<tr>
    		<th>주문상태</th>
    		<th>결제수단</th>
    		<th>최근변경일</th>
    		<th colspan="2">주문상품수</th>
    		<th colspan="2">누적주문수</th>
    	</tr>
    	
		<%-- 조회 결과에 따른 반복 처리 --%>
    	<c:forEach var="item" items="${output }" varStatus="status">
	    	<tr>
	    		<td colspan="3">${item.reg_date }-${item.o_id }</td>
	    		<td>${item.b_name }</td>
	    		<td>${item.b_phone }</td>
	    		<td>${item.r_name }</td>
	    		<td rowspan="2" class="price"><fmt:formatNumber value="${item.price }" pattern="#,###"/></td>
	    		<td rowspan="2" class="price">0</td>
	    		<td rowspan="2" class="price">110,000</td>
	    		<td rowspan="2"><a href="${pageContext.request.contextPath}/manage_order_details.do" class="show_button">보기</a></td>
	    	</tr>
	    	<tr>
	    		<td>입금대기</td>
	    		<td>무통장</td>
	    		<td>${item.edit_date }</td>
    			<td colspan="2">3개</td>
    			<td>${item.order_cnt }건</td>
	    	</tr> 
    	</c:forEach>   	
    </table>
</div>

</body>

</html>