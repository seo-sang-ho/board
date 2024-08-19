package article.projectNum1.comment.entity;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;

import article.projectNum1.member.entity.Member;
import article.projectNum1.write.entity.Write;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comment {

	@Id @GeneratedValue
	@Column(name = "comment_id")
	private Long id;
	private String comment;
	private LocalDateTime create_at;
	private LocalDateTime modify_at;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "write_id")
	private Write write;

	public void setMember(Member member) {
		this.member = member;
		member.getComments().add(this);
	}

	public void setWrite(Write write) {
		this.write = write;
		write.getComments().add(this);
	}
}
