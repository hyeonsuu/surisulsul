package study.spring.surisulsul.controllers;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import study.spring.surisulsul.helper.WebHelper;
import study.spring.surisulsul.model.Member;
import study.spring.surisulsul.model.Product;
import study.spring.surisulsul.service.MemberService;
import study.spring.surisulsul.service.ProductService;
import study.spring.surisulsul.service.SalesService;

@Slf4j
@Controller
public class HomeController {

	/** Service 패턴 구현체 주입 */
	
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;
	
	@Autowired
	ProductService productService;

	@Autowired
	MemberService memberService;

	@Autowired
	SalesService salesService;

	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		// 세션 값 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member)session.getAttribute("loginInfo");
		String manageLoginSession = (String) session.getAttribute("manager_id"); //관리자 로그인 세션			
								
		// 관리자 로그아웃 처리(세션 삭제)
		if(manageLoginSession != null) { 
			session.removeAttribute("manager_id");
			System.out.println("관리자 로그아웃 성공>>>>>>>>>>>>");
		}
		
		/** 주능 결과, 그에 따라 추천하는 술 표시 */
		
		// 결과를 저장할 객체
		// jn_result의 true, false에 따라서 화면 구성이 달라진다.
		boolean jn_result = false;
		Member output = null;
		List<Product> jn_output = null;

		// 로그인 세션이 없을 경우 -> alert 창 
		if (loginSession == null) {
			
			jn_result = false;

		} else { // 로그인 세션이 있는 경우 -> 로그인 된 사용자가 있을 경우
			Member member = new Member();
			member.setId(loginSession.getId());

			try {
				// 세션 정보를 받아 회원정보 조회(마이페이지 등) -> id 정보를 검사
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		/** 로그인 여부, 주능 테스트 여부에 따라 달라지는 화면 */
			if (output.getJn_result() == null) { // 로그인 O , 주능결과 X -> 주능 테스트 배너 띄움.
				
				jn_result = false;
				
			} else {// 로그인 O , 주능결과 O -> 로그인 된 사용자의 결과를 확인하고, 그 타입에 맞게 술을 추천함.
				
				jn_result = true;
				
				// 주능 결과에 맞는 상품 불러오기
				Product input = new Product();
				input.setJn_result(loginSession.getJn_result()); // 로그인 되어 있는 주능결과를 받아옴

				jn_output = new ArrayList<Product>();

				try {
					jn_output = productService.jn_ProductList(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/** 인기 많은 술 best 4 */
		
		List<Product> best_output = new ArrayList<Product>();
		int sales_cnt = 0; // sales 테이블에 데이터 수 조회 결과 저장

		try {
			
			// 매출 데이터가 있는 리스트를 조회
			// 조회 후 매출이 없을때, 있는데 4개 미만일 때 등의 if문 
			sales_cnt = salesService.getSalesCountNotNull(null);
			
			// 매출이 없을 때 (== 0), 비싼 가격순대로 정렬
			if(sales_cnt == 0) {
				best_output = productService.main_best_ProductList_price(null);
			}
			
			// 매출이 있는 상품이 4개 미만일 경우 (매출내역 + 비싼가격순)으로 조회
			else if(sales_cnt < 12) {
				best_output = productService.main_best_ProductList_sales_price(null);
			}
			
			// 인기 상품 목록 4개 정렬
			else {
				best_output = productService.main_best_ProductList(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("jn_output", jn_output);
		model.addAttribute("jn_result", jn_result);
		model.addAttribute("best_output", best_output);

		return "home";
	}

	/** 로그아웃 처리 */
	// 로그아웃 클릭 시, 로그인 세션을 모두 삭제함 (invalidate -> 세션 무효화, 삭제)
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {

		session.invalidate();
		
		// 세션 삭제(로그아웃)후 메인 페이지로 돌아감.
		return "redirect:/";
	}

}