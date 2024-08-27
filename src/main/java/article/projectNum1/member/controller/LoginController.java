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
		try {
			Member loginMember = loginService.login(loginForm);

			if (loginMember == null) {
				model.addAttribute("errorMessage", "로그인 할 수 없습니다. 아이디 또는 비밀번호를 확인하세요.");
				return "login/loginForm"; // 로그인 페이지로 포워딩
			}

			// 세션 로그인 방식
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);

			return "redirect:/";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "로그인 중 오류가 발생했습니다. 다시 시도해주세요.");
			return "login/loginForm"; // 오류 발생 시 로그인 페이지로 포워딩
		}
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
