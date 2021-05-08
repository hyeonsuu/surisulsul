package study.spring.surisulsul.controllers;

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
import study.spring.surisulsul.helper.RegexHelper;
import study.spring.surisulsul.helper.WebHelper;
import study.spring.surisulsul.model.Member;
import study.spring.surisulsul.service.MemberService;

@Controller
@Slf4j
public class ExtraController {
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;

	/** RegexHelper 주입 */
	@Autowired
	RegexHelper regexHelper;

	/** Service 패턴 구현체 주입 */
	@Autowired
	MemberService memberService;
	
	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	/* 탁주 페이지로 연결 */
	@RequestMapping(value = "/takju.do", method = RequestMethod.GET)
	public String takju(Model model) {
		
		return "extra/takju";
	}
	
	/* 청주 페이지로 연결 */
	@RequestMapping(value = "/cheongju.do", method = RequestMethod.GET)
	public String cheongju(Model model) {
		
		return "extra/cheongju";
	}
	
	/* 과실주 페이지로 연결 */
	@RequestMapping(value = "/fruit_drink.do", method = RequestMethod.GET)
	public String fruit_drink(Model model) {
		
		return "extra/fruit_drink";
	}
	
	/* 증류주 페이지로 연결 */
	@RequestMapping(value = "/spirits.do", method = RequestMethod.GET)
	public String spirits(Model model) {
		
		return "extra/spirits";
	}
	
	/* 기타주류 페이지로 연결 */
	@RequestMapping(value = "/liquerurs.do", method = RequestMethod.GET)
	public String liquerurs(Model model) {
		
		return "extra/liquerurs";
	}
	
	/** 주능 페이지로 연결 */
	@RequestMapping(value = "/juneung.do", method = RequestMethod.GET)
	public ModelAndView juneung(Model model, HttpServletRequest request) {
		// 세션값 받아오기
        // 로그인 여부 확인 -> 로그인 페이지로 이동
		// session에서 logininfo 받아오기
		HttpSession session = request.getSession();
		Member loginSession = (Member) session.getAttribute("loginInfo");
		Member output = null;
		
		boolean jn_result = false;
		
		// 로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
		if (loginSession == null) {
			String redirectUrl = contextPath+"/account/login.do";
			return webHelper.redirect(redirectUrl, "로그인이 필요한 페이지입니다.");

		} else { // 로그인 세션이 있는 경우 = 로그인 된 사용자가 있다는 뜻
			
			Member member = new Member();
			member.setId(loginSession.getId());
			 
			try {
				output = memberService.getMemberItem(member);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
           
			
            if (loginSession.getJn_result() == null) { // 로그인 O / 주능결과 X
				jn_result = false;
			} else {// 로그인 O / 주능결과 O
				jn_result = true;
              }
            }
		
            model.addAttribute("output", output);
            model.addAttribute("jn_result", jn_result);
		return new ModelAndView("extra/juneung");
		
	}
	
	/** 주능 페이지에서 가져온 값 처리 */
	@RequestMapping(value = "/juneung_ok.do", method = RequestMethod.POST)
	public ModelAndView juneung_ok(Model model, HttpServletRequest request,
			@RequestParam(value="drink", required=true) String drink,
			@RequestParam(value="food", required=true) String food,
			@RequestParam(value="people", required=true) String people,
			@RequestParam(value="mood", required=true) String mood) {
		
		// 세션 정보 가져오기
		HttpSession session = request.getSession();		
		Member loginSession = (Member) session.getAttribute("loginInfo");
		
		// 로그인 세션이 없을 경우 = 로그인 되어있지 않을 경우 alert 발생
		if(loginSession == null) { 
			return webHelper.redirect(contextPath+"/account/login.do", "로그인 후 이용해주세요.");
		} else { // 로그인 세션이 있는 경우 = 로그인된 사용자가 있다는 뜻
			Member input = new Member();
			String result = null;
			
			if((drink.equals("beer") || drink.equals("wine")) && (food.equals("meat") || food.equals("fish"))){result = "무궁화"; }
			if((drink.equals("soju") || drink.equals("ricewine")) && (food.equals("meat") || food.equals("fish"))){result = "수국";}
			if((drink.equals("beer") || drink.equals("wine")) && (food.equals("fruit") || food.equals("snack"))){result = "진달래";}
			if((drink.equals("soju") || drink.equals("ricewine")) && (food.equals("fruit") || food.equals("snack"))){result = "해바라기";}
			
			/** 데이터 저장하기 */
			input.setId(loginSession.getId());
			input.setAddress2("222"); 
			input.setJn_result(result);
			
			try {
				memberService.editJnMember(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return webHelper.redirect(contextPath+"/mypage/mypage.do", "결과가 나왔습니다!");
	}
	
	
	
}
