package app.springrestapi.controller;

import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.Comment;
import app.springrestapi.pojo.Post;
import app.springrestapi.service.CommentService;
import app.springrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Post> getAllPosts() throws NotFoundException {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) throws NotFoundException {
        return postService.getPostById(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable int id) throws NotFoundException {
        return commentService.getCommentsByPostId(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) throws IllegalDataException {
        return postService.createPost(post);
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post) throws NotFoundException, IllegalDataException {
        return postService.updatePost(post);
    }

    @DeleteMapping(value = "/{id}")
    public Post deletePost(@PathVariable int id) throws NotFoundException {
        return postService.deletePost(id);
    }
}
