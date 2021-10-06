package cn.tarsknock.service;

import cn.tarsknock.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    /**
     *
     * @return 所有的blogtype
     */
    public List<Blog> countList();

    /**
     * 用id查找博客类型
     * @return
     */
    public Blog findById(Integer id);

    /**
     * 不固定参数查询类型参数列表
     * @param paramMap
     * @return
     */
    public List<Blog> list(Map<String, Object> paramMap);

    /**
     * 不固定类型查询类型数
     * @param paramMap
     * @return
     */
    public Long getTotal(Map<String, Object> paramMap);
    public Integer add(Blog blog);

    public Integer updateContent(Blog blog);

    public Integer updateReply(Blog blog);


    public Integer delete(Integer id);

    public Blog getLastBlog(Integer id);

    public Blog getNextBlog(Integer id);
}
