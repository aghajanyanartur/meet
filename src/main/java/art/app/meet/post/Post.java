package art.app.meet.post;

import art.app.meet.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Slf4j
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @PrePersist
    public void onCreated() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}