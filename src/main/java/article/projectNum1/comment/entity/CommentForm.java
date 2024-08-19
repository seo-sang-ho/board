package article.projectNum1.comment.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentForm {

	private String comment;
	private Long writeId;
	private Long memberId;
}
