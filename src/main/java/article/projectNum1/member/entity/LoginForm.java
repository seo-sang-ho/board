package article.projectNum1.member.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}
