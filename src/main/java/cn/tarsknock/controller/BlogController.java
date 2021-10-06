package cn.tarsknock.controller;

import cn.tarsknock.entity.Blog;
import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;


    @RequestMapping(path = "/article/{id}")
    public ModelAndView article(@PathVariable("id")Integer id,
                                HttpServletRequest request){

        ModelAndView mav = new ModelAndView();
        Blog blog = blogService.findById(id);
        Blog lastBlog = blogService.getLastBlog(id);
        Blog nextBlog = blogService.getNextBlog(id);
        blog.setClickHit(blog.getClickHit()+1);     //阅读人数加一
        blogService.updateReply(blog);

        //查询评论
        Map<String, Object> map = new HashMap<>();
        map.put("blogId", id);
        map.put("state",1);
        mav.addObject("commentList",commentService.list(map));

        mav.setViewName("article");
        mav.addObject("blog", blog);
        //上一篇下一篇信息
        if(lastBlog != null) {
            mav.addObject("lastBlogTitle", lastBlog.getTitle());
            mav.addObject("lastBlogId", lastBlog.getId());
        }
        else{
            mav.addObject("lastBlogTitle", "没有上一篇啦");
            mav.addObject("lastBlogId", 1);
        }
        if(nextBlog != null) {
            mav.addObject("nextBlogTitle", nextBlog.getTitle());
            mav.addObject("nextBlogId", nextBlog.getId());
        }
        else{
            mav.addObject("nextBlogTitle", "没有下一篇啦");
            mav.addObject("nextBlogId", 1);
        }
        return mav;
    }


}
