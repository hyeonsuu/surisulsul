package study.spring.surisulsul.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import study.spring.surisulsul.helper.PageData;
import study.spring.surisulsul.helper.RegexHelper;
import study.spring.surisulsul.helper.WebHelper;
import study.spring.surisulsul.model.Basket;
import study.spring.surisulsul.model.Member;
import study.spring.surisulsul.model.Order;
import study.spring.surisulsul.model.Product;
import study.spring.surisulsul.model.Qna;
import study.spring.surisulsul.model.Review;
import study.spring.surisulsul.model.Wishlist;
import study.spring.surisulsul.service.MemberService;
import study.spring.surisulsul.service.ProductService;
import study.spring.surisulsul.service.ReviewAndQnaService;
import study.spring.surisulsul.service.WishlistService;

@Controller
@Slf4j
public class MypageController {
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;

	/** RegexHelper 주입 */
	@Autowired
	RegexHelper regexHelper;

	/** Service 패턴 구현체 주입 */
	@Autowired
	MemberService memberService;

	@Autowired
	ProductService productService;

	@Autowired
	WishlistService wishlistService;

	@Autowired
	ReviewAndQnaService reviewAndQnaService;

	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;

	/** 회원 정보 확인 */
	@RequestMapping(value = "/mypage/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(Model model, HttpServletRequest request) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");
		Member output = null;

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = contextPath + "/account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요한 페이지입니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}

			/** View 처리 */
			model.addAttribute("output", output);
		}
		return new ModelAndView("mypage/mypage");
	}

	/** 주능 결과 확인 */
	@RequestMapping(value = "/mypage/my_recommend.do", method = RequestMethod.GET)
	public ModelAndView my_recommend(Model model, HttpServletRequest request) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");
		Member output = null;
		List<Product> pro_output = null;

		boolean jn_result = false;
		List<Product> jn_output = null;
		String jn_result_name = null;

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = contextPath + "/account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요한 페이지입니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}

			if (output.getJn_result() == null) {// 로그인 O / 주능결과 X
				jn_result = false;
			} else {// 로그인 O / 주능결과 O
				jn_result = true;
				jn_result_name = output.getJn_result();

				Product input = new Product();
				input.setJn_result(output.getJn_result());

				jn_output = new ArrayList<Product>();

				/** 주능 결과에 해당하는 술 가져오기 */
				try {
					pro_output = productService.jn_ProductList(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/** View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("jn_output", jn_output);
		model.addAttribute("jn_result", jn_result);
		model.addAttribute("jn_result_name", jn_result_name);
		model.addAttribute("pro_output", pro_output);

		return new ModelAndView("mypage/my_recommend");
	}

	/** 상품 상세페이지 이동 --> ItemController */

	/** 위시리스트 확인 */
	@RequestMapping(value = "/mypage/wishlist.do", method = RequestMethod.GET)
	public ModelAndView wishlist(Model model, HttpServletRequest request) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");

		int wish_count = 0;
		boolean wishlist;
		Member output = null;
		List<Product> member_product = null;

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = contextPath + "/account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요합니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}

			// 데이터 저장하기
			Wishlist input = new Wishlist();
			input.setM_id(loginSession.getId());

			try {
				// wishlist 갯수 조회
				wish_count = wishlistService.getWishlistCount(input);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (wish_count == 0) {// 로그인 O / 위시리스트 X
				wishlist = false;
			} else {// 로그인 O / 위시리스트 O
				wishlist = true;

				Product product = new Product();
				product.setM_id(output.getId());

				try {
					member_product = productService.wish_ProductList(product);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		/** View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("member_product", member_product);

		return new ModelAndView("mypage/wishlist");
	}

	/** 내가 작성한 리뷰/문의 확인 */
	@RequestMapping(value = "/mypage/my_opinion.do", method = RequestMethod.GET)
	public ModelAndView my_opinion(Model model, HttpServletRequest request,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value = "page", defaultValue = "1") int nowPage,
			@RequestParam(value = "q_page", defaultValue = "1") int q_nowPage) {

		/** 0) 세션값 받아오기 */
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");
		Member output = null;

		/** 1-1) 리뷰 페이지 구현에 필요한 변수값 생성 */
		int totalCount = 0; // 전체 게시글 수
		int listCount = 4; // 한 페이지당 표시할 목록 수
		int pageCount = 5; // 한 그룹당 표시할 페이지 번호 수

		/** 1-2) 문의 페이지 구현에 필요한 변수값 생성 */
		int q_totalCount = 0; // 전체 게시글 수
		int q_listCount = 4; // 한 페이지당 표시할 목록 수
		int q_pageCount = 5; // 한 그룹당 표시할 페이지 번호 수

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = contextPath + "/account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요합니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
		}
		/** 2) 리뷰 데이터 조회 */
		Review input = new Review();
		input.setM_id(output.getId());

		List<Review> reviewOutput = null; // 조회 결과가 저장될 객체
		PageData pageData = null;

		try {
			// 전체 리뷰 수 조회
			totalCount = reviewAndQnaService.getMemberReviewCount(input);

			// 페이지 번호 계산 --> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT 절에서 사용될 값을 Beans의 static 변수에 저장
			Review.setOffset(pageData.getOffset());
			Review.setListCount(pageData.getListCount());

			// 데이터 조회하기
			reviewOutput = reviewAndQnaService.getMemberReviewList(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** 2-2) 문의 데이터 조회 */
		Qna q_input = new Qna();
		q_input.setM_id(output.getId());

		List<Qna> qnaOutput = null; // 조회 결과가 저장될 객체
		PageData q_pageData = null;

		try {
			// 전체 문의 수 조회
			q_totalCount = reviewAndQnaService.getMemberQnaCount(q_input);

			// 페이지 번호 계산 --> 계산 결과를 로그로 출력
			q_pageData = new PageData(q_nowPage, q_totalCount, q_listCount, q_pageCount);

			// SQL의 LIMIT 절에서 사용될 값을 Beans의 static 변수에 저장
			Qna.setOffset(q_pageData.getOffset());
			Qna.setListCount(q_pageData.getListCount());

			// 데이터 조회하기
			qnaOutput = reviewAndQnaService.getMemberQnaList(q_input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** 3) View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("reviewOutput", reviewOutput);
		model.addAttribute("pageData", pageData);
		model.addAttribute("qnaOutput", qnaOutput);
		model.addAttribute("q_pageData", q_pageData);

		return new ModelAndView("mypage/my_opinion");
	}

	/** 탈퇴하기 */
	@RequestMapping(value = "/mypage/is_out.do", method = RequestMethod.GET)
	public ModelAndView is_out(Model model, HttpServletRequest request) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");
		int output = 0;

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = "../account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요한 페이지입니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				output = memberService.outMember(member);
				session.invalidate();
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
		}
		return new ModelAndView("redirect:/");
	}

	/** 나의 리뷰 선택 삭제 처리 */
	@RequestMapping(value = "/mypage/delete_ok.do", method = RequestMethod.POST)
	public ModelAndView delete_ok(Model model, HttpServletRequest request,
			@RequestParam(value = "reviewItem", required = false) List<String> chkItems) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");

		// input 값에 쓰일 basketId 값 선언
		int reviewId = 0;

		if (chkItems != null) {
			for (int i = 0; i < chkItems.size(); i++) {
				// 데이터 삭제에 필요한 조건값을 Beans에 저장
				Review input = new Review();
				input.setM_id(loginSession.getId());
				reviewId = Integer.parseInt(chkItems.get(i));
				input.setId(reviewId);

				// 데이터 삭제
				try {
					reviewAndQnaService.deleteReview(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else { // 체크된 박스 없이 submit됐을 경우
			return webHelper.redirect(null, "삭제할 리뷰가 없습니다.");
		}
		return webHelper.redirect(contextPath + "/mypage/mypage.do#my_opinion", null);
	}

	/** 나의 문의 선택 삭제 처리 */
	@RequestMapping(value = "/mypage/qna_delete_ok.do", method = RequestMethod.POST)
	public ModelAndView qna_delete_ok(Model model, HttpServletRequest request,
			@RequestParam(value = "qnaItem", required = false) List<String> chkItems) {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");

		// input 값에 쓰일 basketId 값 선언
		int qnaId = 0;

		if (chkItems != null) {
			for (int i = 0; i < chkItems.size(); i++) {
				// 데이터 삭제에 필요한 조건값을 Beans에 저장
				Qna input = new Qna();
				input.setM_id(loginSession.getId());
				qnaId = Integer.parseInt(chkItems.get(i));
				input.setId(qnaId);

				// 데이터 삭제
				try {
					reviewAndQnaService.deleteQna(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else { // 체크된 박스 없이 submit됐을 경우
			return webHelper.redirect(null, "삭제할 문의가 없습니다.");
		}
		return webHelper.redirect(contextPath + "/mypage/mypage.do#my_opinion", null);
	}

}