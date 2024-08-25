package article.projectNum1;

import org.springframework.web.bind.annotation.ModelAttribute;

import article.projectNum1.member.entity.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice {

	//@ModelAttribute("username")
	// public String getUsernameFromCookies(HttpServletRequest request) {
	// 	Cookie[] cookies = request.getCookies();
	// 	if (cookies != null) {
	// 		for (Cookie cookie : cookies) {
	// 			if ("username".equals(cookie.getName())) {
	// 				return cookie.getValue();
	// 			}
	// 		}
	// 	}
	// 	return null; // 로그인되지 않은 경우 null 반환
	// }

	@ModelAttribute("username")
	public String getUsernameFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object username = session.getAttribute("loginMember");
			if(username != null){
				return ((Member) username).getUsername();
			}
		}
		return null; // 로그인되지 않은 경우 null 반환
	}
}
