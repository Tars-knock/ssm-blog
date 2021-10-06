package cn.tarsknock.dao;

import cn.tarsknock.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentDao {

    public Integer add(Comment comment);

    public Integer update(Comment comment);

    public List<Comment> list(Map<String, Object> map);

    public Long getTotal(Map<String, Object> map);

    public Integer delete(Integer id);

    public Comment findById(Integer id);

    public Integer deleteByBlog(Integer id);
}
