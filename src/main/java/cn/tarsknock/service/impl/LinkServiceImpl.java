package cn.tarsknock.service.impl;

import cn.tarsknock.dao.LinkDao;
import cn.tarsknock.entity.Link;
import cn.tarsknock.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public Link findById(Integer id) {
        return linkDao.findById(id);
    }

    @Override
    public List<Link> list() {
        return linkDao.list();
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return linkDao.getTotal(paramMap);
    }

    @Override
    public Integer add(Link link) {
        return linkDao.add(link);
    }

    @Override
    public Integer update(Link link) {
        return linkDao.update(link);
    }

    @Override
    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}
