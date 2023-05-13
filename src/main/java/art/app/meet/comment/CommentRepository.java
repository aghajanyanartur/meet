package art.app.meet.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorId(Long authorId);
    List<Comment> findByPostId(Long postId);
    List<Comment> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}