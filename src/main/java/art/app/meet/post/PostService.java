package art.app.meet.post;

import art.app.meet.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> getAllPosts() {
        log.info("Getting all posts");
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        log.info("Getting post by id: {}", id);
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public List<Post> getPostsByAuthor(Long authorId) {
        log.info("Getting posts by author with id: {}", authorId);
        return postRepository.findByAuthorId(authorId);
    }

    public List<Post> searchPosts(String keyword) {
        log.info("Searching for posts with keyword: {}", keyword);
        return postRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .filter(post -> post.getContent().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Post> getPostsBetween(LocalDateTime start, LocalDateTime end) {
        log.info("Getting posts between - start: {}, end: {}", start, end);
        return postRepository.findByCreatedAtBetween(start, end);
    }

    public Post createPost(Post post) {
        log.info("Creating and saving post: {}", post);
        return postRepository.saveAndFlush(post);
    }

    public Post updatePost(Long id, Post post) {
        log.info("Updating post by id: {}, with post: {}", id, post);
        var existingPost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        existingPost.setContent(post.getContent());
        existingPost.setTitle(post.getTitle());
        return postRepository.saveAndFlush(post);
    }

    public void deletePost(Long id) {
        log.info("Deleting post by id: {}", id);
        postRepository.deleteById(id);
    }
}