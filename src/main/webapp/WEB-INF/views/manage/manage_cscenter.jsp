<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/_inc/manage_header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/assets/manage/manage_cscenter.css?time=${currentTime}" />

<div class="content">
	<h1>공지사항 & FAQ 관리</h1>
	<br /> <br /> <a
		href="${pageContext.request.contextPath}/manage_cscenter_add.do"
		class="add_button">등록</a> <br />
	<form name="m_cscenter_form" id="m_cscenter_form" method="post">
		<table class="manage_cs_table">
			<thead>
				<tr>
					<th width=60>번호</th>
					<th>분류</th>
					<th>이름</th>
					<th>제목</th>
					<th width=150>등록날짜</th>
					<th width=150>수정날짜</th>
					<th width=130>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${output == null}">
						<tr colspan="7">
							<h1>등록된 글이 없습니다.</h1>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="output" items="${output}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<c:choose>
									<c:when test="${output.type == 'A'}">
										<td>알립니다</td>
									</c:when>
									<c:when test="${output.type == 'B'}">
										<td>자주 묻는 질문</td>
									</c:when>
								</c:choose>
								<td>관리자</td>
								<td>${output.title}</td>
								<td>${output.reg_date}</td>
								<td>${output.edit_date}</td>

								<%-- 수정페이지로 이동하기 위한 URL --%>

								<c:url value="/manage_cscenter_edit.do" var="editUrl">
									<c:param name="cs_id" value="${output.id}" />
								</c:url>
			<td><a href="${editUrl}" class="edit_button">수정</a>

	<form method="get" action="${pageContext.request.contextPath }/manage_cscenter_delete_ok.do" style="display: inline-block;">
		<input type="hidden" name="id" id="all" value="${output.id}" />
		<button type="submit" class="cs_delete">삭제</button>
		</form></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>

</div>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>