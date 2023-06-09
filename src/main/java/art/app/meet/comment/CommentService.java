package art.app.meet.comment;

import art.app.meet.post.Post;
import art.app.meet.post.PostNotFoundException;
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

    public List<Comment> getCommentsByPost(Long postId) {
        log.info("Getting comments by post: {}", postId);
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getCommentsBetween(LocalDateTime start, LocalDateTime end) {
        log.info("Getting comments between - start: {}, end: {}", start, end);
        return commentRepository.findByCreatedAtBetween(start, end);
    }

    public Comment createComment(Comment comment) {
        log.info("Creating and saving comment: {}", comment);
        return commentRepository.saveAndFlush(comment);
    }

    public Comment updateComment(Long id, Comment comment) {
        log.info("Updating comment by id: {}, with comment: {}", id, comment);
        var existingComment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
        existingComment.setContent(comment.getContent());
        return commentRepository.saveAndFlush(comment);
    }

    public void deleteComment(Long id) {
        log.info("Deleting comment by id: {}", id);
        commentRepository.deleteById(id);
    }
}