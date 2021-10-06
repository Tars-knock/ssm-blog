package cn.tarsknock.service.impl;

import cn.tarsknock.dao.BlogTypeDao;
import cn.tarsknock.entity.BlogType;
import cn.tarsknock.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {
    @Autowired
    private BlogTypeDao blogTypeDao;

    @Override
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    @Override
    public BlogType findById(Integer id) {
        return blogTypeDao.findById(id);
    }

    @Override
    public List<BlogType> list(Map<String, Object> paramMap) {
        return blogTypeDao.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogTypeDao.getTotal(paramMap);
    }

    //集成了update
    @Override
    public Integer add(BlogType blogType) {
        BlogType temp = blogTypeDao.findById(blogType.getId());
        if(temp!=null){
            return blogTypeDao.update(blogType);
        }
        return blogTypeDao.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }
}
