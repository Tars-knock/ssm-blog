package cn.tarsknock.service;

import cn.tarsknock.entity.Link;

import java.util.List;
import java.util.Map;

public interface LinkService {
    //    /**
//     *
//     * @return 所有的Link
//     */
//    public List<Link> countList();

    /**
     * 用id查找博客类型
     * @return
     */
    public Link findById(Integer id);

    /**
     * 不固定参数查询友链列表
     * @return
     */
//    public List<Link> list(Map<String, Object> paramMap);
    public List<Link> list();

    /**
     * 不固定类型查询类型数
     * @param paramMap
     * @return
     */
    public Long getTotal(Map<String, Object> paramMap);
    public Integer add(Link link);

    public Integer update(Link link);

    public Integer delete(Integer id);
}
