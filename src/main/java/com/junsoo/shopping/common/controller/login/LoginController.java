package com.junsoo.shopping.common.controller.login;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junsoo.shopping.common.service.login.LoginService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class LoginController {

	@Inject
	private LoginService service;
	private static final Logger logger=  LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 로그인 페이지로 이동하는 메소드
	 * @param remeberCookie
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/loginPage", method = RequestMethod.GET)
	public ModelAndView loginGet() throws Exception{
		logger.info("login get");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("contents/login/loginPage");
		return mv;
	}

	/**
	 * 로그인 버튼 동작시 아이디와 암호화된 비밀번호를 비교하여 세션 유지시키는 메소드
	 * @param vo
	 * @param req
	 * @param rttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login/loginPost", method = RequestMethod.POST)
	public String loginPost(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("login post");
		HttpSession session = req.getSession();
		UserVO login = service.selectOneUser(vo);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		try {
			boolean passMatch = passwordEncoder.matches(vo.getPassword(), service.selectPassword(vo.getUser_id()));
			if(login != null && passMatch) {
				session.setAttribute("user", login.getUser_id());
				return "redirect:/";
			}
			else {
				session.setAttribute("user", null);
				rttr.addFlashAttribute("msg", false);
				return "contents/login/loginPage";
			}
		}
		catch (NullPointerException npe) {
			logger.warn(npe.getMessage());
			vo=null;
			return "redirect:/";
		}
	}
	
	/**
	 * 로그아웃 이벤트 발생시 세션해제
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		logger.info("logout");
		session.invalidate();
		return "redirect:/";
	}
	
}
