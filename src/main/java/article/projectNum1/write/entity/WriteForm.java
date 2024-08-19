package article.projectNum1.write.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteForm {

	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	@NotNull
	private Long memberId;
}
