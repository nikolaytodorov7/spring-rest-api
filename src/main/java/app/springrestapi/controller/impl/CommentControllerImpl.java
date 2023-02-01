package app.springrestapi.controller.impl;

import app.springrestapi.annotation.Role;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.CommentMapper;
import app.springrestapi.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentControllerImpl {

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(int id) throws NotFoundException {
        List<Comment> commentsByPostId = commentMapper.getCommentsByPostId(id);
        if (commentsByPostId == null || commentsByPostId.size() == 0)
            throw new NotFoundException(String.format("Post with id: '%d' does not have comments or does not exist", id));

        return commentsByPostId;
    }
}