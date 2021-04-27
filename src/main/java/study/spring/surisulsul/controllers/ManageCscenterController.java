package study.spring.surisulsul.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import study.spring.surisulsul.model.Cscenter;
import study.spring.surisulsul.model.Product;
import study.spring.surisulsul.service.CscenterService;

@Slf4j
@Controller
public class ManageCscenterController {
	
	/** Service 패턴 구현체 주입 */
	
	/** WebHelper 주입 */
	@Autowired
	WebHelper webHelper;
	
	/** RegexHelper 주입 */
	@Autowired
	RegexHelper regexHelper;
	
	@Autowired
	CscenterService cscenterService;
	
	
	/** 프로젝트 이름에 해당하는 ContextPath 변수 주입 */
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	/** manage_cscenter 목록 조회  */
	@RequestMapping(value = "/manage_cscenter.do", method =  RequestMethod.GET)
	public ModelAndView manage_cscenter(Model model, HttpServletRequest request) {		
		
		// 세션 값 받아오기
	      HttpSession session = request.getSession();      
	      String manageLoginSession = (String) session.getAttribute("manager_id");         
	      
	      // 로그인 세션이 없을 경우 = 로그인 되어있지 않을 경우 alert 발생
	      if(manageLoginSession == null) { 
	         return webHelper.redirect(contextPath+"/manage.do","관리자 로그인 후 이용해주세요..");
	      }
	      
			List<Cscenter> output = null;
			
			try {
				output = cscenterService.getmanageList();
				System.out.println(output);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			model.addAttribute("output", output);
		
		return new ModelAndView("manage/manage_cscenter");
		
	}
	
	/** 알립니다 & FAQ 등록  */
	@RequestMapping(value = "/manage_cscenter_add.do", method = RequestMethod.GET)
	public ModelAndView cscenter_add(Model model, HttpServletRequest request) {
		
		//세션값 받아오기
	      HttpSession session = request.getSession();      
	      String manageLoginSession = (String) session.getAttribute("manager_id");         
	      
	      //로그인 세션이 없을 경우 = 로그인되어있지 않을 경우 alert 발생
	      if(manageLoginSession==null) { 
	         return webHelper.redirect(contextPath+"/manage.do","관리자 로그인 후 이용해주세요..");
	      }
	      
		return new ModelAndView("manage/manage_cscenter_add");
	}
	
	/** 알립니다 & FAQ 등록 action 페이지 */
	@RequestMapping(value="/manage_cscenter_add_ok.do", method=RequestMethod.POST)
	public ModelAndView cscenter_add_ok(Model model,
			@RequestParam(value="cs_section", required = false) String type,
			@RequestParam(value="cs_title", required = false) String title,
			@RequestParam(value="cs_content", required = false) String content,
			@RequestParam(value="reg_date", required = false) String reg_date,
			@RequestParam(value="edit_date", required = false) String edit_date)  
	
	{
		/** 파라미터 유효성 검사 **/
		
		if (title.equals("")) {
			return webHelper.redirect(null, "제목을 입력하세요.");
		}
		
		if (type.equals("")) {
			return webHelper.redirect(null, "분류를 선택하세요.");
		}
		
		if (content.equals("")) {
			return webHelper.redirect(null, "내용을 입력하세요.");
		}
		
		/** 데이터 저장하기 */
		// 저장할 값을 beans에 담는다.
		Cscenter input = new Cscenter();
		input.setType(type);
		input.setTitle(title);
		input.setContent(content);
		input.setReg_date(reg_date);
		input.setEdit_date(edit_date);
		
		try {
			cscenterService.addCscenter(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		String redirectUrl = contextPath + "/manage_cscenter.do";
		return webHelper.redirect(redirectUrl, "새로운 글이 등록되었습니다.");
	}
	
	/** 알립니다 & FAQ 수정 */
	@RequestMapping(value="/manage_cscenter_edit.do", method=RequestMethod.GET)
	public ModelAndView cscenter_edit(Model model, HttpServletRequest request,
			@RequestParam(value="cs_id", defaultValue = "0") int cs_id)  {
		
		//세션값 받아오기
	      HttpSession session = request.getSession();      
	      String manageLoginSession = (String) session.getAttribute("manager_id");         
	      
	      // 로그인 세션이 없을 경우 = 로그인 되어있지 않을 경우 alert 발생
	      if(manageLoginSession == null) { 
	         return webHelper.redirect(contextPath+"/manage.do","관리자 로그인 후 이용해주세요..");
	      }
	      
	      if (cs_id == 0) {
				return webHelper.redirect(null, "글 등록 번호가 없습니다."); 
			}
			
	      /** 데이터 조회하기 **/
			Cscenter input = new Cscenter();
			input.setId(cs_id);
			
			//조회한 상품데이터 저장할 객체
			Cscenter output = null;
			
			
			try {
				output = cscenterService.getCscenter(input);
			} catch (Exception e) {
				return webHelper.redirect(null, e.getLocalizedMessage());
			}
			
			model.addAttribute("output", output);
	      return new ModelAndView("manage/manage_cscenter_edit");
	}
	
	/** 알립니다 & FAQ 수정 action 페이지 */
	@RequestMapping(value="/manage_cscenter_edit_ok.do", method=RequestMethod.POST)
	public ModelAndView cscenter_edit_ok(Model model,
			@RequestParam(value="cs_id", defaultValue = "0") int id,
			@RequestParam(value="cs_section", required = false) String type,
			@RequestParam(value="cs_title", required = false) String title,
			@RequestParam(value="cs_content", required = false) String content)  
	
	{
		/** 파라미터 유효성 검사 **/
		
		if (title.equals("")) {
			return webHelper.redirect(null, "제목을 입력하세요.");
		}
		
		if (type.equals("")) {
			return webHelper.redirect(null, "분류를 선택하세요.");
		}
		
		if (content.equals("")) {
			return webHelper.redirect(null, "내용을 입력하세요.");
		}
		
		/** 데이터 저장하기 */
		// 저장할 값을 beans에 담는다.
		Cscenter input = new Cscenter();
		input.setId(id);
		input.setType(type);
		input.setTitle(title);
		input.setContent(content);
		
		try {
			cscenterService.editCscenter(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		String redirectUrl = contextPath + "/manage_cscenter.do";
		return webHelper.redirect(redirectUrl, "문의가 수정되었습니다.");
	}
	
	/** 알립니다 & FAQ 삭제 */
	@RequestMapping(value="/manage_cscenter_delete_ok.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cscenter_delete(Model model, HttpServletResponse response,
			@RequestParam(value="id", defaultValue = "0") int id)  {
		
		if (id == 0) {
			return webHelper.redirect(null, "글 등록 번호가 없습니다."); 
		}
		
		Cscenter input = new Cscenter();
		input.setId(id);
		
		try {
			cscenterService.deleteCscenter(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}
		
		return webHelper.redirect(contextPath + "/manage_cscenter.do", "삭제되었습니다.");
	}
	
	
}
