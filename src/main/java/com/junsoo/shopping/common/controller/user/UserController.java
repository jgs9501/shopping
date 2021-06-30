package com.junsoo.shopping.common.controller.user;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.junsoo.shopping.common.service.user.UserService;
import com.junsoo.shopping.common.service.user.UserpointService;
import com.junsoo.shopping.common.vo.UserVO;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService userService;
	@Inject
	UserpointService userpointService;
	/**
	 * 로그인 페이지로 이동하는 메소드
	 * @param remeberCookie
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getLogin(HttpServletRequest request,
					UserVO userVO,
					@RequestParam(defaultValue = "false") boolean error) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		if(error) {
			String msg = "회원정보";
			mv.addObject("result", msg);
		}
		mv.setViewName("/contents/user/login");
		return mv;
	}

//  ****** Spring Security로 이관하여 loginPost 메소드 제거
//	/**
//	 * 로그인 버튼 동작시 아이디와 암호화된 비밀번호를 비교하여 세션 유지시키는 메소드
//	 * @param vo
//	 * @param req
//	 * @param rttr
//	 * @return String
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/loginPost", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView loginPost(UserVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
//		
//		HttpSession session = req.getSession();
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		ModelAndView mv = new ModelAndView();
//		String page = "index";
//		UserVO login = userService.selectOneUser(vo.getUser_id());
//		System.out.println("login Post : " + vo);
//		boolean passMatch = passwordEncoder.matches(vo.getPassword(), 
//								userService.selectPassword(vo.getUser_id()));
//			// 유저 아이디가 없을 경우
//			if(login == null) {
//				session.setAttribute("user", null);
//				session.setAttribute("userVO", null);
//				rttr.addFlashAttribute("msg", false);
//				mv.addObject("result", "아이디");
//				page = "contents/user/login";
//			}
//			// 비밀번호가 다를 경우
//			else if(!passMatch) {
//				session.setAttribute("user", null);
//				session.setAttribute("userVO", null);
//				rttr.addFlashAttribute("msg", false);
//				mv.addObject("result", "비밀번호");
//				page = "contents/user/login";
//			}
//			// 로그인이 성공할 경우
//			else {
//				session.setAttribute("userVO", login);
//				session.setAttribute("user", login.getUser_id());
//			}
//			System.out.println(login.getUser_id());
//		
//		mv.setViewName(page);
//		return mv;
//	}
	
	/**
	 * 로그아웃 이벤트 발생시 세션해제
	 * @param session
	 * @return redirect:/
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/index";
	}
	
	/**
	 *  회원가입 페이지 이동
	 * @return
	 */
	@RequestMapping(value = "/user/regist", method = RequestMethod.GET)
	public String getRegister(){
		
		logger.info("getRegist method called");
		return "contents/user/regist";
	}

	/**
	 * 회원가입 기능
	 * @param userVO
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/regist", method = RequestMethod.POST)
	public ModelAndView postRegister(UserVO userVO, HttpServletRequest request) throws Exception{
		
		String page = "contents/complete";
		ModelAndView mv = new ModelAndView();
		
		userService.insertUser(userVO, request);
		mv.addObject("result", "회원가입");
		mv.setViewName(page);
		
		return mv;
	}
	
	/**
	 * 회원가입시 아이디 존재 체크
	 * @param user_id
	 * @return int
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/checkId", method = RequestMethod.POST)
	public @ResponseBody int checkId(@RequestParam("user_id") String user_id) throws Exception {
		
		return userService.selectCheckId(user_id);
	}
	
	/**
	 * 회원수정 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/modify", method = RequestMethod.GET)
	public ModelAndView getModify(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
//		UserVO userVO = (UserVO)request.getSession().getAttribute("userVO");
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(user_id);
		userVO = userService.selectOneUser(user_id);
		System.out.println(userVO);
		mv.addObject("userVO", userVO);
		mv.setViewName("/contents/user/modify");
		
		return mv;
	}
	
	/**
	 * 회원수정기능
	 * @param userVO
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/modify", method = RequestMethod.POST)
	public ModelAndView postModify(UserVO userVO) throws Exception{
		
		String page = "/contents/complete";
		ModelAndView mv = new ModelAndView();
		userService.updateUser(userVO);
		
		mv.addObject("result", "회원수정");
		mv.setViewName(page);
		return mv;
	}
	
	/**
	 * 비밀번호수정 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/modifyPassword", method = RequestMethod.GET)
	public ModelAndView getModifyPassword(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
//		String user = (String)request.getSession().getAttribute("user");
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		
		mv.addObject("userVO", userVO);
		mv.setViewName("/contents/user/modifyPassword");
		return mv;
	}
	
	/**
	 * 비밀번호 수정기능
	 * @param userVO
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/modifyPassword", method = RequestMethod.POST)
	public ModelAndView postModifyPassword(UserVO userVO, HttpServletRequest request) throws Exception{
		
		String page = "contents/complete";
		ModelAndView mv = new ModelAndView();
		userService.updatePassword(userVO);
		
		mv.addObject("result", "비밀번호 수정");
		mv.setViewName(page);
		return mv;
	}
	
	/**
	 * 회원탈퇴 페이지 이동
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/signOut", method = RequestMethod.GET)
	public ModelAndView getSignOut(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		UserVO userVO = new UserVO();
//		String user = (String)request.getSession().getAttribute("user");
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		userVO = userService.selectOneUser(user_id);
		
		mv.addObject("userVO", userVO);
		mv.setViewName("contents/user/signOut");
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
		
		ModelAndView mv = new ModelAndView();
		
		if(userVO == null) {
			mv.addObject("result", "회원탈퇴 에러");
			mv.setViewName("contents/error");
		} else {
			logger.info("signout user. " + userVO);
			mv.addObject("result", "회원탈퇴");
			mv.setViewName("contents/complete");
			userVO = userService.selectOneUser((String)request.getSession().getAttribute("user"));
			userService.signOut(userVO);
			session.invalidate();
		}
		return mv;
	}
	
	/**
	 * 테스트용 보유포인트 추가
	 * @param request
	 * @param point
	 * @param userVO
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/point", method = RequestMethod.POST)
	public String getUserPoint(HttpServletRequest request,
							   @RequestParam int point) throws Exception {
		
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		HashMap<String, Object> userpointMap = new HashMap<String, Object>();
		
		userpointMap.put("user_id", user_id);
		userpointMap.put("point", point);
		userpointService.updateTestPoint(userpointMap);
		
		return "/index";
	}
	
	/**
	 * 유저 비밀번호 찾기 화면 호출 메소드
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/password", method = RequestMethod.GET)
	public String getFindPassword(HttpServletRequest request) throws Exception {
		
		return "/contents/user/find_password";
	}
	
	/**
	 * 이메일 인증번호 인증완료 후 회원 비밀번호 변경
	 * @param request
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	public String postFindPassword(HttpServletRequest request,
									    @ModelAttribute UserVO userVO) throws Exception {
		
		return "contents/user/find_password";
	}
	
	/**
	 * 이메일 인증번호 인증완료 후 회원 비밀번호 수정 기능 메소드
	 * @param request
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/password/modify", method = RequestMethod.POST)
	public ModelAndView getFindPasswordModify(HttpServletRequest request,
									    @ModelAttribute UserVO userVO) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
		
		mv.addObject("user_id", user_id);
		mv.setViewName("contents/user/find_password_modify");
		
		return mv;
	}
}
