package com.blind.api.domain.like.domain;

import com.blind.api.domain.comment.v1.domain.Comment;
import com.blind.api.domain.post.v2.domain.Post;
import com.blind.api.domain.user.v2.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CommentLike {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "comment_id", nullable = false)
    @ManyToOne
    private Comment comment;

    @JoinColumn(name = "postId", nullable = false)
    @ManyToOne
    private Post post;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @Builder
    public CommentLike(Comment comment, Post post, User user) {
        Assert.notNull(comment, "commentId must not be Null");
        Assert.notNull(user, "user must not be Null");
        Assert.notNull(post, "post must not be Null");
        this.comment = comment;
        this.post = post;
        this.user = user;
    }

}
