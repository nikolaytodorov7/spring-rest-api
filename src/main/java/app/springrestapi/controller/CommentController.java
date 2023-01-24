package app.springrestapi.controller;

import app.springrestapi.mapper.CommentMapper;
import app.springrestapi.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @GetMapping({"/posts/{id}/comments", "comments?postId={id}"})
    public List<Comment> getCommentsByPostId(@PathVariable int id) {
        return commentMapper.getCommentsByPostId(id);
    }
}
