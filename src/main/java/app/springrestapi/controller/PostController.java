package app.springrestapi.controller;

import app.springrestapi.annotation.Role;
import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.pojo.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
public interface PostController {

    @Role("USER")
    @GetMapping
    List<Post> getAllPosts() throws NotFoundException;

    @Role("USER")
    @GetMapping("/{id}")
    Post getPostById(@PathVariable int id) throws NotFoundException;

    @Role("USER")
    @PostMapping
    Post createPost(@RequestBody Post post) throws IllegalDataException;

    @Role("USER")
    @PutMapping
    Post updatePost(@RequestBody Post post) throws NotFoundException, IllegalDataException;

    @Role("USER")
    @DeleteMapping(value = "/{id}")
    Post deletePost(@PathVariable int id) throws NotFoundException;
}
