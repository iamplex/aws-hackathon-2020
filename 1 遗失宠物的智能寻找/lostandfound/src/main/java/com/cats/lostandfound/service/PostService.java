package com.cats.lostandfound.service;

import com.cats.lostandfound.entity.Filter;
import com.cats.lostandfound.entity.Message;
import com.cats.lostandfound.entity.Post;
import com.cats.lostandfound.entity.User;
import com.cats.lostandfound.mapper.PhotoMapper;
import com.cats.lostandfound.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PhotoMapper photoMapper;

    public Message<Post> addPost(Post post) {
        Message<Post> result = new Message<>();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            post.setTimestamp(System.currentTimeMillis());
            long postId = postMapper.post(post);
            post.setPost_id(postId);
            result.setMsg("创建Post成功");
            result.setDetail(post);
            result.setSuccess(true);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Message<Post> deletePost(Post post) {
        Message<Post> result = new Message<>();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            //TODO: delete related photos
            postMapper.deleteByPostId(post.getPost_id());
            result.setMsg("删除成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Message<Post> updatePost(Post post) {
        Message<Post> result = new Message<>();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            post.setTimestamp(System.currentTimeMillis());
            postMapper.updatePost(post.getPost_id(), post.getLocation(), post.getTitle(),
                    post.getDescription(), post.getCat_class(), post.getType(), post.getStatus(),
                    post.getCover_path(), System.currentTimeMillis());
            result.setMsg("发表成功");
            result.setDetail(post);
            result.setSuccess(true);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Message<List<Post>> findPostsByFilters(Filter filter) {
        Message<List<Post>> result = new Message<>();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Post> posts = postMapper.findPostsByFilters(filter);
            result.setMsg("查询成功");
            result.setDetail(posts);
            result.setSuccess(true);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Message<List<Post>> findPostsByUserId(long user_id) {
        Message<List<Post>> result = new Message<>();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Post> posts = postMapper.findPostsByUserId(user_id);
            result.setMsg("查询成功");
            result.setDetail(posts);
            result.setSuccess(true);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
