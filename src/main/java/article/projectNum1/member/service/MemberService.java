package article.projectNum1.member.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import article.projectNum1.member.entity.Member;
import article.projectNum1.member.entity.MemberUpdateForm;
import article.projectNum1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(Member member) {
		memberRepository.save(member);
	}

	public Member findById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("정보에 맞는 회원이 없습니다."));
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public void update(Long id, MemberUpdateForm form) {
		Member findMember = findById(id);
		findMember.setUsername(form.getUsername());
		findMember.setPassword(form.getPassword());
		findMember.setEmail(form.getEmail());
	}

	public void delete(Member member) {
		memberRepository.delete(member);
	}
}
