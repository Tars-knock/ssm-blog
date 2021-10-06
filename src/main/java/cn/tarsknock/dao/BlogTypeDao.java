package cn.tarsknock.dao;

import cn.tarsknock.entity.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeDao {
    /**
     *
     * @return 所有的blogtype
     */
    public List<BlogType> countList();

    /**
     * 用id查找博客类型
     * @return
     */
    public BlogType findById(Integer id);

    /**
     * 不固定参数查询类型参数列表
     * @param paramMap
     * @return
     */
    public List<BlogType> list(Map<String, Object> paramMap);

    /**
     * 不固定类型查询类型数
     * @param paramMap
     * @return
     */
    public Long getTotal(Map<String, Object> paramMap);
    public Integer add(BlogType blogType);

    public Integer update(BlogType blogType);

    public Integer delete(Integer id);
}
