package article.projectNum1.comment.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import article.projectNum1.comment.entity.Comment;
import article.projectNum1.comment.entity.CommentForm;
import article.projectNum1.comment.service.CommentService;
import article.projectNum1.member.entity.Member;
import article.projectNum1.member.service.MemberService;
import article.projectNum1.write.entity.Write;
import article.projectNum1.write.service.WriteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final WriteService writeService;
	private final MemberService memberService;

	@PostMapping("/comments/new")
	public String newComment(CommentForm commentForm){
		Comment comment = new Comment();
		comment.setComment(commentForm.getComment());
		Member findMember = memberService.findById(commentForm.getMemberId());
		comment.setMember(findMember);
		Write findWrite = writeService.findById(commentForm.getWriteId());
		comment.setWrite(findWrite);
		comment.setCreate_at(LocalDateTime.now());
		commentService.save(comment);
		return "redirect:/writes/" + commentForm.getWriteId();
	}
}
