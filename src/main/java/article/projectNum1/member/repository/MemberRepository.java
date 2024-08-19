package article.projectNum1.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import article.projectNum1.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
