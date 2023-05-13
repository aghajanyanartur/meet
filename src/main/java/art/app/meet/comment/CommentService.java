package art.app.meet.comment;

import art.app.meet.post.Post;
import art.app.meet.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        log.info("Getting all comments");
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        log.info("Getting comment by id: {}", id);
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public List<Comment> getCommentsByAuthor(Long authorId) {
        log.info("Getting comments by author: {}", authorId);
        return commentRepository.findByAuthorId(authorId);
    }

    public List<Comment> getCommentsByPost(Post post) {
        log.info("Getting comments by post: {}", post);
        return commentRepository.findByPost(post);
    }

    public List<Comment> getCommentsBetween(LocalDateTime start, LocalDateTime end) {
        log.info("Getting comments between - start: {}, end: {}", start, end);
        return commentRepository.findByCreatedAtBetween(start, end);
    }

    public Comment createComment(Comment comment) {
        log.info("Creating and saving comment: {}", comment);
        return commentRepository.saveAndFlush(comment);
    }

    public Comment updateComment(Comment comment) {
        log.info("Updating comment: {}", comment);
        return commentRepository.saveAndFlush(comment);
    }

    public void deleteComment(Long id) {
        log.info("Deleting comment by id: {}", id);
        commentRepository.deleteById(id);
    }
}