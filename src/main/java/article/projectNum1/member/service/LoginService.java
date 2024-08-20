package article.projectNum1.member.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import article.projectNum1.member.entity.LoginForm;
import article.projectNum1.member.entity.Member;
import article.projectNum1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

	private final MemberRepository memberRepository;

	public Member login(LoginForm loginForm) {
		Member findMember = memberRepository.findByUsername(loginForm.getUsername());
		if(!findMember.getPassword().equals(loginForm.getPassword())){
			throw new NoSuchElementException("해당 아이디를 찾을 수 없습니다.");
		}
		return findMember;
	}
}
