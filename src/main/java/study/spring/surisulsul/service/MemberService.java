package study.spring.surisulsul.service;

import java.util.List;
import study.spring.surisulsul.model.Member;

public interface MemberService {

	// 세션 정보를 받아 회원정보 조회(마이페이지 등)
	public Member getMemberItem(Member input) throws Exception;

	// 이메일 찾기를 위한 조회
	public Member getMemberEmail(Member input) throws Exception;

	// 로그인을 위한 회원정보 조회
	public Member getMemberLogin(Member input) throws Exception;

	// 로그인을 위한 회원정보 업데이트
	public Member editMemberLogin(Member input) throws Exception;

	// 전체 회원 목록 조회 (관리자용)
	public List<Member> getMemberList(Member input) throws Exception;

	// 탈퇴 회원 목록 조회 (관리자용)
	public List<Member> getOutMemberList(Member input) throws Exception;

	// 전체 회원 수 조회 (관리자용) 
	public int getMemberCount(Member input) throws Exception;

	// 탈퇴 회원 수 조회 (관리자용)
	public int getOutMemberCount(Member input) throws Exception;

	// 오늘 가입 회원 수 조회(관리자용)
	public int getMemberInTodayCount() throws Exception;

	// 오늘 탈퇴 회원 수 조회(관리자용)
	public int getMemberOutTodayCount() throws Exception;

	// 회원정보 수정(관리자용) - 비밀번호 유지
	public int editMemberAll(Member input) throws Exception;
	
	// 회원정보 수정(관리자용) - 비밀번호 수정
	public int editMemberPwAll(Member input) throws Exception;	

	// 회원가입
	public int addMember(Member input) throws Exception;

	// 회원가입 시 나이 검사
	public int chkAge(Member input) throws Exception;

	// 회원정보 수정 및 비밀번호 재발급
	public int editMember(Member input) throws Exception;

	// 탈퇴한 회원의 데이터 삭제
	public int deleteMember(Member input) throws Exception;

	// 중복 이메일 조회
	public int getJoinEmailCount(Member input) throws Exception;

	// 비밀번호 확인용 조회
	public Member getChkPw(Member input) throws Exception;

	// 비밀번호 찾기용 조회
	public int getPasswordCount(Member input) throws Exception;

	public Member getPasswordMember(Member input) throws Exception;

	// 로그인 시 이메일 체크용 조회
	public int getLoginEmailCount(Member input) throws Exception;

	// 주능 회원정보 수정
	public int editJnMember(Member input) throws Exception;

	// 회원 탈퇴
	public int outMember(Member input) throws Exception;
}
