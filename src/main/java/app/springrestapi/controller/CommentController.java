package app.springrestapi.controller;

import app.springrestapi.annotation.Role;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.Comment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping()
public interface CommentController {
    @Role("USER")
    @GetMapping({"/comments?postId={id}", "posts/{id}/comments"})
    List<Comment> getCommentsByPostId(@PathVariable int id) throws NotFoundException;
}
