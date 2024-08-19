package article.projectNum1;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import article.projectNum1.member.entity.Member;
import article.projectNum1.member.repository.MemberRepository;
import article.projectNum1.write.entity.Write;
import article.projectNum1.write.repository.WriteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {

	private final MemberRepository memberRepository;
	private final WriteRepository writeRepository;

	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setUsername("qwe");
		member.setPassword("qwe");
		member.setEmail("qwe@naver.com");
		memberRepository.save(member);

		Write write = new Write();
		write.setMember(member);
		write.setTitle("제목1");
		write.setContent("내용1");
		write.setCreate_at(LocalDateTime.now());
		write.setModify_at(null);
		writeRepository.save(write);
	}
}
