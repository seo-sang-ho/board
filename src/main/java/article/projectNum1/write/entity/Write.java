package article.projectNum1.write.entity;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import article.projectNum1.comment.entity.Comment;
import article.projectNum1.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Write {

	@Id @GeneratedValue
	@Column(name = "write_id")
	private Long id;
	private String title;
	private String content;
	private LocalDateTime create_at;
	private LocalDateTime modify_at;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "write", cascade = ALL)
	private List<Comment> comments = new ArrayList<>();

	public void setMember(Member member) {
		this.member = member;
		member.getWrites().add(this);
	}
}
