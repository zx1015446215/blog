package com.zx.shark.service.impl;

import com.zx.shark.mapper.CommentMapper;
import com.zx.shark.model.Comment;
import com.zx.shark.model.Tree;
import com.zx.shark.model.User;
import com.zx.shark.service.CommentService;
import com.zx.shark.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RedisTemplate redisTemplate;

    private Long cid;
    @Override
    public  List<Tree<Comment>> findCommentByCid(Long cid) {
        this.cid=cid;
        List<Tree<Comment>> trees = new ArrayList<>();
        List<Comment> comments = commentMapper.selectByCid(cid);
        //将每一条评论放在一颗树中
        for (Comment comment : comments){
            Tree<Comment> tree = new Tree<>();
            tree.setId(comment.getId().toString());
            tree.setParentId(comment.getParent_id().toString());
            tree.setText(comment.getContent());
            Map<String,Object> attributes = new HashMap<>();
            attributes.put("user_id",comment.getUser_id());
            attributes.put("date",comment.getDate());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        //对树的集合进行整理，父节点为0的就为根，每条评论的第一条都为根节点
        List<Tree<Comment>> treeList = BuildTree.buildList(trees, "0");
        return treeList;
    }

    @Override
    public void saveComment(Long parent_id,String content) {
        Long user_id;
        //从SecurityContextHolder中获取用户名
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        String username = String.valueOf(authentication.getPrincipal());
        //从redis缓存中查找用户信息
        ValueOperations<String,User> operations=redisTemplate.opsForValue();
        boolean haskey= redisTemplate.hasKey(username);
        //若缓存中存在
        if(haskey){
            User user=operations.get(username);
            user_id = user.getId();
        }else if(userService.findUserByUsername(username) != null){
            //根据用户名从数据库中获取用户的id
            User user = userService.findUserByUsername(username);
            user_id = user.getId();
        }else{
            user_id=8888L;
        }
        Comment comment = new Comment(cid,user_id,parent_id,content,new Timestamp(System.currentTimeMillis()));

        commentMapper.saveComment(comment);

    }
}
