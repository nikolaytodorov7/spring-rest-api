package app.springrestapi.controller;

import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.CommentMapper;
import app.springrestapi.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @GetMapping({"/comments?postId={id}", "posts/{id}/comments"})
    public List<Comment> getCommentsByPostId(@PathVariable int id) throws NotFoundException {
        List<Comment> commentsByPostId = commentMapper.getCommentsByPostId(id);
        if (commentsByPostId == null || commentsByPostId.size() == 0)
            throw new NotFoundException(String.format("Post with id: '%d' does not have comments or does not exist", id));

        return commentsByPostId;
    }
}
