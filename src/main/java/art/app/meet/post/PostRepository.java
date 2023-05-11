package art.app.meet.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthorId(Long authorId);
    List<Post> findByTitleContainingIgnoreCase(String keyword);
    List<Post> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
