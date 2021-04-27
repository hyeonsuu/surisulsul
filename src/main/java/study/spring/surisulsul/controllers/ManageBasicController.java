package study.spring.surisulsul.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import study.spring.surisulsul.helper.WebHelper;
import study.spring.surisulsul.model.Member;
import study.spring.surisulsul.model.Order;
import study.spring.surisulsul.service.ManageOrdersService;
import study.spring.surisulsul.service.MemberService;
import study.spring.surisulsul.service.OrderService;
import study.spring.surisulsul.service.ReviewAndQnaService;

@Controller
public class ManageBasicController {
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;

	/** Service 패턴 구현체 주입 */
	@Autowired
	MemberService memberService;

	@Autowired
	OrderService orderService;

	@Autowired
	ManageOrdersService manageService;

	@Autowired
	ReviewAndQnaService reviewAndQnaService;

	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;

	/** 관리자 - 로그인 페이지 이동 처리 */
	@RequestMapping(value = "/manage.do", method = RequestMethod.GET)
	public ModelAndView manage_login(Model model, HttpServletRequest request) throws Exception {
		// 세션값 받아오기
		HttpSession session = request.getSession();
		String manageLoginSession = (String) session.getAttribute("manager_id");

		if (manageLoginSession != null) {
			return webHelper.redirect(null, "관리자 페이지 로그인이 이미 되어있습니다.");
		}

		return new ModelAndView("manage/manage_login");

	}

	/** 관리자 - 로그인 action 페이지 */
	@RequestMapping(value = "/manage_login_ok.do", method = RequestMethod.POST)
	public ModelAndView login_ok(Model model, HttpServletRequest request,
			@RequestParam(value = "manager_id", required = false) String id,
			@RequestParam(value = "manager_pw", required = false) String pw) {

		/** 관리자 ID / password와 일치하는지 확인 */
		if (id.equals("managerSuri")) {
			if (pw.equals("sul123!@#")) {
				// 로그인 성공 시 세션 객체 생성
				HttpSession session = request.getSession();
				session.setAttribute("manager_id", id);
			} else {
				return webHelper.redirect(null, "패스워드가 잘못되었습니다. 다시 시도해주세요.");
			}
		} else {
			return webHelper.redirect(null, "ID가 잘못되었습니다. 다시 시도해주세요.");
		}

		/** 관리자 홈으로 이동 */
		String redirectUrl = contextPath + "/manage_home.do";
		return webHelper.redirect(redirectUrl, "관리자 로그인 성공");
	}

	/** 관리자 - 로그인 확인 후 manage_home 페이지로 이동 처리 */
	@RequestMapping(value = "/manage_home.do", method = RequestMethod.GET)
	public ModelAndView manage_login_ok(Model model, HttpServletRequest request) {
		// 세션 값 받아오기
		HttpSession session = request.getSession();
		String manageLoginSession = (String) session.getAttribute("manager_id"); // 관리자 로그인 세션

		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (manageLoginSession == null) {
			return webHelper.redirect(contextPath + "/manage.do", "관리자 로그인 후 이용해주세요..");
		}

		// 값 저장할 객체
		int todayIn = 0; // 오늘 가입 회원 수
		int todayOut = 0; // 오늘 탈퇴 회원 수
		int qnaCount = 0; // 오늘 문의 수
		int orderCount = 0; // 오늘 주문 수

		// 조회한 데이터 저장하기 : 가입 회원 수
		try {
			todayIn = memberService.getMemberInTodayCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 조회한 데이터 저장하기 : 탈퇴 회원 수
		try {
			todayOut = memberService.getMemberOutTodayCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 조회한 데이터 저장하기 : 문의 수
		try {
			qnaCount = reviewAndQnaService.getNewQnaCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 조회한 데이터 저장하기 : 주문 수
		try {
			orderCount = manageService.getNewOrderCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/** 차트용 */
		
		// 결과를 담을 객체
		List<Order> output = new ArrayList<Order>();		
		List<String> dateList = new ArrayList<String>();
		
		//오늘의 날짜
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String todayDate = null; 
		
		//해당 날짜 주문 여부 확인 : 주문이 있을 때 = 1, 주문이 없을 때 0
		int count = 0;
		
		// 7일 반복문
		for(int i=0; i<7; i++) {
			//날짜 List에 넣기
			todayDate = format.format(calendar.getTime());
			dateList.add(todayDate);
			
			//새로운 객체 생성 -> for문 밖에서 생성시 오류
			Order input = new Order();
			input.setFrom_date(todayDate);
			
			//해당 날짜에 주문이 있는지 여부 확인
			try {
				count = manageService.getOrderChk(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//주문 내역이 없다면 -> 0으로 설정
			if(count == 0) {
				input.setSum_price(0);
				input.setReg_date(todayDate);
				output.add(input);

			}else {
				//주문 내역이 있다면 -> 데이터 불러오기
				Order tmp = new Order();
				try {
					tmp = manageService.getOrderSum(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
				output.add(tmp);
			}
			calendar.add(calendar.DATE, -1);
		}
		
		System.out.println(output);
		

		model.addAttribute("todayIn", todayIn);
		model.addAttribute("todayOut", todayOut);
		model.addAttribute("qnaCount", qnaCount);
		model.addAttribute("orderCount", orderCount);
		model.addAttribute("output", output);
		model.addAttribute("dateList", dateList);
		
		return new ModelAndView("manage/manage_home");
	}
}
