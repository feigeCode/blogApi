package com.feige.pojo;

import java.util.List;

public class CommentAndReplies {
    private Comment rootComment;
    private List<Comment> replies;

    public CommentAndReplies(Comment rootComment, List<Comment> replies) {
        this.rootComment = rootComment;
        this.replies = replies;
    }

    public CommentAndReplies() {
    }

    public Comment getRootComment() {
        return rootComment;
    }

    public void setRootComment(Comment rootComment) {
        this.rootComment = rootComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "CommentAndReplies{" +
                "rootComment=" + rootComment +
                ", replies=" + replies +
                '}';
    }
}
