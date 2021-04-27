package study.spring.surisulsul.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import study.spring.surisulsul.helper.PageData;
import study.spring.surisulsul.helper.RegexHelper;
import study.spring.surisulsul.helper.WebHelper;
import study.spring.surisulsul.model.Cscenter;
import study.spring.surisulsul.model.Qna;
import study.spring.surisulsul.model.Review;
import study.spring.surisulsul.service.CscenterService;

@Controller
public class CscenterController {
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;

	/** RegexHelper 주입 */
	@Autowired
	RegexHelper regexHelper;

	/** Service 패턴 구현체 주입 */
	@Autowired CscenterService cscenterService;
	
	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	
	/** 고객센터 페이지로 이동 */
	@RequestMapping(value="/cscenter/cscenter_main.do", method=RequestMethod.GET)
	public String cscenter_main(Model model
			//@RequestParam(value="default", defaultValue="a")String type
			) {
		//model.addAttribute("type", type);
		//return webHelper.redirect("cscenter_main", null);
		return "cscenter/cscenter_main";
	}
	
	/** 공지사항 페이지로 이동 */
	@RequestMapping(value="/cscenter/notice.do", method=RequestMethod.GET)
	public ModelAndView notice(Model model,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value="page", defaultValue="1") int nowPage) {
		/** 1) 페이지 구현에 필요한 변수값 생성*/
		int totalCount = 0;
		int listCount = 10;
		int pageCount = 5;
		
		/** 2) 데이터 조회하기 */
		List<Cscenter> output = null; // 조회 결과가 저장될 객체 -> cscenter에 있는 리스트를 가져온다.
		PageData pageData = null; 
		
		try {
			// 전체 게시글 수 조회
			totalCount = cscenterService.getNoticeList(null).size();
			
			// 페이지 번호 계산 --> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);
			
			// SQL의 limit 절에서 사용될 값을 Beans의 static 변수에 저장
			Review.setOffset(pageData.getOffset());
			Review.setListCount(pageData.getListCount());
			
			// 데이터 조회하기
			output = cscenterService.getNoticeList(null);
			
			} catch(Exception e) {
			
			return webHelper.redirect(null, e.getLocalizedMessage());
			
			}
		
		/** 3) View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		return new ModelAndView("cscenter/cscenter_notice");
	}
	
	/** faq 페이지로 이동 */
	@RequestMapping(value="/cscenter/faq.do", method=RequestMethod.GET)
	public ModelAndView faq(Model model,
			// 페이지 구현에서 사용할 현재 페이지 번호
			@RequestParam(value="page", defaultValue="1") int nowPage) {
		/** 1) 페이지 구현에 필요한 변수값 생성*/
		int totalCount = 0;
		int listCount = 10;
		int pageCount = 5;
		
		/** 2) 데이터 조회하기 */
		
		List<Cscenter> output = null; // 조회 결과가 저장될 객체
		PageData pageData = null;
		
		try {
			// 전체 게시글 수 조회
			totalCount = cscenterService.getFaqList(null).size();
			
			// 페이지 번호 계산 --> 계산 결과를 로그로 출력
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);
			
			// SQL의 limit 절에서 사용될 값을 Beans의 static 변수에 저장
			Review.setOffset(pageData.getOffset());
			Review.setListCount(pageData.getListCount());
			
			// 데이터 조회하기
			output = cscenterService.getFaqList(null);
		}catch(Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		/** 3) View 처리 */
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);
		return new ModelAndView("cscenter/cscenter_faq");
	}
}
