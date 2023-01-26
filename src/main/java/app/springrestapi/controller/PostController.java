package app.springrestapi.controller;

import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
public interface PostController {
    @GetMapping
    List<Post> getAllPosts() throws NotFoundException;

    @GetMapping("/{id}")
    Post getPostById(@PathVariable int id) throws NotFoundException;

    @PostMapping
    Post createPost(@RequestBody Post post) throws IllegalDataException;

    @PutMapping
    Post updatePost(@RequestBody Post post) throws NotFoundException, IllegalDataException;

    @DeleteMapping(value = "/{id}")
    Post deletePost(@PathVariable int id) throws NotFoundException;
}
