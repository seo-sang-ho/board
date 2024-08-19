package article.projectNum1.comment.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import article.projectNum1.comment.entity.Comment;
import article.projectNum1.comment.entity.CommentUpdateForm;
import article.projectNum1.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	public void save(Comment comment) {
		commentRepository.save(comment);
	}

	public Comment findById(Long id) {
		return commentRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("없는 댓글입니다."));
	}

	public void update(Long id, CommentUpdateForm commentUpdateForm) {
		Comment findComment = findById(id);

		findComment.setComment(commentUpdateForm.getComment());
		findComment.setModify_at(LocalDateTime.now());
	}

	public void delete(Long id) {
		Comment findComment = findById(id);
		commentRepository.delete(findComment);
	}
}
