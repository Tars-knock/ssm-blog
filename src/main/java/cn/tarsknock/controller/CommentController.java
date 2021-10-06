package cn.tarsknock.controller;

import cn.tarsknock.entity.Blog;
import cn.tarsknock.entity.Comment;
import cn.tarsknock.service.BlogService;
import cn.tarsknock.service.CommentService;
import cn.tarsknock.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @RequestMapping(path = "/article/comment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil comment(Comment comment,
                                HttpServletRequest request){
        try {
            comment.setUserIp(request.getRemoteAddr());
            commentService.add(comment);

            Blog blog = blogService.findById(comment.getBlog().getId());
            blog.setReplyHit(blog.getReplyHit()+1);

            blogService.updateReply(blog);
            return ResponseUtil.success();
        }catch (Exception e){
            return ResponseUtil.fail("评论失败");
        }

    }

}
