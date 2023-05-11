package art.app.meet.endpoint;

import art.app.meet.post.Post;
import art.app.meet.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public List<Post> getAllPosts() {
        log.info("Endpoint getting all posts");
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        log.info("Endpoint getting post by id: {}", id);
        return postService.getPostById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<Post> getPostsByAuthor(@PathVariable Long authorId) {
        return postService.getPostsByAuthor(authorId);
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    @GetMapping("/between")
    public List<Post> getPostsBetween(@RequestParam LocalDateTime start,
                                      @RequestParam LocalDateTime end) {
        return postService.getPostsBetween(start, end);
    }

    @PostMapping("")
    public Post createPost(@RequestParam(name = "authorId") Long authorId,
                           @RequestBody Post post) {
        log.info("Endpoint creating post: {}", post);

        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        log.info("Endpoint updating user by id: {}, with post: {}", id, post);
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        log.info("Endpoint deleting post by id: {}", id);
        postService.deletePost(id);
    }
}