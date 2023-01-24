package app.springrestapi.controller;

import app.springrestapi.mapper.PostMapper;
import app.springrestapi.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id) {
        return postMapper.getPostById(id);
    }

    @PostMapping(value = "/posts", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Post createPost(@RequestBody Post post) {
        return postMapper.insertPost(post);
    }

    @PutMapping(value = "/posts", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Post updatePost(@RequestBody Post post) {
        return postMapper.updatePost(post);
    }

    @DeleteMapping(value = "/posts/{id}")
    public Post deletePost(@PathVariable int id) {
        return postMapper.deletePost(id);
    }
}
