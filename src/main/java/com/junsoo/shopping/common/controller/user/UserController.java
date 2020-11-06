package com.junsoo.shopping.common.controller.user;

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

import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
@RequestMapping(value = "/contents")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService userService;
	
	/**
	 * 로그인 페이지로 이동하는 메소드
	 * @param remeberCookie
	 * @return ModelAndView
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
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/login/loginPost", method = RequestMethod.POST)
	public String loginPost(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("login post");
		HttpSession session = req.getSession();
		UserVO login = userService.selectOneUser(vo);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		try {
			boolean passMatch = passwordEncoder.matches(vo.getPassword(), userService.selectPassword(vo.getUser_id()));
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
	
	/**
	 * 회원수정 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/modifyForm", method = RequestMethod.GET)
	public ModelAndView getModify(HttpServletRequest request) throws Exception{
		
		logger.info("getModify called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		
		try {
			if(user == null) {
				mv.setViewName("contents/errorForm");
			}
			else {
				mv.addObject("userVO", userService.selectOneUser(user));
				mv.setViewName("contents/modify/modifyForm");
			}
		} catch (NullPointerException npe) {
			logger.warn(npe.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		}
		
		return mv;
	}
	
	/**
	 * 회원수정기능
	 * @param userVO
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/modifyForm", method = RequestMethod.POST)
	public String postModify(UserVO userVO) throws Exception{
		
		String page = "contents/modify/modifyComplete";
		try {
			logger.info("postModify method called");
			userService.updateUser(userVO);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			page = "redirect:/";
		} catch (Exception e) {
			logger.error(e.getMessage());
			page = "redirect:/";
		}
		
		return page;
	}
	
	/**
	 * 비밀번호수정 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/modifyPassword", method = RequestMethod.GET)
	public ModelAndView getModifyPassword(HttpServletRequest request) throws Exception{
		
		logger.info("getModifyPassword called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		try {
			if(user == null) {
				mv.setViewName("contents/errorForm");
			}
			else {
				mv.addObject("userVO", userService.selectOneUser(user));
				mv.setViewName("contents/modify/modifyPassword");
			}
		} catch (NullPointerException npe) {
			logger.warn(npe.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		} catch (Exception e) {
			logger.warn(e.getMessage());
			mv.setViewName("/:redirect/contents/errorForm");
		}
		return mv;
	}
	
	/**
	 * 비밀번호 수정기능
	 * @param userVO
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/modifyPassword", method = RequestMethod.POST)
	public String postModifyPassword(UserVO userVO, HttpServletRequest request) throws Exception{
		
		String page = "contents/modify/modifyComplete";
		try {
			logger.info("postModifyPassword method called");
			userService.updatePassword(userVO, request);
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			page = "redirect:/";
		} catch (Exception e) {
			logger.error(e.getMessage());
			page = "redirect:/";
		}
		
		return page;
	}
	
	/**
	 * 회원탈퇴 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/signOut", method = RequestMethod.GET)
	public ModelAndView getSignOut(HttpServletRequest request) throws Exception {
		
		logger.info("getSignOut method called");
		ModelAndView mv = new ModelAndView();
		String user = (String)request.getSession().getAttribute("user");
		
		try {
			mv.setViewName("contents/user/signOut");
			mv.addObject("userVO", userService.selectOneUser(user));
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/errorForm");
		}
		return mv;
	}
	
	/**
	 * 회원탈퇴 기능 구현
	 * @param userVO
	 * @param request
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/signOut", method = RequestMethod.POST)
	public ModelAndView postSignOut(UserVO userVO, 
									HttpServletRequest request, 
									HttpSession session) throws Exception {
		
		logger.info("postSignOut method called");
		ModelAndView mv = new ModelAndView();
		
		try {
			if(userVO != null) {
				mv.addObject("result", "회원탈퇴");
				mv.setViewName("contents/complete");
				userVO = userService.selectOneUser(
						(String)request.getSession().getAttribute("user"));
				userService.signOut(userVO);
				session.invalidate();
			}
		} catch (NullPointerException npe) {
			logger.error(npe.getMessage());
			mv.setViewName("contents/errorForm");
		} catch (Exception e) {
			logger.error(e.getMessage());
			mv.setViewName("contents/errorForm");
		}
		return mv;
	}
}
