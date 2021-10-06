package cn.tarsknock.service;

import cn.tarsknock.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public Integer add(Comment comment);

    public Integer update(Comment comment);

    public List<Comment> list(Map<String, Object> map);

    public Long getTotal(Map<String, Object> map);

    public Integer delete(Integer id);

    public Comment findById(Integer id);

    /**
     * 调用该方法将评论设置为审核通过
     * @param 待审核的comment的id
     * @return  是否更改成功
     */
    public boolean approved(Integer id);
}
