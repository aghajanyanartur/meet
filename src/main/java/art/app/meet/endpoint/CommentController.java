package art.app.meet.endpoint;

import art.app.meet.comment.Comment;
import art.app.meet.comment.CommentService;
import art.app.meet.post.Post;
import art.app.meet.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    public List<Comment> getAllComments() {
        log.info("Endpoint getting all comments");
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        log.info("Endpoint getting comment by id: {}", id);
        return commentService.getCommentById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<Comment> getCommentsByAuthor(@PathVariable Long authorId) {
        return commentService.getCommentsByAuthor(authorId);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
    @GetMapping("/between")
    public List<Comment> getCommentsBetween(@RequestParam LocalDateTime start,
                                      @RequestParam LocalDateTime end) {
        return commentService.getCommentsBetween(start, end);
    }

    @PostMapping("")
    public Comment createComment(@RequestParam(name = "authorId") Long authorId,
                              @RequestParam(name = "postId") Long postId,
                              @RequestBody Comment comment) {
        log.info("Endpoint creating comment: {}", comment);

        return commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        log.info("Endpoint updating comment by id: {}, with comment: {}", id, comment);
        return commentService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        log.info("Endpoint deleting comment by id: {}", id);
        commentService.deleteComment(id);
    }
}