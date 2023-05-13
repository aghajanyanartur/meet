package art.app.meet.comment;

import art.app.meet.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorId(Long authorId);
    List<Comment> findByPost(Post post);
    List<Comment> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}