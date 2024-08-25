package article.projectNum1.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import article.projectNum1.member.entity.LoginForm;
import article.projectNum1.member.entity.Member;
import article.projectNum1.member.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	/*
	로그인 페이지
	 */
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login/loginForm";
	}

	/*
	로그인 기능
	 */
	@PostMapping("/login")
	public String login(@Valid LoginForm loginForm, Model model, HttpServletRequest request) {
		Member loginMember = loginService.login(loginForm);
		model.addAttribute("loginMember", loginMember);

		if(loginMember == null){
			throw new IllegalStateException("로그인 할 수 없습니다.");
		}

		/* 쿠키 로그인 방식
		Cookie cookie = new Cookie("username", loginMember.getUsername());
		cookie.setMaxAge(60 * 30); // 30분
		response.addCookie(cookie);
		 */

		//세션 로그인 방식
		HttpSession session = request.getSession();
		session.setAttribute("loginMember",loginMember);

		return "redirect:/";
	}

	/*
	로그아웃 기능
	 */
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		// Cookie cookie = new Cookie("username",null);
		// cookie.setMaxAge(0);
		// response.addCookie(cookie);

		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate(); // 세션 무효화
		}
		return "redirect:/";
	}
}
