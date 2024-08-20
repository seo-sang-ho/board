package article.projectNum1.member.entity;

import static jakarta.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;

import article.projectNum1.comment.entity.Comment;
import article.projectNum1.write.entity.Write;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	private String username;
	private String email;
	private String password;

	private MemberRole role;

	@OneToMany(mappedBy = "member", cascade = ALL)
	private List<Write> writes = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = ALL)
	private List<Comment> comments = new ArrayList<>();
}
