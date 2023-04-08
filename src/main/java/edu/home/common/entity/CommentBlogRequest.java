package edu.home.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class CommentBlogRequest {
    private Long blogId;
    private String content;
}
