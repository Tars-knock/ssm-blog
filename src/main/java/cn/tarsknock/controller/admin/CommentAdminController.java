package cn.tarsknock.controller.admin;

import cn.tarsknock.entity.PageBean;
import cn.tarsknock.service.CommentService;
import cn.tarsknock.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RequiresRoles("admin")
@Controller
public class CommentAdminController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/admin/comments")
    public String comments(HttpServletRequest request,
                           @RequestParam(value = "page", required = false, defaultValue = "1") String page){

        /**
         * comment_list页面 一页有多少行
         */
        int pageSize = 6;
        PageBean pageBean = new PageBean(Integer.parseInt(page), pageSize, commentService.getTotal(null).intValue());
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", pageBean.getStart());
        paramMap.put("size", pageBean.getPageSize());

        request.setAttribute("comments", commentService.list(paramMap));
        request.setAttribute("pageInfo", pageBean);
        return "admin/comment_list";
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/comments/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil pass(String id){
        if(commentService.approved(Integer.parseInt(id)))   return ResponseUtil.success();
        else return ResponseUtil.fail("审核操作失败");
    }

    @RequiresPermissions("admin:change")
    @RequestMapping(path = "/admin/comments/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil delete(String id){
        try {
            commentService.delete(Integer.parseInt(id));
            return ResponseUtil.success();
        }catch (Exception e){
            return ResponseUtil.fail("删除评论失败");
        }
    }
}
