package cn.tarsknock.service.impl;

import cn.tarsknock.dao.BlogDao;
import cn.tarsknock.dao.CommentDao;
import cn.tarsknock.entity.Comment;
import cn.tarsknock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Override
    public Integer add(Comment comment) {
        return commentDao.add(comment);
    }

    @Override
    public Integer update(Comment comment) {
        return commentDao.update(comment);
    }

    @Override
    public List<Comment> list(Map<String, Object> map) {
        return commentDao.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return commentDao.getTotal(map);
    }

    @Override
    public Integer delete(Integer id) {
        return commentDao.delete(id);
    }

    @Override
    public Comment findById(Integer id) {
        return commentDao.findById(id);
    }

    @Override
    public boolean approved(Integer id) {
        try {
            Comment comment = commentDao.findById(id);
            comment.setState(1);
            commentDao.update(comment);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
