package article.projectNum1.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import article.projectNum1.comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
