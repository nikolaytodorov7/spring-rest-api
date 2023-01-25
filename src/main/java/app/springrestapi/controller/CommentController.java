package app.springrestapi.controller;

import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.CommentMapper;
import app.springrestapi.pojo.Comment;
import app.springrestapi.service.CommentService;
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
    CommentService commentService;

    @GetMapping("?postId={id}")
    public List<Comment> getCommentsByPostId(@PathVariable int id) throws NotFoundException {
        return commentService.getCommentsByPostId(id);
    }
}
