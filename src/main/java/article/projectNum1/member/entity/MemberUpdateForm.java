package article.projectNum1.member.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateForm {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;
}
