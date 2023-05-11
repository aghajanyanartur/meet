package art.app.meet.post;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("Could not find post with id " + id);
    }
}