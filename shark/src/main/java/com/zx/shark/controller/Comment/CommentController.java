package com.zx.shark.controller.Comment;

import com.zx.shark.mapper.CommentMapper;
import com.zx.shark.model.Comment;
import com.zx.shark.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/comment")
@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @RequestMapping("/list/{cid}")
    public List<Comment> getCommentByCid(@PathVariable("cid") long cid){
        List<Comment> comments = commentMapper.selectByCid(cid);
        System.out.println(comments.get(0).getContent());
        return comments;
    }
}
