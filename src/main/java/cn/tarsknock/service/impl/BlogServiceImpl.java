package cn.tarsknock.service.impl;

import cn.tarsknock.dao.BlogDao;
import cn.tarsknock.dao.CommentDao;
import cn.tarsknock.entity.Blog;
import cn.tarsknock.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Blog> countList() {
        return blogDao.countList();
    }

    @Override
    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    @Override
    public List<Blog> list(Map<String, Object> paramMap) {
        return blogDao.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogDao.getTotal(paramMap);
    }

    @Override
    public Integer add(Blog blog) {
        return blogDao.add(blog);
    }

    @Override
    public Integer updateContent(Blog blog) {
        return blogDao.updateContent(blog);
    }

    @Override
    public Integer updateReply(Blog blog) {
        return blogDao.updateReply(blog);
    }

    @Override
    public Integer delete(Integer id) {
        commentDao.deleteByBlog(id);
        return blogDao.delete(id);
    }

    @Override
    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    @Override
    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }
}
