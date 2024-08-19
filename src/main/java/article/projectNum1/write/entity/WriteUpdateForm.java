package article.projectNum1.write.entity;

import article.projectNum1.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WriteUpdateForm {

	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private Long memberId;
}
