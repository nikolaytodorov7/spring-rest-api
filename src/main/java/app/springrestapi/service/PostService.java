package app.springrestapi.service;

import app.springrestapi.exception.IllegalDataException;
import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.PostMapper;
import app.springrestapi.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostMapper postMapper;

    public List<Post> getAllPosts() throws NotFoundException {
        List<Post> allPosts = postMapper.getAllPosts();
        if (allPosts == null || allPosts.size() == 0)
            throw new NotFoundException("Can't load all posts! No posts found!");

        return allPosts;
    }

    public Post getPostById(int id) throws NotFoundException {
        Post post = postMapper.getPostById(id);
        if (post == null)
            throw new NotFoundException(String.format("Post with id '%d' not found!", id));

        return post;
    }

    public Post createPost(Post post) throws IllegalDataException {
        if (post == null)
            throw new IllegalDataException("Invalid post provided!");

        postMapper.insertPost(post);
        return postMapper.insertPost(post);
    }

    public Post updatePost(Post post) throws NotFoundException, IllegalDataException {
        Post oldPost = getPostById(post.id);
        if (oldPost.equals(post))
            throw new IllegalDataException("Unable to update post which is the same!");

        return postMapper.updatePost(post);
    }

    public Post deletePost(int id) throws NotFoundException {
        getPostById(id);
        return postMapper.deletePost(id);
    }
}
