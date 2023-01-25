package app.springrestapi.service;

import app.springrestapi.exception.NotFoundException;
import app.springrestapi.mapper.CommentMapper;
import app.springrestapi.mapper.PostMapper;
import app.springrestapi.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(int id) throws NotFoundException {
        List<Comment> commentsByPostId = commentMapper.getCommentsByPostId(id);
        if (commentsByPostId == null || commentsByPostId.size() == 0)
            throw new NotFoundException(String.format("Post with id: '%d' does not have comments or does not exist", id));

        return commentsByPostId;
    }
}
