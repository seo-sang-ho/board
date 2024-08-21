package article.projectNum1;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

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
		// 30명의 더미 회원과 각 회원에 대해 1개의 더미 글 생성
		IntStream.range(0, 30).forEach(i -> {
			// 회원 생성
			Member member = new Member();
			member.setUsername("user" + i);  // "user0", "user1", ..., "user29"
			member.setPassword("password" + i); // "password0", "password1", ..., "password29"
			member.setEmail("user" + i + "@example.com"); // "user0@example.com", "user1@example.com", ..., "user29@example.com"
			memberRepository.save(member);

			// 글 생성
			Write write = new Write();
			write.setMember(member);
			write.setTitle("Title " + i);  // "Title 0", "Title 1", ..., "Title 29"
			write.setContent("Content for write " + i + ". This is a sample content for the write number " + i + "."); // Sample content
			write.setCreate_at(LocalDateTime.now());
			write.setModify_at(null);
			writeRepository.save(write);
		});
	}
}
