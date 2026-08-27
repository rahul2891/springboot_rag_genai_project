package main.java.devPliot.backend.entity;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "github_id", unique = true, nullable = false)
    private Long githubId;

    @Column(name = "github_username", unique = true, nullable = false)
    private String githubUsername;

    @Column(name = "display_name", nullable = false, length = 200)
    private String displayName;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Column(name = "access_token", nullable = false, columnDefinition = "TEXT")
    private String accessToken;


    @Column(name = "refresh_token", length = 500)
    private String tokenScopes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if(createdAt == null) {
            createdAt = Instant.now();
        }
    }
    
}
