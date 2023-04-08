package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
public class CommentReplyBlogRequest {
    private Long blogId;
    private String content;
    private Long commentId;
}
